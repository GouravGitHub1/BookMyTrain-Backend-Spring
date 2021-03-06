package com.bookmytrain.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seatBooking")
public class SeatBookingModel {
	private String _id;
	private int seatNumber;
	private String status;
	private String bookedUser;
	private Date bookingDate;
	private String pnr;

	public SeatBookingModel(String _id, int seatNumber, String status, String bookedUser, Date bookingDate,
			String pnr) {
		super();
		this._id = _id;
		this.seatNumber = seatNumber;
		this.status = status;
		this.bookedUser = bookedUser;
		this.bookingDate = bookingDate;
		this.pnr = pnr;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public SeatBookingModel() {
	};

	@Override
	public String toString() {
		return "seatBookingModel [_id=" + _id + ", seatNumber=" + seatNumber + ", status=" + status + ", bookedUser="
				+ bookedUser + ", bookingDate=" + bookingDate + "]";
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBookedUser() {
		return bookedUser;
	}

	public void setBookedUser(String bookedUser) {
		this.bookedUser = bookedUser;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
}
