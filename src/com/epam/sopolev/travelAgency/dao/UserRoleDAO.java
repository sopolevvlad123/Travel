package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.UserRole;

public interface UserRoleDAO {
	boolean createRole(String roleName);
	UserRole getRole(int roleId);
	List<UserRole> getAllRoles ();
	boolean updateRole(UserRole userRole);
	boolean deleteRole(int roleId);
	

}
