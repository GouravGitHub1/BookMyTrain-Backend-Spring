package com.bookmytrain.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmytrain.model.BookedResponseModel;
import com.bookmytrain.model.BookingModel;
import com.bookmytrain.model.seatBookingModel;
import com.bookmytrain.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	public List<seatBookingModel> fetchAllBookingData(){
		return bookingRepository.findAll();
	}
	
	public BookedResponseModel bookSeat(BookingModel bookingModel) {
		BookedResponseModel response = new BookedResponseModel();
		List<seatBookingModel> seatsData = bookingRepository.findAll();
		int requiredSeats = bookingModel.getSeatsRequired();
				
		int[] seatMatrix = new int[seatsData.size() + 1];
		
		for (seatBookingModel seatData : seatsData) {
			if (seatData.getStatus().equalsIgnoreCase("booked")){
				seatMatrix[seatData.getSeatNumber()] = 1;
			}
		}
		
		System.out.println(seatsData.size());
		
		int tempRowSum = 0;
		int maxAvailable = 0;
		int maxStart = -1;
		boolean flagSeatFound = false;
		int i = 1;
		for (i = 1; i < seatMatrix.length; i++) {
			System.out.print(seatMatrix[i]+" ");
			tempRowSum = tempRowSum + seatMatrix[i];
			
			if (i%7==0 && i>0) {
				tempRowSum = 7 - tempRowSum;
				System.out.print(" ->"+tempRowSum);
				if (tempRowSum>=requiredSeats) {
					flagSeatFound = true;
					System.out.print("found seats from " + (i - 6) +" to " + i);
					break;
				}
				if (maxAvailable<tempRowSum) {
					maxAvailable = tempRowSum;
					maxStart = i - 7;
				}
				tempRowSum = 0;
				System.out.println();
			}
			
		}
		
		if (!flagSeatFound) {
			if (maxAvailable<tempRowSum) {
				maxAvailable = tempRowSum;
				maxStart = i - 3;
			}
			if (maxAvailable>=requiredSeats) {
				flagSeatFound = true;
				System.out.print("found seats from " + maxStart +" to " + (maxStart+requiredSeats));
			}
			else {
				System.out.println("remaining seats " + (requiredSeats - maxAvailable));
			}
		}
		
		
		response.setPnrNumber(java.util.UUID.randomUUID().toString());
		response.setSeatMatrix(seatsData);
		return response;
	}

}
