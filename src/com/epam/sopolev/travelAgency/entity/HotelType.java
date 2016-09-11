package com.epam.sopolev.travelAgency.entity;


public class HotelType  {
	private int hotelTypeId;
	private String hotelTypeName;
	
	
	public int getHotelTypeId() {
		return hotelTypeId;
	}
	public void setHotelTypeId(int hotelTypeId) {
		this.hotelTypeId = hotelTypeId;
	}
	public String getHotelTypeName() {
		return hotelTypeName;
	}
	public void setHotelTypeName(String hotelTypeName) {
		this.hotelTypeName = hotelTypeName;
	}
	@Override
	public String toString() {
		return "HotelType [hotelTypeId=" + hotelTypeId + ", hotelTypeName=" + hotelTypeName + "]";
	}
	
}
