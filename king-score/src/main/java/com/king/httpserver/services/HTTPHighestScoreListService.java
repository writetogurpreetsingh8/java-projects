package com.king.httpserver.services;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.king.httpserver.constants.Constant;
import com.king.httpserver.core.DataAccessor;
import com.king.httpserver.request.HTTPRequest;
import com.king.httpserver.response.HTTPResponse;
import com.king.httpserver.services.filter.HTTPStatusCode;

class HTTPHighestScoreListService extends AbstractHTTPService {

	private static final long serialVersionUID = -794711049127072184L;
	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPHighestScoreListService.class);

	// prevent from reflection
	private HTTPHighestScoreListService() throws HTTPServiceInstantiationException {
		if (getInstance() != null) {
			LOGGER.error("HTTPHighestScoreListService is singleton by designed, can't make it clone... ");
			throw new HTTPServiceInstantiationException(new IllegalAccessException(Constant.REQUIRED_SINGLETON));
		}
		LOGGER.debug("HTTPHighestScoreListService is initialized....... ");
	}

	// thread safe implementation by static inner class
	private static class HTTPHighestScoreListServiceSingleton {
		private final static HTTPHighestScoreListService service = new HTTPHighestScoreListService();
	}

	public static HTTPHighestScoreListService getInstance() {
		return HTTPHighestScoreListServiceSingleton.service;
	}

	@Override
	public void doServe(final HTTPRequest httpRequest, final HTTPResponse httpResponse) {

		LOGGER.debug("Invoking.. doServe() method");

		try {
			Integer levelId = Integer.parseInt((String) httpRequest.getParameters().get("levelid"));

			LOGGER.info("levelId:- ({}) received ", levelId);
			/**
			 * checking levelId
			 */
			if (!isLevelValid(levelId, httpResponse)) {
				return;
			}

			// processing highest score
			Map<Integer, Integer> highestScores = DataAccessor.getInstance().retreiveHighestScore(levelId);
			if (highestScores == null) {
				LOGGER.info("Levels aren't found to generate approperiate CSV... ");
				setResponseMessageAndCode(httpResponse, Constant.EMPTY_STRING,
						HTTPStatusCode.SUCCESSFUL_RESPONSE.STATUS_CODE);
			} else {
				// sort the data as descending order but sort by values
				highestScores = highestScores.entrySet().stream()
						.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors
								.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

				setResponseMessageAndCode(httpResponse, prepareCSVData(highestScores),
						HTTPStatusCode.SUCCESSFUL_RESPONSE.STATUS_CODE);

				setResponseHeader(httpResponse, Constant.CONTENT_TYPE_KEY, Constant.CONTENT_TYPE_KEY_VALUE);
				setResponseHeader(httpResponse, Constant.CONTENT_DISPOSITION_KEY, Constant.CONTENT_DISPOSITION_VALUE);
				LOGGER.info("The Request of highest score is servered successfully and generated approperiate CSV... ");
			}

		} catch (NumberFormatException e) {
			LOGGER.error(Constant.INTERNAL_EXCEPTION, e);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR.MESSAGE,
					HTTPStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR.STATUS_CODE);
		} catch (Exception e) {
			LOGGER.error(Constant.INTERNAL_EXCEPTION, e);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR.MESSAGE,
					HTTPStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR.STATUS_CODE);
		}
	}

	private boolean isLevelValid(final Integer levelId, final HTTPResponse httpResponse) {

		boolean flag = true;
		if (levelId < 0) {
			LOGGER.error(Constant.LEVEL_NOT_VALID, levelId);
			setResponseMessageAndCode(httpResponse, HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE,
					HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST.STATUS_CODE);
			flag = false;
		}
		return flag;
	}

	private String prepareCSVData(final Map<Integer, Integer> highestScores) {

		if (!highestScores.isEmpty()) {

			final StringBuilder header = new StringBuilder("UserId=Score");
			final String data = highestScores.entrySet().stream().map(entry -> {
				if (entry.getValue() < 0) {
					return (entry.getKey().toString() + Constant.EQUALS);
				}
				return (entry.getKey().toString() + Constant.EQUALS + entry.getValue().toString());
			}).collect(Collectors.joining(Constant.CTRL));
			header.append(Constant.CTRL).append(data);
			return header.toString();
		} else {
			return Constant.EMPTY_STRING;
		}
	}
}
