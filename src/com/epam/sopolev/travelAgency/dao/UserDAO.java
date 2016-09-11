package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.User;

public interface UserDAO {
	
	boolean createUser(String firstName, String lastName, String email, String password,int role,  boolean bloked, int lang, String roleName);
	
	User getUserById(long userId);
	
	User getUserByEmail(String email);
	
	List<User> getAllUsers();
	
	boolean updateUser(User user);
	
	boolean deleteUser(long userId);
	
	boolean blockUnblockUser(long userId, boolean isBlocked);
	
	

}
