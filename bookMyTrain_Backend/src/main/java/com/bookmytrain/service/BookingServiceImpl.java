package com.bookmytrain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmytrain.model.BookedResponseModel;
import com.bookmytrain.model.BookingException;
import com.bookmytrain.model.BookingModel;
import com.bookmytrain.model.SeatBookingModel;
import com.bookmytrain.repository.BookingRepository;
import com.bookmytrain.repository.BookingRepositoryTemplate;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	BookingRepositoryTemplate bookingRepositoryTemplate;
	
	public List<SeatBookingModel> fetchAllBookingData(){
		return bookingRepository.findAll();
	}
	
	public List<SeatBookingModel> fetchBookingDataByPnr(String pnr){
		return bookingRepository.findByPnr(pnr);
	}
	
	public BookedResponseModel bookSeat(BookingModel bookingModel) throws BookingException{
		BookedResponseModel response = new BookedResponseModel();
		List<SeatBookingModel> seatsData = bookingRepository.findAll();
		int requiredSeats = bookingModel.getSeatsRequired();
				
		int[] seatMatrix = new int[seatsData.size() + 1];
		
		for (SeatBookingModel seatData : seatsData) {
			if (seatData.getStatus().equalsIgnoreCase("booked")){
				seatMatrix[seatData.getSeatNumber()] = 1;
			}
		}
		
//		System.out.println(seatsData.size());
		List<Integer> allocatedSeats = new ArrayList<>();
		
		int tempRowSum = 0;
		boolean flagSeatFound = false;
		int i = 1;
		int[] rowRemainingSeats = new int[12+1];
		int rowIndex = 1;
		int totalRemainingSeats = 0;
		for (i = 1; i < seatMatrix.length; i++) {
//			System.out.print(seatMatrix[i]+" ");
			tempRowSum = tempRowSum + seatMatrix[i];
			if(seatMatrix[i]==0)
				totalRemainingSeats++;
			if (i%7==0 && i>0) {
				tempRowSum = 7 - tempRowSum;
				rowRemainingSeats[rowIndex] = tempRowSum;
				rowIndex++;
//				System.out.print(" ->"+tempRowSum);
				tempRowSum = 0;
//				System.out.println();
			}
			
		}
		
		rowRemainingSeats[rowIndex] = 3 - tempRowSum;
		
//		System.out.println("totalRemainingSeats "+totalRemainingSeats);
		if (totalRemainingSeats<requiredSeats) {
			throw new BookingException("Not enough seats remaining.");
		}
		
		int remainingSeats = 0;
		if (!flagSeatFound) {
				int minDistanceFromRequired = 999;
				int minDistanceRow = -1;
				int maxSeatsInArow = -1;
				int rowWithMaxSeats = -1;
//				System.out.println("\nrowRemainingSeats");
				for (int j = 1; j < rowRemainingSeats.length; j++) {
//					System.out.print(rowRemainingSeats[j] + " ");
					if ((rowRemainingSeats[j] - requiredSeats) >= 0 && (rowRemainingSeats[j] - requiredSeats) < minDistanceFromRequired){
						minDistanceFromRequired = rowRemainingSeats[j] - requiredSeats;
						minDistanceRow = j;
					}
					if (rowRemainingSeats[j] > maxSeatsInArow){
						maxSeatsInArow = rowRemainingSeats[j];
						rowWithMaxSeats = j;
					}
				}

				if (minDistanceRow == -1) {
					minDistanceRow = rowWithMaxSeats;
				}
				
//				System.out.println("minDistanceRow " +minDistanceRow  + " with seats " + rowRemainingSeats[minDistanceRow] +" remaining seats " + (requiredSeats - rowRemainingSeats[minDistanceRow]));
				remainingSeats = requiredSeats - rowRemainingSeats[minDistanceRow];
//					System.out.println("selected row: " + minDistanceRow + " seat: " + (minDistanceRow*7-6));
					i = minDistanceRow*7-6;
					int end = minDistanceRow*7;
					if (end > 80)
						end = 80;
					while(requiredSeats!=0) {
						if (seatMatrix[i]==0) {
							allocatedSeats.add(i);
							requiredSeats--;
						}
						i++;
						if (i >end)
							break;
						}
//					System.out.println("allocatedSeats "+allocatedSeats);
			}
		
//		System.out.println("remainingSeats "+remainingSeats);
		if (remainingSeats>0) {
			int startSeat = allocatedSeats.get(0);
			int endSeat = allocatedSeats.get(allocatedSeats.size()-1);
//			System.out.println(startSeat +" "+endSeat);
			
//			for (int j : seatMatrix) {
//				System.out.print(j);
//			}
//			System.out.println();
			int distance = 1;
			while (remainingSeats>0) {
				if (startSeat - distance > 0 && seatMatrix[startSeat - distance]==0) {
//					System.out.println(startSeat - distance + " " +seatMatrix[startSeat - distance]);
					allocatedSeats.add(startSeat - distance);
					remainingSeats--;
				}
				if (remainingSeats<=0)
					break;
				if (endSeat + distance < 81 && seatMatrix[endSeat + distance]==0) {
//					System.out.println(endSeat + distance + " " +seatMatrix[endSeat + distance]);
					allocatedSeats.add(endSeat + distance);
					remainingSeats--;
				}
				distance++;
			}
		}
		
//		System.out.println("allocatedSeats "+allocatedSeats);
//		System.out.println(bookingRepositoryTemplate.fetchSeatsData(allocatedSeats));
		
		response.setPnrNumber(java.util.UUID.randomUUID().toString());
		response.setSeatMatrix(seatsData);
		response.setAllocatedSeats(allocatedSeats);
		response.setSeatMatrix(bookingRepositoryTemplate.bookSeats(response, bookingModel.getUserName()));
		
		return response;
	}

}
