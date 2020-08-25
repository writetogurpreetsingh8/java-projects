package com.hotel.utility;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.hotel.bean.PastDateNotAcceptableException;
import com.hotel.constant.BookingConstant;

public abstract class BookingUtility {
    
	private static final DateTimeFormatter format = DateTimeFormatter.ofPattern(BookingConstant.YYYY_MM_DD);

	public static Date convertStringIntoDate(String bookingDate)throws PastDateNotAcceptableException {
		
		return isPresentOrFutureDate(Date.from(LocalDate.parse(bookingDate, format).atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}
	
	public static boolean checkIfCurrentDate(Date arguDate,Date roomDate) {
		
		if(roomDate!= null) {
			return roomDate.compareTo(arguDate) == BookingConstant._ZERO ? false : true;
		}
		return true;
		
	}
	
	public static TernaryFunction<String,Integer,Date,String> replaceString = (value,replacementString1,replacementString2) -> {
		
		return value.replace(BookingConstant.REPLACEMENT_STR, String.valueOf(replacementString1))
				.replace(BookingConstant.REPLACE_DATE, convertDateIntoString(replacementString2));
	};
	
	public static BiFunction<String,Integer, String> noRoomExist = (value,roomNumber) -> {
		
		return value.replace(BookingConstant.REPLACEMENT_STR, String.valueOf(roomNumber));
	};
	
	public static BiFunction<String,Date, String> replaceRoomAvailablity = (value,date) -> {
		
		return value.replace(BookingConstant.REPLACE_DATE, convertDateIntoString(date));
	};
	
	public static int findFloorNumbers(int roomNumber) {
		return roomNumber % BookingConstant._TWO == BookingConstant._ZERO ? BookingConstant._ONE : BookingConstant._TWO;
	}
	
	public static String convertDateIntoString(Date date) {
		
		Instant instant =  date.toInstant();
		LocalDate ld = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		return ld.format(format);
	}
	
	public static List<String> fromBindingErrors(BindingResult result) {
		List<String> validErrors = new ArrayList<String>();
		for (ObjectError objectError : result.getAllErrors()) {
			validErrors.add(objectError.getDefaultMessage());
		}
		return validErrors;
	}
	
	private static Date isPresentOrFutureDate(Date date)throws PastDateNotAcceptableException {
		
		if(date == null) {
			throw new PastDateNotAcceptableException(BookingConstant.NULL_DATE_NOT_ACCEPTABLE);
		}
		Date current = Date.from(LocalDate.parse(LocalDate.now().toString(), format).atStartOfDay(ZoneId.systemDefault()).toInstant());
		int value = date.compareTo(current);
		if(value < 0) {
			throw new PastDateNotAcceptableException(replaceRoomAvailablity.apply(BookingConstant.PAST_DATE_NOT_ACCEPTABLE,date));
		}
		return date;
	}
	
	@FunctionalInterface
	public interface TernaryFunction<L,O,I,K>{
		K execute(L l,O o,I i);
	}

}
