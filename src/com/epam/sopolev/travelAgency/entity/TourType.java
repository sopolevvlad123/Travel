package com.epam.sopolev.travelAgency.entity;

import java.io.Serializable;

public class TourType implements Serializable{
	
	private int typeId;
	private String typeName;
	
	
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "TourType [typeId=" + typeId + ", typeName=" + typeName + "]";
	}
	
	

}
