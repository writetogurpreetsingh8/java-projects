package com.hotel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.bean.BookingRequestBean;
import com.hotel.bean.BookingResponseBean;
import com.hotel.bean.PastDateNotAcceptableException;
import com.hotel.bean.RoomNotAvailableException;
import com.hotel.constant.BookingConstant;
import com.hotel.service.BookingManagerService;
import com.hotel.utility.BookingUtility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Booking Controller",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class BookingController {

    @Autowired
    private BookingManagerService hotelBookingService;

    @ApiOperation(value ="Check whether room is available or not!!",
			produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.TEXT_PLAIN_VALUE,response = BookingResponseBean.class)
    @GetMapping("/rooms/{roomNumber}/{date}")
    public ResponseEntity<BookingResponseBean> isRoomAvailable(@ApiParam(name = "roomNumber",type = "string",value = "Any non-zero positive integer value",example = "101",required = true)
    @PathVariable("roomNumber") int roomNumber, @ApiParam(name = "date",type = "string",value = "Booking Date is mandatory and must be present/future in yyyy-MM-dd format only!",example = "2020-12-22",required = true)
    @PathVariable("date") String date) throws PastDateNotAcceptableException {
        
    	BookingResponseBean bean = hotelBookingService.isRoomAvailable(roomNumber,BookingUtility.convertStringIntoDate(date));
    	
    	return (bean.getResponseCode() == BookingConstant.RESPONSE_CODE_SUCCESS) ? 
   			 ResponseEntity.ok(bean) :  new ResponseEntity<BookingResponseBean>(bean, HttpStatus.NOT_FOUND);
    }
    
    @ApiOperation(value ="Fetch available rooms!!",
			produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.TEXT_PLAIN_VALUE,response = BookingResponseBean.class)
    @GetMapping("/rooms/{date}")
    public ResponseEntity<BookingResponseBean> getAvailableRooms(@ApiParam(name = "date",type = "string",value = "Booking Date is mandatory and must be present/future in yyyy-MM-dd format only!",example = "2020-12-22",required = true) @PathVariable("date") String date) throws PastDateNotAcceptableException {
    	
    	BookingResponseBean bean = hotelBookingService.getAvailableRooms(BookingUtility.convertStringIntoDate(date));
    			
    	return (bean.getResponseCode() == BookingConstant.RESPONSE_CODE_SUCCESS) ? 
    			 ResponseEntity.ok(bean) :  new ResponseEntity<BookingResponseBean>(bean, HttpStatus.NOT_FOUND);
    }
    
    @ApiOperation(value ="Allot hotel room to the guest!!",
			produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE,response = BookingResponseBean.class)
    @PostMapping("/rooms/room")
    public ResponseEntity<BookingResponseBean> addBooking(@ApiParam(name = "Booking reqeust parameters",type = "BookingRequestBean",value = "Json",example = "{hotelRoomNumber:101,guestLastName:'stark',bookingDate:'2020-12-22'}",required = true) @Valid @RequestBody BookingRequestBean bookingRequestBean)throws RoomNotAvailableException,InterruptedException, PastDateNotAcceptableException {
    	return ResponseEntity.ok(hotelBookingService.addBooking(bookingRequestBean.getGuestLastName(), bookingRequestBean.getHotelRoomNumber(), 
    			BookingUtility.convertStringIntoDate(bookingRequestBean.getBookingDate())));
    }
  
    @ApiOperation(value ="Make the hotel room vacant!!",
			produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.TEXT_PLAIN_VALUE,response = BookingResponseBean.class)
    @PostMapping("/rooms/{roomNumber}/{date}")
    public ResponseEntity<BookingResponseBean> makeRoomAvailable(@ApiParam(name = "roomNumber",type = "string",value = "Any non-zero positive integer value",example = "101",required = true) @PathVariable("roomNumber") int roomNumber,
    		@ApiParam(name = "date",type = "string",value = "Booking Date is mandatory and must be present/future in yyyy-MM-dd format only!",example = "2020-12-22",required = true) @PathVariable("date") String date) throws PastDateNotAcceptableException {
    	
    	
    	BookingResponseBean bean = hotelBookingService.makeRoomAvailable(roomNumber,BookingUtility.convertStringIntoDate(date));
    	
    	return (bean.getResponseCode() == BookingConstant.RESPONSE_CODE_SUCCESS) ? 
   			 ResponseEntity.ok(bean) :  new ResponseEntity<BookingResponseBean>(bean, HttpStatus.NOT_FOUND);
    }
    

}
