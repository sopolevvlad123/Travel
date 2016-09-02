package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.sopolev.travelAgency.dao.UserRoleDAO;
import com.epam.sopolev.travelAgency.entity.UserRole;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;
import com.epam.sopolev.travelAgency.utils.SqlConstants;

public class UserRoleDAOImpl implements UserRoleDAO {	
	
	
	@Override
	public boolean createRole(String roleName) {
		
	
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_CREATE_USER_ROLE)) {
			
			statement.setString(1, roleName);
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "create userRole fail", e); 
			return false;
		}
		
		return true;
	}

	@Override
	public UserRole getRole(int roleId) {
		
		UserRole userRole = new UserRole();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_BY_ID_USER_ROLE)) {
			
			statement.setInt(1, roleId);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					userRole.setRoleId(resultSet.getInt(SqlConstants.SQL_ROLE_ID));
					userRole.setRoleName(resultSet.getString(SqlConstants.SQL_ROLE_NAME));
					
					return fitUserRole(userRole, resultSet);	
					
				}								 
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getById userRole fail", e); 
		} 
		return userRole;
	}

	@Override
	public List<UserRole> getAllRoles() {	
		
		List<UserRole> roleList = new ArrayList<>();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_ALL_ROLES)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					UserRole userRole = new UserRole();
					
					roleList.add(fitUserRole(userRole, resultSet));
					
				}
				return roleList;
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getAll userRole fail", e); 
		} 
		return roleList;
	}

	@Override
	public boolean updateRole(UserRole userRole) {
				
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_UPDATE_ROLE)) {
			
			statement.setString(1, userRole.getRoleName());		
			statement.setInt(2,userRole.getRoleId());
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "update userRole fail", e); 
			return false;
		} 
		
		return true;
	}

	@Override
	public boolean deleteRole(int roleId) {		
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_DELETE_ROLE)) {
			
			statement.setInt(1, roleId);		
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "delete userRole fail", e);
			return false;
		}
		
		return true;
	}
	
	private UserRole fitUserRole(UserRole userRole, ResultSet resultSet) throws SQLException{
		userRole.setRoleId(resultSet.getInt(SqlConstants.SQL_ROLE_ID));
		userRole.setRoleName(resultSet.getString(SqlConstants.SQL_ROLE_NAME));
		return userRole;
	}

}
