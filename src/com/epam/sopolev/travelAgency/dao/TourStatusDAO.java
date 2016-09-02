package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.Tour;
import com.epam.sopolev.travelAgency.entity.TourStatus;

public interface TourStatusDAO {
	boolean createTourStatus(String statusName);
	TourStatus getTourStatus(int statusId);
	List<TourStatus> getAlltourStatus ();
	boolean updateTourStatus(TourStatus tourStatus);
	boolean deleteTourStatus(int statusId);
}
