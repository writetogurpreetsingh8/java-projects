package com.hotel.constant;

import java.util.Date;

public interface BookingConstant {
	
	String YYYY_MM_DD = "yyyy-MM-dd";
	int RESPONSE_CODE_SUCCESS = 200;
	int RESPONSE_CODE_NOT_FOUND = 404;
	int _ONE = 1;
	int _TWO = 2;
	int _ZERO = 0;
	boolean is_ROOM_AVAILABLE_SUCCESS = true;
	boolean is_ROOM_AVAILABLE_FAILURE = false;
	String ROOM_AVAILABLE_SUCCESS_MESSAGE = " Yup! Room Nummber <room_number> is available for the date of <date> !!";
	String ROOM_AVAILABLE_FAILURE_MESSAGE = " Nope! Room Nummber <room_number> isn't available for the date of <date> !!";
	String ROOM_AVAILABLE_ERROR_MESSAGE = "There is no such room number <room_number> exist in the hotel !!";
	String ROOM_AVAILABLE_ALREADY_AVAILABLE = "The room <room_number> is already available for the date of <date> !!";
	String ROOM_AVAILABLE_MADE_AVAILABLE = "The room <room_number> has been made available for the date of <date> !!";
	String REPLACEMENT_STR = "<room_number>";
	String REPLACE_DATE = "<date>";
	String ROOM_NOT_AVAILABLE = "Room(s) isn't/aren't available for the date of <date> !!";
	String ROOM_AVAILABLE = "Room(s) are available for the date of <date> !!";
	String ROOM_HAS_BOOKED = "The room number <room_number> for the date of <date> has been booked successfully !!";
	boolean FAIR_POLICY_TRUE = true;
	String EMPTY_STRING = "";
	Date NULL = null;
	String EXCEPTION_OCCURRED = "Exception occurred !!";
	String PAST_DATE_NOT_ACCEPTABLE = "Past date <date> is not acceptable here !!";
	String NULL_DATE_NOT_ACCEPTABLE = "Null as a date is not acceptable !!";
}
