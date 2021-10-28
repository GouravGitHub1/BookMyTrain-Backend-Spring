package com.bookmytrain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookmytrain.model.BookedResponseModel;
import com.bookmytrain.model.BookingModel;
import com.bookmytrain.model.seatBookingModel;

@Service
public interface BookingService {

	public List<seatBookingModel> fetchAllBookingData();
	
	public BookedResponseModel bookSeat(BookingModel bookingModel);

	
}
