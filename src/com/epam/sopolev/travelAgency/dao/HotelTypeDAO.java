package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.HotelType;

public interface HotelTypeDAO {
	void createHotelType(String hotelTypeName);
	HotelType getHotelType(int hotelTypeId);
	List<HotelType> getAllHotelTypes ();
	void updateHotelType(HotelType hotelType);
	void deleteHotelType(int hotelTypeId);

}
