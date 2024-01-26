package com.king.httpserver.config;

import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.httpserver.HTTPConfigurationException;

/**
 * use to load the application.json file and provide the Configuration instance
 * This is singleton but no need to create thread-safe since it will use as read-only once configuration has loaded, 
 */
public class ConfigurationManager{

	private final static Logger LOGGER = LoggerFactory.getLogger(ConfigurationManager.class);

    private static ConfigurationManager configurationManager;
    private Configuration configuration;
    
    // it will invoke before starting http-server after that will be used as a read-only
    // so no need to make it thread-safe
    public static ConfigurationManager getInstance() {
    	if(configurationManager == null) {
    		configurationManager = new ConfigurationManager();
    	}
        return configurationManager;
    }

    /**
     *  Used to load a configuration file by the path provided
     * @throws HTTPConfigurationException 
     */
    public void loadConfiguration(String filePath)  {
    	LOGGER.debug("Loading Server Configuration... ");
        FileReader fileReader = null;
        ObjectMapper myObjectMapper = defaultObjectMapper();
        try {
            fileReader = new FileReader(filePath);
            configuration = myObjectMapper.readValue(fileReader, Configuration.class);
        } catch (IOException e) {
            throw new HTTPConfigurationException(e);
        }
    }
    
    public Configuration getConfiguration() {
    	return this.configuration;
    }
    
    private ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }
}
