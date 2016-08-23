package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.Tour;
import com.epam.sopolev.travelAgency.entity.TourStatus;

public interface TourStatusDAO {
	void createTourStatus(String statusName);
	TourStatus getTourStatus(int statusId);
	List<TourStatus> getAlltourStatus ();
	void updateTourStatus(TourStatus tourStatus);
	void deleteTourStatus(int statusId);
}
