package com.bookmytrain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bookmytrain.model.SeatBookingModel;

public interface BookingRepository extends MongoRepository<SeatBookingModel, String>{
	
	
	List<SeatBookingModel> findByseatNumber(int seatNumber);
	
	List<SeatBookingModel> findByPnr(String pnr);
	
}
