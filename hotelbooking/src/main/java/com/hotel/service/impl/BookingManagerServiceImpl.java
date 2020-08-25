package com.hotel.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.bean.BookingResponseBean;
import com.hotel.bean.RoomNotAvailableException;
import com.hotel.constant.BookingConstant;
import com.hotel.entity.BookingRoomEntity;
import com.hotel.service.BookingManagerService;
import com.hotel.utility.HotelRoomsPopulater;
import com.hotel.utility.BookingUtility;

@Service
public class BookingManagerServiceImpl implements BookingManagerService {

	@Autowired
	private HotelRoomsPopulater  hotelRoomPopulater;
	
	private static final Lock lock = new ReentrantLock(BookingConstant.FAIR_POLICY_TRUE);
	
	@Override
	public BookingResponseBean isRoomAvailable(int room, Date date) {
		
		/*List<Entry<Integer,RoomEntity>> list = hotelRoomPopulater.getRooms().entrySet().stream().filter(entry->{
			return (entry.getKey() == room) 
					&& (Utility.checkIfCurrentDate(entry.getValue().getBookingDate()));
		}).collect(Collectors.toList());
		*/
		
		BookingResponseBean bean = null;  
		
		if(hotelRoomPopulater.getRooms().containsKey(room)) {
			
			if(!BookingUtility.checkIfCurrentDate(date,hotelRoomPopulater.getRooms().get(room).getBookingDate())) {
				// room not avilable
				bean = new BookingResponseBean(BookingUtility.replaceString.execute(BookingConstant.ROOM_AVAILABLE_FAILURE_MESSAGE,room,
						date), BookingConstant.RESPONSE_CODE_NOT_FOUND, BookingConstant.is_ROOM_AVAILABLE_FAILURE);
			}
			else {
				// room avaliable
				bean = new BookingResponseBean(BookingUtility.replaceString.execute(BookingConstant.ROOM_AVAILABLE_SUCCESS_MESSAGE,room,
						date), BookingConstant.RESPONSE_CODE_SUCCESS, BookingConstant.is_ROOM_AVAILABLE_SUCCESS);
			}
		}
		else {
			// no such room exist in the hotel
			bean = new BookingResponseBean(BookingUtility.noRoomExist.apply(BookingConstant.ROOM_AVAILABLE_ERROR_MESSAGE,room), BookingConstant.RESPONSE_CODE_NOT_FOUND, BookingConstant.is_ROOM_AVAILABLE_FAILURE);
		}
		return bean;
	}

	@Override
	public BookingResponseBean addBooking(String guest, int room, Date date)throws RoomNotAvailableException, InterruptedException{
		
		// making thread safe and acquiring lock here!!!!
		lock.lockInterruptibly();
		
		BookingResponseBean bean = null;  
		
		if(hotelRoomPopulater.getRooms().containsKey(room)) {
			
			if(!BookingUtility.checkIfCurrentDate(date,hotelRoomPopulater.getRooms().get(room).getBookingDate())) {
				// room not available
				//making thread safe and releaseing lock here in case of exception occurred!!!!
				lock.unlock();
				throw new RoomNotAvailableException(BookingUtility.replaceString.execute(BookingConstant.ROOM_AVAILABLE_FAILURE_MESSAGE,room,
						date));
			}
			else {
				// room available and alot to her/him
				BookingRoomEntity roomEntity = hotelRoomPopulater.getRooms().get(room);
				roomEntity.setGuestLastName(guest);
				roomEntity.setBookingDate(date);
				bean = new BookingResponseBean(BookingUtility.replaceString.execute(BookingConstant.ROOM_HAS_BOOKED,room,
						date), BookingConstant.RESPONSE_CODE_SUCCESS, roomEntity);
			}
		}
		else {
			//making thread safe and releaseing lock here in case of exception occurred!!!!
			lock.unlock();
			throw new RoomNotAvailableException(BookingUtility.replaceString.execute(BookingConstant.ROOM_AVAILABLE_ERROR_MESSAGE,room,
					date));
		}
		//making thread safe and releaseing lock here!!!!
		lock.unlock();
		return bean;
	}

	@Override
	public BookingResponseBean getAvailableRooms(Date date) {
		
		List<BookingRoomEntity> list = hotelRoomPopulater.getRooms().values().stream().filter(entry->{
			return (BookingUtility.checkIfCurrentDate(date,entry.getBookingDate()));
		}).collect(Collectors.toList());
		
		BookingResponseBean bean = null;  
		
		if(list.isEmpty()) {
			// no room available for the given date......
			bean = new BookingResponseBean(BookingUtility.replaceRoomAvailablity.apply(BookingConstant.ROOM_NOT_AVAILABLE,date), BookingConstant.RESPONSE_CODE_NOT_FOUND, list);
		}
		else {
			// room(s) available for the given date......
			bean = new BookingResponseBean(BookingUtility.replaceRoomAvailablity.apply(BookingConstant.ROOM_AVAILABLE,date), BookingConstant.RESPONSE_CODE_SUCCESS, list);
		}
		return bean;
	}

	@Override
	public BookingResponseBean makeRoomAvailable(int room,Date date) {
		
		BookingResponseBean bean = null;  
		
		if(hotelRoomPopulater.getRooms().containsKey(room)) {
			
			if(!BookingUtility.checkIfCurrentDate(date,hotelRoomPopulater.getRooms().get(room).getBookingDate())) {
				// forcefully make room available for the given date....
				BookingRoomEntity roomEntity = hotelRoomPopulater.getRooms().get(room);
				roomEntity.setGuestLastName(BookingConstant.EMPTY_STRING);
				roomEntity.setBookingDate(BookingConstant.NULL);
				
				bean = new BookingResponseBean(BookingUtility.replaceString.execute(BookingConstant.ROOM_AVAILABLE_MADE_AVAILABLE,room,
						date), BookingConstant.RESPONSE_CODE_SUCCESS, roomEntity);
			
			}
			else {
				// room is already available for the given date....
				bean = new BookingResponseBean(BookingUtility.replaceString.execute(BookingConstant.ROOM_AVAILABLE_ALREADY_AVAILABLE,room,
						date), BookingConstant.RESPONSE_CODE_SUCCESS, hotelRoomPopulater.getRooms().get(room));
			}
		}
		else {
			// no such room exist in the hotel
		  bean = new BookingResponseBean(BookingUtility.noRoomExist.apply(BookingConstant.ROOM_AVAILABLE_ERROR_MESSAGE,room), BookingConstant.RESPONSE_CODE_NOT_FOUND, BookingConstant.is_ROOM_AVAILABLE_FAILURE);
		}
		return bean;
	}

}
