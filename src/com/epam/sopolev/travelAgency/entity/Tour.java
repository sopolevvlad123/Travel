package com.epam.sopolev.travelAgency.entity;

import java.io.Serializable;


public class Tour implements Serializable{

	private static final long serialVersionUID = -7530711839871916698L;
	
	private long tourId;
	private String tourName;
	private double price;
	private int tourType;
	private int peopleNumber;
	private int hotelType;	
	private int maxDiscount;
	private int dicsountStep = 1;
	private int currentDiscount;
	private int tourStatus;
	
	

	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
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
	public int getDicsountStep() {
		return dicsountStep;
	}
	public void setDicsountStep(int dicsountStep) {
		this.dicsountStep = dicsountStep;
	}
	@Override
	public String toString() {
		return "Tour [tourId=" + tourId + ", tourName=" + tourName + ", price=" + price + ", tourType=" + tourType
				+ ", peopleNumber=" + peopleNumber + ", hotelType=" + hotelType + ", maxDiscount=" + maxDiscount
				+ ", currentDiscount=" + currentDiscount + ", dicsountStep=" + dicsountStep + ", tourStatus="
				+ tourStatus + "]";
	}

	
	
	
	
	
	
	
	
	
	
	

}
