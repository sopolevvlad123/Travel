package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.sopolev.travelAgency.dao.UserDAO;
import com.epam.sopolev.travelAgency.entity.AbstractUser;
import com.epam.sopolev.travelAgency.entity.User;
import com.epam.sopolev.travelAgency.entity.UserRole;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;

public class UserDAOImpl implements UserDAO {
	private Connection connection;
	
	private final String CREATE_USER    = "INSERT INTO `travel_agency`.`users` (`first_name`, `last_name`, `email`, `password`, `user_role`, `blocked`) "
											+ "VALUES (?, ?, ?, ?, ?, ?);";
	private final String GET_BY_ID_USER = "SELECT * FROM travel_agency.users WHERE user_id = ?";
	private final String GET_ALL_USERS  = "SELECT * FROM travel_agency.users;";
	private final String UPDATE_USER    = "UPDATE `travel_agency`.`users` SET first_name = ?, last_name = ?, email = ?, password = ?, user_role = ?, blocked = ? WHERE user_id = ?;";
	private final String DELETE_USER    = "DELETE FROM `travel_agency`.`users` WHERE user_id = ? ";

	private final String USER_ID    = "user_id";
	private final String FIRST_NAME = "first_name";
	private final String LAST_NAME  = "last_name";
	private final String EMAIL      = "email";
	private final String PASSWORD   = "password";
	private final String USER_ROLE  = "user_role";
	private final String BLOCKED    = "blocked";

	@Override
	public void createUser(String firstName, String lastName, String email, String password, int role, boolean bloked) {
		this.connection = ConnectionFactory.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
			
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, password);
			statement.setInt(5, role);
			statement.setBoolean(6, bloked);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}

	}

	@Override
	public User getUser(int userId) {
		
		User user = new User();
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_USER)) {
			
			statement.setInt(1, userId);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while (resultSet.next()) {
					
					return fitUserParams(user, resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		
		this.connection = ConnectionFactory.getConnection();
		List<User> userList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while (resultSet.next()) {

					User user = new User();
					userList.add(fitUserParams(user, resultSet));

				}
				
				return userList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
		return userList;
	}

	@Override
	public void updateUser(User user) {
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
			
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getRole());
			statement.setBoolean(6, user.isBlocked());
			statement.setInt(7, user.getUserId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
	}

	@Override
	public void deleteUser(int userId) {
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
			
			statement.setInt(1, userId);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}

	}

	private User fitUserParams(User user, ResultSet resultSet) throws SQLException {
		
		user.setUserId(resultSet.getInt(USER_ID));
		user.setFirstName(resultSet.getString(FIRST_NAME));
		user.setLastName(resultSet.getString(LAST_NAME));
		user.setEmail(resultSet.getString(EMAIL));
		user.setPassword(resultSet.getString(PASSWORD));
		user.setRole(resultSet.getInt(USER_ROLE));
		user.setBlocked(resultSet.getBoolean(BLOCKED));
		return user;
	}

}
