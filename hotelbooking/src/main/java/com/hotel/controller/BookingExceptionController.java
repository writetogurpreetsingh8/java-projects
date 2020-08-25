package com.hotel.controller;

import java.time.format.DateTimeParseException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotel.bean.BookingResponseBean;
import com.hotel.bean.PastDateNotAcceptableException;
import com.hotel.bean.RoomNotAvailableException;
import com.hotel.constant.BookingConstant;
import com.hotel.utility.BookingUtility;

@RestControllerAdvice
public class BookingExceptionController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BookingResponseBean> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
	 	
	 	BookingResponseBean bookingResponseBean = new BookingResponseBean(exception.getMessage(),
	 			HttpStatus.BAD_REQUEST.value(),BookingUtility.fromBindingErrors(exception.getBindingResult()));
	 	
        return new ResponseEntity<BookingResponseBean>(bookingResponseBean, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BookingResponseBean> httpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException exception) {
	 	
	 	BookingResponseBean bookingResponseBean = new BookingResponseBean(exception.getMessage(),
	 			HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST);
	 	
        return new ResponseEntity<BookingResponseBean>(bookingResponseBean, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value= {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<BookingResponseBean> constraintViolationException(ConstraintViolationException exception){
    
		BookingResponseBean bookingResponseBean = new BookingResponseBean(exception.getMessage(),
	 			HttpStatus.CONFLICT.value(),BookingConstant.EXCEPTION_OCCURRED);
		
        return new ResponseEntity<BookingResponseBean>(bookingResponseBean, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(value= {RoomNotAvailableException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BookingResponseBean> roomNotAvailableException(RoomNotAvailableException exception){
    
		BookingResponseBean bookingResponseBean = new BookingResponseBean(exception.getMessage(),
	 			HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
	 
        return new ResponseEntity<BookingResponseBean>(bookingResponseBean, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value= {InterruptedException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BookingResponseBean> interruptedException(InterruptedException exception){
    
		BookingResponseBean bookingResponseBean = new BookingResponseBean(exception.getMessage(),
	 			HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR);
	 
        return new ResponseEntity<BookingResponseBean>(bookingResponseBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(value= {DateTimeParseException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BookingResponseBean> dateTimeParseException(DateTimeParseException exception){
    
		BookingResponseBean bookingResponseBean = new BookingResponseBean(exception.getMessage(),
	 			HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR);
	 
        return new ResponseEntity<BookingResponseBean>(bookingResponseBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(PastDateNotAcceptableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<BookingResponseBean> pastDateNotAcceptableException(PastDateNotAcceptableException exception) {
	 	
	 	BookingResponseBean bookingResponseBean = new BookingResponseBean(exception.getMessage(),
	 			HttpStatus.BAD_REQUEST.value(),HttpStatus.NOT_ACCEPTABLE);
	 	
        return new ResponseEntity<BookingResponseBean>(bookingResponseBean, HttpStatus.BAD_REQUEST);
    }

}
