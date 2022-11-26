package com.king.httpserver.services.filter;

public enum HTTPStatusCode {

    /* --- CLIENT ERRORS --- */
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_ERROR_405_METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    CLIENT_ERROR_401_UNAUTHORIZED_USER(401,"UnAuthorized User"),
    /* --- SERVER ERRORS --- */
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SUCCESSFUL_RESPONSE(200, "OK");

	public final int STATUS_CODE;
    public final String MESSAGE;

    HTTPStatusCode(int STATUS_CODE, String MESSAGE) {
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }

}
