package com.epam.sopolev.travelAgency.entity;

import java.io.Serializable;


public class Tour implements Serializable{
	private int tourId;
	private String tourName;
	private double price;
	private int tourType;
	private int peopleNumber;
	private int hotelType;	
	private int maxDiscount;
	private int currentDiscount;
	private int tourStatus;
	
	

	public int getTourId() {
		return tourId;
	}
	public void setTourId(int tourId) {
		this.tourId = tourId;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTourType() {
		return tourType;
	}
	public void setTourType(int tourType) {
		this.tourType = tourType;
	}
	public int getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	public int getHotelType() {
		return hotelType;
	}
	public void setHotelType(int hotelType) {
		this.hotelType = hotelType;
	}
	public int getMaxDiscount() {
		return maxDiscount;
	}
	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	public int getCurrentDiscount() {
		return currentDiscount;
	}
	public void setCurrentDiscount(int currentDiscount) {
		this.currentDiscount = currentDiscount;
	}
	public int getTourStatus() {
		return tourStatus;
	}
	public void setTourStatus(int tourStatus) {
		this.tourStatus = tourStatus;
	}

	@Override
	public String toString() {
		return "Tour [tourId=" + tourId + ", tourName=" + tourName + ", price=" + price + ", tourType=" + tourType
				+ ", peopleNumber=" + peopleNumber + ", hotelType=" + hotelType + ", maxDiscount=" + maxDiscount
				+ ", currentDiscount=" + currentDiscount + ", tourStatus=" + tourStatus + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
