package com.king.httpserver.services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.king.httpserver.services.createscore.HTTPCreateScoreService;
import com.king.httpserver.services.getscore.HTTPHighestScoreListService;
import com.king.httpserver.services.login.HTTPLoginService;

/**
 * HTTPServiceFactory based on Factory design pattern to create the
 * HTTPService's instance i.e HTTPScoreService, HTTPCreateScoreService, HTTPHighestScoreListService & HTTPBadService etc
 * by using their appropriate singleton static methods
 *
 */
public abstract class HTTPServiceFactory {

	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPServiceFactory.class);
	
	private HTTPServiceFactory(){}
	
	public static AbstractHTTPService getHTTPRequest(final Map<String, Object> httpRequestParams)  {

		final String requestType = (String) httpRequestParams.get("request");
		AbstractHTTPService abstractHTTPService = null;
		
		if(HTTPServiceType.LOGIN_SERVICE.name.equalsIgnoreCase(requestType)) {
			LOGGER.info("Login Service to create the User....");
			// creating instance of Login Service
			abstractHTTPService = HTTPLoginService.getInstance();
		}
		else if (HTTPServiceType.SCORE_SERVICE.name.equalsIgnoreCase(requestType)) {
			LOGGER.info("Score service to POST the Scores...."); 
			// creating instance of Create Score Service
			abstractHTTPService = HTTPCreateScoreService.getInstance();
		}
		else if (HTTPServiceType.HIGEST_SCORE_LIST_SERVICE.name.equalsIgnoreCase(requestType)) {
			LOGGER.info("Get higest score service....."); 
			// creating instance of Getting Highest Score Service
			abstractHTTPService = HTTPHighestScoreListService.getInstance();
		}
		else {
			LOGGER.info("Bad Request...");
			// BadRequest
			abstractHTTPService = HTTPBadService.getInstance();
		}
		return abstractHTTPService;
	}

}
