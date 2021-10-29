package com.bookmytrain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookmytrain.model.BookedResponseModel;
import com.bookmytrain.model.BookingException;
import com.bookmytrain.model.BookingModel;
import com.bookmytrain.model.SeatBookingModel;

@Service
public interface BookingService {

	public List<SeatBookingModel> fetchAllBookingData();
	
	public List<SeatBookingModel> fetchBookingDataByPnr(String pnr);
	
	public BookedResponseModel bookSeat(BookingModel bookingModel) throws BookingException;

	
}
