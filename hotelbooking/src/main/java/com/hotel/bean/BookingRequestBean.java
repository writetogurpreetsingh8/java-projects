package com.hotel.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * request bean
 *
 */
@ApiModel(value = "Request Parameters",description = "Hold the request parameters for hotel booking api!")
public class BookingRequestBean {
	
	@ApiModelProperty(name = "Room number",value = "room number must have positive integer and greater than 0")
	@Min(value = 1L,message="room number must have positive integer and greater than 0")
	private int hotelRoomNumber;
	
	@ApiModelProperty(name = "Guest last name",value = "Guest last name must contains characters only!")
	@NotNull(message = "guest last name is mandatory!") 
	@NotBlank(message = "guest last name can't be blank!")
	@Pattern(regexp = "[a-zA-Z]*",message = "guest last name must contains characters only!")
	private String guestLastName;
	
	
	@ApiModelProperty(name = "Booking date",value = "Booking Date is mandatory and must be present/future in yyyy-MM-dd format only!")
	@NotNull(message = "booking date is mandatory!") 
	@NotBlank(message = "booking date can't be blank!")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "Booking Date must be in yyyy-MM-dd format only!")
	private String bookingDate;

	public int getHotelRoomNumber() {
		return hotelRoomNumber;
	}

	public void setHotelRoomNumber(int hotelRoomNumber) {
		this.hotelRoomNumber = hotelRoomNumber;
	}

	public String getGuestLastName() {
		return guestLastName;
	}

	public void setGuestLastName(String guestLastName) {
		this.guestLastName = guestLastName;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	

    
}
