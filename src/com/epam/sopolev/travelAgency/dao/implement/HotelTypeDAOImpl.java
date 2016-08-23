package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.sopolev.travelAgency.dao.HotelTypeDAO;
import com.epam.sopolev.travelAgency.entity.HotelType;
import com.epam.sopolev.travelAgency.entity.UserRole;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;

public class HotelTypeDAOImpl  implements HotelTypeDAO{
	private Connection connection;
	
	private final String CREATE_HOTEL_TYPE    = "INSERT INTO `travel_agency`.`hotel_type` (`type_name`) VALUES (?);";
	private final String GET_BY_ID_HOTEL_TYPE = "SELECT * FROM `travel_agency`.`hotel_type` WHERE type_id = ?";
	private final String GET_ALL_HOTEL_TYPE   = "SELECT * FROM `travel_agency`.`hotel_type`;";
	private final String UPDATE_HOTEL_TYPE    = "UPDATE `travel_agency`.`hotel_type` SET type_name = ? WHERE type_id = ?;";
	private final String DELETE_HOTEL_TYPE    = "DELETE FROM `travel_agency`.`hotel_type` WHERE type_id = ? ";

	private final String HOTEL_TYPE_NAME  = "type_name";
	private final String HOTEL_TYPE_ID    = "type_id";
	
	@Override
	public void createHotelType(String hotelTypeName) { 
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(CREATE_HOTEL_TYPE)) {
			
			statement.setString(1, hotelTypeName);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
	}

	@Override
	public HotelType getHotelType(int hotelTypeId) {
		
		HotelType hotelType = new HotelType();
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_HOTEL_TYPE)) {
			
			statement.setInt(1, hotelTypeId);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					hotelType.setHotelTypeName(resultSet.getString(HOTEL_TYPE_NAME));
					hotelType.setHotelTypeId(resultSet.getInt(HOTEL_TYPE_ID));
					return hotelType;		
					
				}								 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return hotelType;
	}

	@Override
	public List<HotelType> getAllHotelTypes() {
		this.connection = ConnectionFactory.getConnection();
		List<HotelType> hotelTypeList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_ALL_HOTEL_TYPE)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					HotelType hotelType = new HotelType();
					hotelType.setHotelTypeName(resultSet.getString(HOTEL_TYPE_NAME));
					hotelType.setHotelTypeId(resultSet.getInt(HOTEL_TYPE_ID));
					hotelTypeList.add(hotelType);
					
				}
				return hotelTypeList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return hotelTypeList;
	}

	@Override
	public void updateHotelType(HotelType hotelType) {
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_HOTEL_TYPE)) {
			
			statement.setString(1, hotelType.getHotelTypeName());		
			statement.setInt(2,hotelType.getHotelTypeId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}	
	}

	@Override
	public void deleteHotelType(int hotelTypeId) {
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(DELETE_HOTEL_TYPE)) {
			
			statement.setInt(1, hotelTypeId);		
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}	
		
	}
	

}
