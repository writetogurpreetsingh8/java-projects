package com.hotel.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.bean.BookingResponseBean;
import com.hotel.bean.RoomNotAvailableException;
import com.hotel.entity.BookingRoomEntity;

public interface BookingManagerService {
	
	
	/**
	* Return bean having true/false if there is no booking for the given room on the date,
	* otherwise false
	*/
	BookingResponseBean isRoomAvailable(int room, Date date);
	/**
	* Add a booking for the given guest in the given room on the given
	* date. If the room is not available, throw a suitable Exception.
	*/
	BookingResponseBean addBooking(String guest, int room, Date date)throws RoomNotAvailableException,InterruptedException;

	/**
	* Return a bean having list  of all the available room numbers for the given date
	*/
	BookingResponseBean getAvailableRooms(Date date);
	
	/**
	 * make the hotel room free by room number
	 */
	BookingResponseBean makeRoomAvailable(int room,Date date);
	   
  
}