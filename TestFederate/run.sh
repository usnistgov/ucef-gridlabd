#!/bin/bash
cd TestFederation-java-federates/TestFederation-impl-java/TestFederate
mvn exec:java -P TestFederate,JavaFed -Dlog4j.configurationFile=conf/log4j2.xml

