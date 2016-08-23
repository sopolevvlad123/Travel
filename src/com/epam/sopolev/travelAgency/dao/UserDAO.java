package com.epam.sopolev.travelAgency.dao;

import java.util.List;

import com.epam.sopolev.travelAgency.entity.User;

public interface UserDAO {
	void createUser(String firstName, String lastName, String email, String password,int role,  boolean bloked);
	User getUser(int userId);
	List<User> getAllUsers();
	void updateUser(User user);
	void deleteUser(int userId);
	

}
