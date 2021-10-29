package com.bookmytrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmytrain.model.BookedResponseModel;
import com.bookmytrain.model.BookingException;
import com.bookmytrain.model.BookingModel;
import com.bookmytrain.model.SeatBookingModel;
import com.bookmytrain.service.BookingService;

@RestController
@RequestMapping("api/")
@CrossOrigin
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping(value = "bookseat")
	private BookedResponseModel bookSeat(@RequestBody BookingModel bookingModel) throws BookingException{
		return bookingService.bookSeat(bookingModel);
	}
	
	@GetMapping(value = "bookingdata")
	private List<SeatBookingModel> bookingData() {
		return bookingService.fetchAllBookingData();
	}
	
	@GetMapping(value = "bookingdata/{pnr}")
	private List<SeatBookingModel> bookingDataByPnr(@PathVariable String pnr) {
		return bookingService.fetchBookingDataByPnr(pnr);
	}
	
}
