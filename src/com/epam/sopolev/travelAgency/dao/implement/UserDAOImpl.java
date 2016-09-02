package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.sopolev.travelAgency.dao.UserDAO;
import com.epam.sopolev.travelAgency.entity.Tour;
import com.epam.sopolev.travelAgency.entity.User;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;
import com.epam.sopolev.travelAgency.utils.SqlConstants;

public class UserDAOImpl implements UserDAO {
	
	


	@Override
	public boolean createUser(String firstName, String lastName, String email, String password, int role, boolean bloked, int lang) {
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_CREATE_USER)) {
			
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, password);
			statement.setInt(5, role);
			statement.setBoolean(6, bloked);
			statement.setInt(7, lang);
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "create user fail", e); 
			return false;
		}
		
		return true;
	}

	@Override
	public User getUserById(long userId) {
		
		User user = new User();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_BY_ID_USER)) {
			statement.setLong(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
				user = fitUserParams(user, resultSet);					
				}								 
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getById tour fail", e); 
		} 
		
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {
		
	/*	User user = new User();
				
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_BY_EMAIL_USER)) {
			
			statement.setString(1, email);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while (resultSet.next()) {
					
					return fitUserParams(user, resultSet);
				}
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getByEmail user fail", e); 
		} 
		
		return user;*/
User user = new User();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_BY_EMAIL_USER)) {
			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
				user = fitUserParams(user, resultSet);					
				}								 
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getById tour fail", e); 
		} 
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> userList = new ArrayList<>();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM travel_agency.users;")) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while (resultSet.next()) {
						System.out.println("ALL Users");
					User user = new User();
					userList.add(fitUserParams(user, resultSet));

				}
				
				return userList;
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getAll user fail", e); 
		} 
		
		return userList;
	}

	@Override
	public boolean updateUser(User user) {		
		
		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_UPDATE_USER)) {
			
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getRole());
			statement.setBoolean(6, user.isBlocked());
			statement.setLong(7, user.getUserId());
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "update user fail", e); 
			return false;
		} 
		return true;
	}

	@Override
	public boolean deleteUser(long userId) {
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_DELETE_USER)) {
			
			statement.setLong(1, userId);
			statement.execute();

		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "delete user fail", e); 
			return false;
		} 
		
		return true;
	}
	
	@Override
	public boolean blockUnblockUser(long userId, boolean isBlocked) {
		try (Connection connection = ConnectionFactory.getConnection();
				 PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_BLOCK_UNBLOCK_USER)) {
				
				statement.setBoolean(1, isBlocked);				
				statement.setLong(2, userId);
				statement.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
				Logger.getLogger(e.getClass()).log(Level.ERROR, "update user fail", e); 
				return false;
			} 
			return true;
	};

	private User fitUserParams(User user, ResultSet resultSet) throws SQLException {
		
		user.setUserId(resultSet.getLong(SqlConstants.SQL_USER_ID));
		user.setFirstName(resultSet.getString(SqlConstants.SQL_FIRST_NAME));
		user.setLastName(resultSet.getString(SqlConstants.SQL_LAST_NAME));
		user.setEmail(resultSet.getString(SqlConstants.SQL_EMAIL));
		user.setPassword(resultSet.getString(SqlConstants.SQL_PASSWORD));
		user.setRole(resultSet.getInt(SqlConstants.SQL_USER_ROLE));
		user.setBlocked(resultSet.getBoolean(SqlConstants.SQL_BLOCKED));
		user.setLang(resultSet.getInt(SqlConstants.SQL_LANG));
		return user;
	}

}
