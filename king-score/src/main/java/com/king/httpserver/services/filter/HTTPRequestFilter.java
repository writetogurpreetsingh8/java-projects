package com.king.httpserver.services.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.king.httpserver.constants.Constant;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

/**
 * 
 * This class would work as a filter, each & ever request will be handled
 * by doFilter() of it ,  this class following responsibilities
 * 1) check whether request is GET or POST ( no another method is accepted) 
 * 2) get the data from HTTPExchange and parse it 
 * 3) Identify the type of Request , whether Login Request, POST Score Request or Getting Highest Score list
 * 4) create the Map<String, Object> parameters  and store the Request type and data into it
 * and added in the HTTPExchange instance 
 */
public class HTTPRequestFilter extends Filter{

	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPRequestFilter.class);

	public HTTPRequestFilter(){}
	
	@Override
	public String description() {
		return "Filter Get/Post parameters";
	}

	@Override
	public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {
		processAndValid(httpExchange);
        chain.doFilter(httpExchange);
	}
	
	/**
	 * process and validate request whether GET or POST 
	 */
	private void processAndValid(final HttpExchange exchange) throws IOException  {
		
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final URI requestedUri = exchange.getRequestURI();
		final String query = requestedUri.getRawQuery();   
		
		exchange.setAttribute("requestParameters", parameters);
		
		if (HTTPMethod.GET.name().equalsIgnoreCase(exchange.getRequestMethod())) {
			LOGGER.info("GET Request from {} ",requestedUri);
	        if(query != null) {
	        	parseQuery(query, parameters);
	        }
		}
		else if (HTTPMethod.POST.name().equalsIgnoreCase(exchange.getRequestMethod())) {
			LOGGER.info("POST Request from {} ",requestedUri);
			 parseQuery(query, parameters);
             InputStreamReader isr =
                new InputStreamReader(exchange.getRequestBody(),Constant.UTF_8);
             parameters.put("scores", new BufferedReader(isr).readLine());
			
		}else {
			parameters.put("errorCode", HTTPStatusCode.CLIENT_ERROR_405_METHOD_NOT_ALLOWED.STATUS_CODE);
			parameters.put("errorMessage", HTTPStatusCode.CLIENT_ERROR_405_METHOD_NOT_ALLOWED.MESSAGE);
		}
		parseUrlEncodedParameters(exchange);
	}

    /*
     * Usage: retrieve the URL encoded parameters
     * 
     * Input:
     *    exchange = request/response object
     *    
     */
    private void parseUrlEncodedParameters(HttpExchange exchange)
        throws UnsupportedEncodingException {

        @SuppressWarnings("unchecked")
        Map<String, Object> parameters =
                (Map<String, Object>)exchange.getAttribute("requestParameters");

        String uri = exchange.getRequestURI().toString();
        String[] tokens = uri.split("[/?=]");

        if(tokens.length > Constant._THREE) {
            if(tokens[Constant._THREE].equals("score") || tokens[Constant._THREE].equals("highscorelist")) {
                parameters.put("levelid", tokens[Constant._TWO]);
                parameters.put("request",tokens[Constant._THREE]);
            } else if(tokens[Constant._THREE].equals("login")) {
                parameters.put("userid", tokens[Constant._TWO]);
                parameters.put("request",tokens[Constant._THREE]);
            }
            else {
            	parameters.put("errorCode", HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.STATUS_CODE);
    			parameters.put("errorMessage", HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);
            }
        }
        else {
        	parameters.put("errorCode", HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.STATUS_CODE);
			parameters.put("errorMessage", HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);
        }
    }
    
    /*
     * Usage: parse a query string in the format key=value&key=value and
     *      retrieve the values
     */
    private static void parseQuery(String query, Map<String, Object> parameters)
        throws UnsupportedEncodingException {

    	if (query != null) {
            String pairs[] = query.split("[&]");

            for (String pair : pairs) {
                String param[] = pair.split("[=]");

                String key = null;
                String value = null;
                if (param.length > 0) {
                    // Retrieve the key
                    key = URLDecoder.decode(param[0],
                        System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    // Retrieve the value
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                parameters.put(key.toLowerCase(), value);
            }
        }
    }
}
