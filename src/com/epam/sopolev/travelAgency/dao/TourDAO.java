package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.HotelType;
import com.epam.sopolev.travelAgency.entity.Tour;

public interface TourDAO {
	
	boolean createTour(String tourName, double price, int tourType, int peopleNumber, int hotelType, int maxDiscount, int discountStep,
			int currentDiscount, int tourStatus);
	
	Tour getTourById(long tourId);
	
	Tour getTourByName(String tourName);	
	
	List<Tour> getAllTours ();
	
	List<Tour> getFreeTours();
	
	
	boolean updateTour(Tour tour);
	
	boolean deleteTour(long tourId);
	
}
