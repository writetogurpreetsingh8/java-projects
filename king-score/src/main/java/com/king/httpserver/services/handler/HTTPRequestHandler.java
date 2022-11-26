package com.king.httpserver.services.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.king.httpserver.request.HTTPRequest;
import com.king.httpserver.response.HTTPResponse;
import com.king.httpserver.services.AbstractHTTPService;
import com.king.httpserver.services.HTTPServiceFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * 
 * After Filtering the request flow will land here, the handle() is responsible
 * to handle every request it has HTTPExchange parameter, in this this method
 * retrieving the MAP that has been added in the previous state called
 * HTTPRequestFilter class and on the basis of Request ( either Login request ,
 * POST Score Request or Get Highest Score list ) it will create the appropriate
 * HTTPServiceType instance to get it handled the current request and finally
 * setting the Response into OutputStream of HTTPExchange
 */
public class HTTPRequestHandler implements HttpHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPRequestHandler.class);

	@SuppressWarnings("unchecked")
	@Override
	public void handle(final HttpExchange httpExchange) throws IOException {

		final Map<String, Object> httpRequestParams = (Map<String, Object>) httpExchange.getAttribute("requestParameters");
		
		// retrieving service instance from HTTPServiceFactory on the basis of
		// request type coming-in
		AbstractHTTPService abstractHTTPService = HTTPServiceFactory.getHTTPRequest(httpRequestParams);

		final HTTPRequest httpRequest = new HTTPRequest(httpRequestParams);
		final HTTPResponse httpResponse = new HTTPResponse(httpExchange.getResponseHeaders());
		
		// processing request here by invoking its doServe()
		abstractHTTPService.doServe(httpRequest, httpResponse);

		// output stream of response
		try (final OutputStream os = httpExchange.getResponseBody()) {
			
			httpExchange.sendResponseHeaders(httpResponse.getHttpStatusCode(),
					httpResponse.getHttpResponseMessage().length());
			
			os.write(httpResponse.getHttpResponseMessage().getBytes());
		}
		
		LOGGER.debug("Respose message:- ({}) and code:- ({}) ", httpResponse.getHttpResponseMessage(),
				httpResponse.getHttpStatusCode());
	}

}
