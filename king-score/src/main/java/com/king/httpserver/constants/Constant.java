package com.king.httpserver.constants;

public interface Constant {

	String HTTP_SERVER_INITIALIZATION_EXCEPTION = "An exception occurred, while initialzing Http Server ";
	String HTTP_SERVER_CONFIGURATION_EXCEPTION = "An exception occurred, while loading Http-Server configuration ";
	String HTTP_SERVER_LISTENING = "Http Server has been started, Read to server........";
	String HTTP_SERVER_STARTING= "Http Server Starting up..........";
	String UTF_8 = "UTF-8";
	int _THREE = 3;
	int _TWO = 2;
	int _ZERO = 0;
	String USER_ID_NOT_VALID = "User Id must be unsigned integer number ({}), ";
	String INTERNAL_EXCEPTION = "An exception ocurred, while user trying to login ";
	String LEVEL_NOT_VALID = "User Level must be unsigned number greater than 0 ({}) ";
	String SCORE_NOT_VALID = "User Score must be unsigned number greater than 0 ({}) ";
	String SESSION_ID_EXPIRED = "Session id ({}) has been expired, Please create new session Id ";
	String SESSION_ID_NOT_VALID = "Session id ({}) cann't be null, Please valid Session Id ";
	String SESSION_ID_NOT_FOUND = "Session not found while trying to fetch it againts ({}) id ";
	String REQUIRED_SINGLETON = "This requires singleton";
	String CLONE_NOT_SUPPORTED = "Clone not supported";
	String CONTENT_TYPE_KEY = "Content-Type";
	String CONTENT_TYPE_KEY_VALUE = "text/csv";
	String CONTENT_DISPOSITION_KEY = "Content-Disposition";
	String CONTENT_DISPOSITION_VALUE = "attachment;filename=highestScoreCsv.csv";
	String EMPTY_STRING = "";
	String EQUALS = "=";
	String CTRL = "\r\n";
}
