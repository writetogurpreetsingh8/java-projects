package com.king.httpserver;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.king.httpserver.constants.Constant;
import com.king.httpserver.core.HTTPServerException;
import com.king.httpserver.services.filter.HTTPRequestFilter;
import com.king.httpserver.services.handler.HTTPRequestHandler;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

/**
 * 
 * This is main class would work as a HTTPServer and will run at default port 1010 mention in the application.json file
 *
 */
class HTTPServer {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
	
	public static void main(String[] args) throws IOException  {
		
		LOGGER.info(Constant.HTTP_SERVER_STARTING);

		final ConfigurationManager configManager = ConfigurationManager.getInstance();
		
		try {
				File jarPath = new File(HTTPServer.class.getProtectionDomain().getCodeSource().getLocation().getPath());
				configManager.loadConfiguration(jarPath.getParentFile().getAbsoluteFile()+"/application.json");
		}		
		catch (HTTPConfigurationException e) {
			LOGGER.error(Constant.HTTP_SERVER_CONFIGURATION_EXCEPTION,e);
        }

		try {
			
			final Configuration configuration = configManager.getConfiguration();
			LOGGER.info("Using Port: {} ", configuration.getServerPort());
			LOGGER.info("Using WebContext: {} ",configuration.getWebContext());
			
			final HttpServer server = HttpServer.create(new InetSocketAddress(configuration.getServerPort()), configuration.getBacklog());
			server.setExecutor(Executors.newCachedThreadPool());
			Runtime.getRuntime().addShutdownHook(new Thread(()->{
				server.stop(0);
			}));
			
			final HttpContext context = server.createContext(configuration.getWebContext(), new HTTPRequestHandler()::handle);
			context.getFilters().add(new HTTPRequestFilter());
			server.start();
			LOGGER.info(Constant.HTTP_SERVER_LISTENING);
			
		} catch (IOException e) {
			LOGGER.error(Constant.HTTP_SERVER_INITIALIZATION_EXCEPTION, new HTTPServerException(e));
		}
	}

}
