package gov.nist.hla.som;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class SOMReader {
    private static final Logger logger = LogManager.getLogger();
    
    private DocumentBuilder builder;
    
    private LinkedList<InteractionInfo> interactionClasses = new LinkedList<InteractionInfo>();
    
    private LinkedList<ObjectInfo> objectClasses = new LinkedList<ObjectInfo>();
  
    public SOMReader()
            throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        this.builder = factory.newDocumentBuilder();
    }
    
    public List<InteractionInfo> getInteractionClasses() {
        return interactionClasses;
    }
    
    public List<ObjectInfo> getObjectClasses() {
        return objectClasses;
    }
    
    public void readXML(String filepath)
            throws IOException {
        InputStream input = null;

        try {
            logger.info("reading the SOM file " + filepath);
            input = new FileInputStream(filepath);
            Document document = builder.parse(input);
            Element root = document.getDocumentElement();

            logger.debug("on interactions...");
            LinkedList<Element> interactions = getChildrenByTagName(root, "interactions");
            if (interactions.size() > 1) {
                throw new IOException("SOM contains multiple interaction tables");
            } else if (interactions.isEmpty()) {
                logger.info("SOM does not contain an interaction table");
            } else {
                LinkedList<Element> interactionClass =
                        getChildrenByTagName(interactions.getFirst(), "interactionClass");
                if (interactionClass.size() > 1) {
                    throw new IOException("SOM contains multiple top-level interaction classes");
                } else if (interactionClass.isEmpty()) {
                    logger.info("SOM does not contain any interaction classes");
                } else {
                    parseInteraction(interactionClass.getFirst(), null);
                }
            }

            logger.debug("on objects...");
            LinkedList<Element> objects = getChildrenByTagName(root, "objects");
            if (objects.size() > 1) {
                throw new IOException("SOM contains multiple object tables");
            } else if (objects.isEmpty()) {
                logger.info("SOM does not contain an object table");
            } else {
                LinkedList<Element> objectClass = getChildrenByTagName(objects.getFirst(), "objectClass");
                if (objectClass.size() > 1) {
                    throw new IOException("SOM contains multiple top-level object classes");
                } else if (objectClass.isEmpty()) {
                    logger.info("SOM does not contain any object classes");
                } else {
                    parseObject(objectClass.getFirst(), null);
                }
            }
            
            logger.debug("validating class names...");
            HashSet<String> classNames = new HashSet<String>();
            for (InteractionInfo interaction : interactionClasses) {
                if (!classNames.add(interaction.getName())) {
                    logger.error("interaction class name not unique: " + interaction.getName());
                    throw new IOException("multiple interactions have the same fully quantified class name");
                }
            }
            for (ObjectInfo object : objectClasses) {
                if (!classNames.add(object.getName())) {
                    logger.error("object class name not unique: " + object.getName());
                    throw new IOException("multiple objects have the same fully quantified class name");
                }
            }
        } catch (SAXException e) {
            logger.error("format error in SOM file " + filepath);
            throw new IOException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.warn("exception when closing resource", e);
                }
            }
        }
    }
    
    private void parseInteraction(Element interaction, InteractionInfo parent)
            throws IOException {
        String name = null;
        Sharing sharing = null;
        LinkedList<Element> derivedClasses = new LinkedList<Element>();
        LinkedList<ParameterInfo> parameters = new LinkedList<ParameterInfo>();
        
        logger.debug("on new interaction...");
        Node child = interaction.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                switch (child.getNodeName()) {
                    case "name":
                        if (name != null) {
                            throw new IOException("interaction class defines multiple name tags");
                        }
                        name = child.getTextContent();
                        break;
                    case "sharing":
                        if (sharing != null) {
                            throw new IOException("interaction class defines multiple sharing tags");
                        }
                        sharing = new Sharing(child.getTextContent());
                        break;
                    case "parameter":
                        Element element = (Element)child;

                        LinkedList<Element> nameList = getChildrenByTagName(element, "name");
                        LinkedList<Element> dataTypeList = getChildrenByTagName(element, "dataType");
                        if (nameList.size() != 1 || dataTypeList.size() != 1) {
                            throw new IOException("interaction class has incorrect parameter structure");
                        }

                        String parameterName = nameList.getFirst().getTextContent();
                        String parameterDataType = dataTypeList.getFirst().getTextContent();
                        try {
                            parameters.add(new ParameterInfo(parameterName, parameterDataType));
                        } catch (ObjectModelException e) {
                            throw new IOException(e);
                        }
                        break;
                    case "interactionClass":
                        derivedClasses.add((Element)child);
                        break;
                    default:
                        logger.debug("skipping element " + child.getNodeName());
                }
            }
            child = child.getNextSibling();
        }

        if (name == null) {
            throw new IOException("interaction class defined without a name");
        }
        if (sharing == null) {
            throw new IOException("interaction class defined without a sharing type");
        }
        
        InteractionInfo interactionInfo;
        try {
            interactionInfo = new InteractionInfo(name, sharing, parameters, parent);
        } catch (ObjectModelException e) {
            throw new IOException(e);
        }
        logger.debug("parsed interaction " + interactionInfo.toString());
        
        interactionClasses.add(interactionInfo);
        for (Element derived : derivedClasses) {
            parseInteraction(derived, interactionInfo);
        }
    }
    
    private void parseObject(Element object, ObjectInfo parent)
            throws IOException {
        String name = null;
        LinkedList<Element> derivedClasses = new LinkedList<Element>();
        LinkedList<AttributeInfo> attributes = new LinkedList<AttributeInfo>();

        logger.debug("on new object...");
        Node child = object.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                switch (child.getNodeName()) {
                    case "name":
                        if (name != null) {
                            throw new IOException("object class defines multiple name tags");
                        }
                        name = child.getTextContent();
                        break;
                    case "attribute":
                        Element element = (Element)child;

                        LinkedList<Element> nameList = getChildrenByTagName(element, "name");
                        LinkedList<Element> dataTypeList = getChildrenByTagName(element, "dataType");
                        LinkedList<Element> sharingList = getChildrenByTagName(element, "sharing");
                        if (nameList.size() != 1 || dataTypeList.size() != 1 || sharingList.size() != 1) {
                            throw new IOException("object class has incorrect attribute structure");
                        }

                        String attributeName = nameList.getFirst().getTextContent();
                        String attributeDataType = dataTypeList.getFirst().getTextContent();
                        Sharing attributeSharing = new Sharing(sharingList.getFirst().getTextContent());
                        try {
                            attributes.add(new AttributeInfo(attributeName, attributeDataType, attributeSharing));
                        } catch (ObjectModelException e) {
                            throw new IOException(e);
                        }
                        break;
                    case "objectClass":
                        derivedClasses.add((Element)child);
                        break;
                    default:
                        logger.debug("skipping element " + child.getNodeName());
                }
            }
            child = child.getNextSibling();
        }

        if (name == null) {
            throw new IOException("object class defined without a name");
        }
        
        ObjectInfo objectInfo;
        try {
            objectInfo = new ObjectInfo(name, attributes, parent);
        } catch (ObjectModelException e) {
            throw new IOException(e);
        }
        logger.debug("parsed object " + objectInfo.toString());
        
        objectClasses.add(objectInfo);
        for (Element derived : derivedClasses) {
            parseObject(derived, objectInfo);
        }
    }
    
    private LinkedList<Element> getChildrenByTagName(Element parent, String tagName) {
        LinkedList<Element> children = new LinkedList<Element>();

        Node childNode = parent.getFirstChild();
        while (childNode.getNextSibling() != null) {
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element)childNode;
                if (childElement.getTagName().equals(tagName)) {
                    children.add(childElement);
                }
            }
            childNode = childNode.getNextSibling();
        }
        return children;
    }
}
