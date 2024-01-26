package com.trade.fare.contant;

public interface TradeConstant {
	
	String COMMA = ",";
	String EQUAL_OPERATOR = " = ";
	String EMPTY = "";
	String OPEN_SQUARE_BRACKET = "\\[";
	String CLOSE_SQUARE_BRACKET = "]";
	String PERCENTAGE = " % ";
	String SYSTEM_INITIALIZED_MESSAGE = "Trade System has been initialized..............., Read to Receive User Input as Trade Attributes!!";
	String HOW_TO_STOP_SYSTEM_MESSAGE = "Enter \"exit\" to stop the Trade System!!";
	String SYSTEM_TRADE_DATA_FORMAT_MESSAGE = "[amount = <trade_amount_into_numbers>, trade_ref = \"<trade_ref>\", location = \"<trade_location>\", first_party = \"<trade_first_party>\", second_party = \"<trade_second_party>\", product = \"<trade_product>\"]";
	String SYSTEM_INFO_MESSAGE = "Enter the Trade Input as following Sequence & Pattern only, to let the system behave expected!!";
	String SYSTEM_EXIT_MESSAGE = "Trade System Exiting..........!!";
	
	String SYSTEM_MATCHING_MESSAGE = "Based on matching criteria, Trade_<no1> matches Trade_<no2> and all fields matches. Hence matching percentage is 100 %. "
			+ "\r\n Trade_<no1> & Trade_<no2> are removed from system and no longer participates in matching.";
	
	String SYSTEM_NOT_MATCHING_MESSAGE = "Based on matching criteria, Trade_<no1> matches Trade_<no2> and matching percentage is calculated as follows - \r\n" + 
			"matching fields :<matching_fields> \r\n" + 
			"Matching percentage = <matching_percentage>%\r\n";
	
	String TRADE_AVAILABLE_MESSAGE = "Now available Trade's are following:-";
	String TRADE_NEW_DATA_MESSAGE = "Enter New Trade Data in below Sequene & Pattern only:-";
	String TRADE_NO_1 = "<no1>";
	String TRADE_NO_2 = "<no2>";
	String TRADE_MATCHING_PERCENTAGE = "<matching_percentage>";
	String TRADE_MATCHING_FIELDS = "<matching_fields>";
	String TRADE_FIRST_PARTY = "first_party 10%, ";
	String TRADE_SECOND_PARTY = "second_party = 10%, ";
	String TRADE_AMOUNT = "amount = 25%, ";
	String TRADE_PRODUCT = "product = 20%,";
	String TRADE_REF = "trade_ref = 35%";
	String TRADE_NO = "Trade_";
	String TRADE_COUNT = "Trade Count is/are:- ";
	int _ZERO = 0;
	int _ONE = 1;
	int _TWO = 2;
	int _THREE = 3;
	int _FOUR = 4;
	int _FIVE = 5;
	int _HUNDRED = 100;
	
	// below values would be treated as percentage
	int _TRADE_FIRST_PARTY_PERCENTAGE = 10;
	int _TRADE_SECOND_PARTY_PERCENTAGE = 10;
	int _TRADE_PRODUCT_PERCENTAGE = 20;
	int _TRADE_AMOUNT_PERCENTAGE = 25;
	int _TRADE_REF_PERCENTAGE = 35;
	

}
