package com.hotel.entity;

import java.util.Date;

public class BookingRoomEntity {
	
	private int roomNumber;
	private String guestLastName;
	
	private Date bookingDate;
	
	private int floorNumber;

	
	
	public BookingRoomEntity(int roomNumber,int floor) {
		super();
		this.roomNumber = roomNumber;
		this.floorNumber = floor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getGuestLastName() {
		return guestLastName;
	}

	public void setGuestLastName(String guestLastName) {
		this.guestLastName = guestLastName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	
	
	

}
