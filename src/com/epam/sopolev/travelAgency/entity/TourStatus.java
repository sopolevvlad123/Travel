package com.epam.sopolev.travelAgency.entity;

import java.io.Serializable;

public class TourStatus implements Serializable {
	private int statusId;
	private String statusName;
	
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "TourStatus [statusId=" + statusId + ", statusName=" + statusName + "]";
	}
		
}
