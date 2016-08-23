package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.TourType;


public interface TourTypeDAO {
	void createTourType(String tourTypeName);
	TourType getTourType(int tourTypeId);
	List<TourType> getAllTourTypes ();
	void updateTourType(TourType tourType);
	void deleteTourType(int tourTypeId);
}
