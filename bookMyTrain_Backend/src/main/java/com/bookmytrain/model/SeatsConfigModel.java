package com.bookmytrain.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SeatConfigurations")
public class SeatsConfigModel {
	@Override
	public String toString() {
		return "SeatsConfigModel [_id=" + _id + ", seatsInArow=" + seatsInArow + ", totalSeats=" + totalSeats
				+ ", totalRows=" + totalRows + "]";
	}

	private String _id;

	public SeatsConfigModel() {
		super();
	}

	public SeatsConfigModel(String _id, int seatsInArow, int totalSeats, int totalRows) {
		super();
		this._id = _id;
		this.seatsInArow = seatsInArow;
		this.totalSeats = totalSeats;
		this.totalRows = totalRows;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getSeatsInArow() {
		return seatsInArow;
	}

	public void setSeatsInArow(int seatsInArow) {
		this.seatsInArow = seatsInArow;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	private int seatsInArow;
	private int totalSeats;
	private int totalRows;
}
