package com.bookmytrain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.bookmytrain.model.BookedResponseModel;
import com.bookmytrain.model.SeatBookingModel;
import com.bookmytrain.model.SeatsConfigModel;

@Repository
public class BookingRepositoryTemplate {

	@Autowired
	MongoTemplate mongotemplate;

	public List<SeatBookingModel> bookSeats(BookedResponseModel bookedResponseModel, String userName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("seatNumber").in(bookedResponseModel.getAllocatedSeats()));
		
		Update update = new Update();
		update.set("bookedUser", userName);
		update.set("pnr", bookedResponseModel.getPnrNumber());
		update.set("bookingDate", new Date());
		update.set("status", "booked");
		
		mongotemplate.updateMulti(query, update, SeatBookingModel.class);
		
		return mongotemplate.findAll(SeatBookingModel.class);
	}

	public List<SeatBookingModel> fetchSeatsData(List<Integer> seatNumber) {
		Query query = new Query();
		query.addCriteria(Criteria.where("seatNumber").in(seatNumber));

		return mongotemplate.find(query, SeatBookingModel.class);
	}
	
	public SeatsConfigModel fetchConfigs() {
		List<SeatsConfigModel> seatsConfigModels = mongotemplate.findAll(SeatsConfigModel.class);
		return seatsConfigModels.get(0);
	}

}
