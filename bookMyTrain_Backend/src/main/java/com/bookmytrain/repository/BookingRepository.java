package com.bookmytrain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bookmytrain.model.seatBookingModel;

public interface BookingRepository extends MongoRepository<seatBookingModel, String>{

}
