package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.TourType;


public interface TourTypeDAO {
	boolean createTourType(String tourTypeName);
	TourType getTourType(int tourTypeId);
	List<TourType> getAllTourTypes ();
	boolean updateTourType(TourType tourType);
	boolean deleteTourType(int tourTypeId);
}
