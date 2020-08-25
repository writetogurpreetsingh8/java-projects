package com.hotel.utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.hotel.entity.BookingRoomEntity;

@Component
public class HotelRoomsPopulater implements ApplicationRunner {

	@Value("#{'${hotel.rooms}'.split(',')}")
	private String[] hotelRooms;
	
	private Map<Integer,BookingRoomEntity> rooms = new HashMap<>();
	
	private void addDefaultRoomIntoHotel() {
		
		Arrays.asList(hotelRooms).stream().forEach(
		
		room ->{
				BookingRoomEntity roomEntity = createRoom.apply(Integer.parseInt(room.trim()));
				rooms.put(Integer.parseInt(room.trim()),roomEntity);
		}		
		);
	}
	
	private Function<Integer,BookingRoomEntity> createRoom = (roomNumber) ->{
		return new BookingRoomEntity(roomNumber,BookingUtility.findFloorNumbers(roomNumber)); 
	};

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		this.addDefaultRoomIntoHotel();
	}

	public Map<Integer, BookingRoomEntity> getRooms() {
		return rooms;
	}
	

}
