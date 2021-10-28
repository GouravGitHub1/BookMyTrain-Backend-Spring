package com.bookmytrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmytrain.model.BookedResponseModel;
import com.bookmytrain.model.BookingModel;
import com.bookmytrain.model.seatBookingModel;
import com.bookmytrain.service.BookingService;

@RestController
@RequestMapping("api/")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping(value = "bookseat")
	private BookedResponseModel bookSeat(@RequestBody BookingModel bookingModel) {
		return bookingService.bookSeat(bookingModel);
	}
	
	@GetMapping(value = "bookingdata")
	private List<seatBookingModel> bookingData() {
		System.out.println("bookingData");
		return bookingService.fetchAllBookingData();
	}
	
}
