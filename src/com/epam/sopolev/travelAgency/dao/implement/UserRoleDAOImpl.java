package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.sopolev.travelAgency.dao.UserRoleDAO;
import com.epam.sopolev.travelAgency.entity.UserRole;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;

public class UserRoleDAOImpl implements UserRoleDAO {
	
	private Connection connection;
	
	private final String CREATE_USER_ROLE    = "INSERT INTO `travel_agency`.`user_role` (`role_name`) VALUES (?)";
	private final String GET_BY_ID_USER_ROLE = "SELECT * FROM travel_agency.user_role WHERE role_id = ?";
	private final String GET_ALL_ROLES       = "SELECT * FROM travel_agency.user_role;";
	private final String UPDATE_ROLE         = "UPDATE `travel_agency`.`user_role` SET role_name = ? WHERE role_id = ?;";
	private final String DELETE_ROLE         = "DELETE FROM `travel_agency`.`user_role` WHERE role_id = ? ";

	private final String ROLE_NAME = "role_name";
	private final String ROLE_ID   = "role_id";
	
	@Override
	public void createRole(String roleName) {
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_ROLE)) {
			
			statement.setString(1, roleName);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
	}

	@Override
	public UserRole getRole(int roleId) {
		
		UserRole userRole = new UserRole();
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_USER_ROLE)) {
			
			statement.setInt(1, roleId);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					userRole.setRoleId(resultSet.getInt(ROLE_ID));
					userRole.setRoleName(resultSet.getString(ROLE_NAME));
					return userRole;	
					
				}								 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return userRole;
	}

	@Override
	public List<UserRole> getAllRoles() {	
		
		this.connection = ConnectionFactory.getConnection();
		List<UserRole> roleList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ROLES)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					UserRole userRole = new UserRole();
					userRole.setRoleId(resultSet.getInt(ROLE_ID));
					userRole.setRoleName(resultSet.getString(ROLE_NAME));;
					roleList.add(userRole);
					
				}
				return roleList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return roleList;
	}

	@Override
	public void updateRole(UserRole userRole) {
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_ROLE)) {
			
			statement.setString(1, userRole.getRoleName());		
			statement.setInt(2,userRole.getRoleId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}	

	}

	@Override
	public void deleteRole(int roleId) {
		
		this.connection = ConnectionFactory.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(DELETE_ROLE)) {
			
			statement.setInt(1, roleId);		
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}	
	}

}
