package com.bookmytrain.model;

import java.util.List;

public class BookedResponseModel {
	private String pnrNumber;
	private List<Integer> allocatedSeats;
	private List<seatBookingModel> seatMatrix;

	public BookedResponseModel() {
		super();
	}

	public BookedResponseModel(String pnrNumber, List<Integer> allocatedSeats, List<seatBookingModel> seatMatrix) {
		super();
		this.pnrNumber = pnrNumber;
		this.allocatedSeats = allocatedSeats;
		this.seatMatrix = seatMatrix;
	}

	@Override
	public String toString() {
		return "BookedResponseModel [pnrNumber=" + pnrNumber + ", allocatedSeats=" + allocatedSeats + ", seatMatrix="
				+ seatMatrix + "]";
	}

	public String getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public List<Integer> getAllocatedSeats() {
		return allocatedSeats;
	}

	public void setAllocatedSeats(List<Integer> allocatedSeats) {
		this.allocatedSeats = allocatedSeats;
	}

	public List<seatBookingModel> getSeatMatrix() {
		return seatMatrix;
	}

	public void setSeatMatrix(List<seatBookingModel> seatMatrix) {
		this.seatMatrix = seatMatrix;
	}
}
