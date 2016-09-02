package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.sopolev.travelAgency.dao.HotelTypeDAO;
import com.epam.sopolev.travelAgency.entity.HotelType;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;
import com.epam.sopolev.travelAgency.utils.SqlConstants;

public class HotelTypeDAOImpl  implements HotelTypeDAO{
	
	
	@Override
	public boolean createHotelType(String hotelTypeName) { 		
		
		try (Connection connection = ConnectionFactory.getConnection();
			  PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_CREATE_HOTEL_TYPE)) {
			
			statement.setString(1, hotelTypeName);
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "create hotelType fail", e);
			return false;
		} 
		return true;
	}

	@Override
	public HotelType getHotelType(int hotelTypeId) {
		
		HotelType hotelType = new HotelType();
		
		try (Connection connection = ConnectionFactory.getConnection();
			  PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_BY_ID_HOTEL_TYPE)) {	
			
			statement.setInt(1, hotelTypeId);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					return fitHotelType(hotelType, resultSet);		
					
				}								 
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getById hotelType fail", e); 
		} 
		return hotelType;
	}

	@Override
	public List<HotelType> getAllHotelTypes() {
		List<HotelType> hotelTypeList = new ArrayList<>();
		
		try (Connection connection = ConnectionFactory.getConnection();
				  PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_ALL_HOTEL_TYPE)) {	
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					HotelType hotelType = new HotelType();					
					hotelTypeList.add(fitHotelType(hotelType, resultSet));
					
				}
				return hotelTypeList;
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getAll hotelType fail", e); 
		} 
		return hotelTypeList;
	}

	@Override
	public boolean updateHotelType(HotelType hotelType) {		
	
		try (Connection connection = ConnectionFactory.getConnection();
				  PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_UPDATE_HOTEL_TYPE)) {
			
			statement.setString(1, hotelType.getHotelTypeName());		
			statement.setInt(2,hotelType.getHotelTypeId());
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "update hotelType fail", e); 
			return false;
		} 	
		
		return true;
	}

	@Override
	public boolean deleteHotelType(int hotelTypeId) {		
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_DELETE_HOTEL_TYPE)) {
			
			statement.setInt(1, hotelTypeId);		
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "delete hotelType fail", e); 
			return false;
		}
		
		return true;		
	}
	
	private HotelType fitHotelType(HotelType hotelType, ResultSet resultSet) throws SQLException{
		
		hotelType.setHotelTypeName(resultSet.getString(SqlConstants.SQL_HOTEL_TYPE_NAME));
		hotelType.setHotelTypeId(resultSet.getInt(SqlConstants.SQL_HOTEL_TYPE_ID));
		return hotelType;
	}
	

}
