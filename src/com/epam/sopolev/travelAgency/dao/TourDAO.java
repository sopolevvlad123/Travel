package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.HotelType;
import com.epam.sopolev.travelAgency.entity.Tour;

public interface TourDAO {
	void createTour(String tourName, double price, int tourType, int peopleNumber, int hotelType, int maxDiscount,
			int currentDiscount, int tourStatus);
	Tour getTour(int tourId);
	List<Tour> getAllTours ();
	void updateTour(Tour tour);
	void deleteTour(int tourId);
	
}
