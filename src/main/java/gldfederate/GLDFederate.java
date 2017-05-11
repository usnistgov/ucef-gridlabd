package gldfederate;

import gldfederate.exception.HTTPClientException;
import gldfederate.exception.InvalidDateFormat;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class GLDFederate {    
    private static final Logger logger = LogManager.getLogger();
    
    private Configuration configuration;
    private SimpleDateFormat dateFormat;
    final private HTTPClient client;
    private Process gridlabd;
    
    public GLDFederate(String filepath)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); 
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        logger.info("reading GridLAB-D federate configuration from " + filepath);
        try {
            configuration = mapper.readValue(new File(filepath), Configuration.class);
        } catch (JsonParseException e) {
            logger.error("invalid format for YAML configuration file " + filepath);
            throw new IOException(e);
        } catch (JsonMappingException e) {
            logger.error("invalid options in YAML configuration file " + filepath);
            throw new IOException(e);
        }
        
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        client = new HTTPClient("localhost:6267");
    }
    
    private long dateToUnixTime(String date)
            throws InvalidDateFormat {
        try {
            return dateFormat.parse(date).getTime()/1000;
        } catch (ParseException e) {
            logger.error("unable to parse date using format " + dateFormat.toPattern());
            throw new InvalidDateFormat(e);
        }
    }
    
    private String unixTimeToDate(long unixTime) {
        return dateFormat.format(new Date(unixTime*1000));
    }
    
    private void runGLD()
            throws IOException {
        String stopTime = configuration.getTimeStop() < 0 ? "NEVER" : Long.toString(configuration.getTimeStop());
        String initTime = unixTimeToDate(configuration.getTimeInit());
        
        logger.debug("creating the GridLAB-D process builder");
        ProcessBuilder builder = new ProcessBuilder();
        builder.inheritIO(); // maybe replace
        builder.directory(new File(configuration.getWorkingDirectory()));
        builder.command(
                "gridlabd",
                configuration.getModelFilepath(),
                "--server",
                "--define",
                "starttime=" + initTime,
                "--define",
                "stoptime=" + stopTime,
                "--define",
                "pauseat=" + initTime
                );
        logger.debug("command   = " + Arrays.toString(builder.command().toArray()));
        logger.debug("directory = " + configuration.getWorkingDirectory());
        
        logger.info("launching the GridLAB-D process");
        gridlabd = builder.start();
        
        // this will handle SIGINT; pkill java will be required for other halt conditions 
        logger.info("registering shutdown hook");
        Thread gldShutdown = new Thread() {
            public void run() {
                try {
                    client.get("/control/shutdown");
                    logger.info("sent shutdown command to GridLAB-D");
                } catch (HTTPClientException e) {
                    logger.info("destroying the GridLAB-D process");
                    gridlabd.destroy();
                }
            }
        };
        Runtime.getRuntime().addShutdownHook(gldShutdown);
    }
    
    private void initGLD()
            throws InvalidDateFormat,
                   InterruptedException,
                   HTTPClientException {
        // need a small delay before control can be issued; need to improve how we do this
        int attempt = 1;
        boolean connected = false;
        while (!connected) {
            try {
                logger.info("trying to connect to GridLAB-D (" + attempt + ")");
                advanceTime(configuration.getTimeStart());
                connected = true;
            } catch (HTTPClientException e) {
                // should check process exit value to see if still running
                if (attempt == configuration.getMaxConnectionAttempts()) {
                    throw e;
                }
                final int delay = configuration.getWaitReconnectMs();
                logger.warn("connection to GridLAB-D server failed; retry in " + delay + " ms");
                Thread.sleep(delay);
                attempt += 1;
            }
        }
    }
    
    private void advanceTime(long unixTime)
            throws HTTPClientException,
                   InvalidDateFormat,
                   InterruptedException {
        String date = unixTimeToDate(unixTime);
        logger.info("advancing GridLAB-D time to " + date);
        // URLEncoder.encode(date, "UTF-8") produces characters GLD does not recognize
        client.get("/control/pauseat=" + date.replaceAll(" ", "%20"));
        
        boolean advancing = true;
        while (advancing) {
            String currentDate = client.get("/raw/clock");
            logger.debug("clock = " + currentDate);
            long currentUnixTime = dateToUnixTime(currentDate);
            if (currentUnixTime < unixTime) {
                logger.debug("waiting " + configuration.getWaitAdvanceTimeMs() + " ms for GridLAB-D clock to advance");
                Thread.sleep(configuration.getWaitAdvanceTimeMs());
            } else {
                advancing = false;
            }
        }
    }
    
    private void execute()
            throws HTTPClientException,
                   InvalidDateFormat,
                   InterruptedException {
        // how do we handle time zones in GLD?
        // server_portnumber=X can be used to set the port; no guarantee when this port is in use
        // server_quit_on_close=1 can be used for clean exits if the client connection is recycled
        try {
            runGLD();
        } catch (IOException e) {
            logger.error("failed to launch GridLAB-D", e);
        }
        initGLD();
        
        long simulationTime = configuration.getTimeInit();
        boolean terminate = false;
        while (!terminate) {
            try {
                int code = gridlabd.exitValue();
                logger.info("GridLAB-D done with exit value = " + code);
                terminate = true;
            } catch (IllegalThreadStateException e) {
                int step = configuration.getLogicalTimeStep() * configuration.getSimulationTimeStep();
                advanceTime(simulationTime + step);
                simulationTime += step;
                
                String houseXML = client.get("/xml/F1_house_B7/*");
                logger.info("house data = " + houseXML);
            }
        }
    }

    public static void main(String args[]) {
        if (args.length != 1) {
            logger.error("command line argument for configuration file not specified");
            System.exit(1);
        }
        
        try {
            GLDFederate gldfed = new GLDFederate(args[0]);
            gldfed.execute();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
