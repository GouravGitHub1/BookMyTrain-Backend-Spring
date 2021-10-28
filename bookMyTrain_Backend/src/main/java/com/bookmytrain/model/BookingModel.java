package com.bookmytrain.model;

public class BookingModel {
	private String userName;
	private Integer seatsRequired;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSeatsRequired() {
		return seatsRequired;
	}

	public void setSeatsRequired(Integer seatsRequired) {
		this.seatsRequired = seatsRequired;
	}

	@Override
	public String toString() {
		return "BookingModel [userName=" + userName + ", seatsRequired=" + seatsRequired + "]";
	}

	public BookingModel(String userName, Integer seatsRequired) {
		super();
		this.userName = userName;
		this.seatsRequired = seatsRequired;
	}

	public BookingModel() {
		super();
	}
}
