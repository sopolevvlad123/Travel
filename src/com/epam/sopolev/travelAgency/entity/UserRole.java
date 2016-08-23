package com.epam.sopolev.travelAgency.entity;

import java.io.Serializable;

public class UserRole  implements Serializable{

	private int roleId;
	private String roleName;
	
	
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
}
