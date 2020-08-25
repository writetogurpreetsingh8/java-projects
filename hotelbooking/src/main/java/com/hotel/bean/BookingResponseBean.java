package com.hotel.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * response bean
 *
 */
@ApiModel(value = "Response data",description = "Hold the response data for hotel booking api!")
public class BookingResponseBean {
	
	@ApiModelProperty(name = "Response message",value = "It holds the response message!")
	private String responseMessage;
	
	@ApiModelProperty(name = "Response code",value = "It holds the response code!")
	private int responseCode;

	@ApiModelProperty(name = "Response date",value = "It holds the response data!")
	private Object resposeData;

	public BookingResponseBean(String responseMessage, int responseCode, Object resposeData) {
		super();
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
		this.resposeData = resposeData;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public Object getResposeData() {
		return resposeData;
	}

	public void setResposeData(Object resposeData) {
		this.resposeData = resposeData;
	}
	
	
	
	

}
