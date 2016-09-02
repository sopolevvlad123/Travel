package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.HotelType;

public interface HotelTypeDAO {
	boolean createHotelType(String hotelTypeName);
	HotelType getHotelType(int hotelTypeId);
	List<HotelType> getAllHotelTypes ();
	boolean updateHotelType(HotelType hotelType);
	boolean deleteHotelType(int hotelTypeId);

}
