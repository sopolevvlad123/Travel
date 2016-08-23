package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.UserRole;

public interface UserRoleDAO {
	void createRole(String roleName);
	UserRole getRole(int roleId);
	List<UserRole> getAllRoles ();
	void updateRole(UserRole userRole);
	void deleteRole(int roleId);
	

}
