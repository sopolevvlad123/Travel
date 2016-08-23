package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.sopolev.travelAgency.dao.TourTypeDAO;
import com.epam.sopolev.travelAgency.entity.HotelType;
import com.epam.sopolev.travelAgency.entity.TourType;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;


public class TourTypeDAOImpl implements TourTypeDAO {
	private Connection connection;
	
	private final String CREATE_TOUR_TYPE    = "INSERT INTO `travel_agency`.`tour_type` (`tour_type_name`) VALUES (?);";
	private final String GET_BY_ID_TOUR_TYPE = "SELECT * FROM `travel_agency`.`tour_type` WHERE tour_type_id = ?";
	private final String GET_ALL_TOUR_TYPE   = "SELECT * FROM `travel_agency`.`tour_type`;";
	private final String UPDATE_TOUR_TYPE    = "UPDATE `travel_agency`.`tour_type` SET tour_type_name = ? WHERE tour_type_id = ?;";
	private final String DELETE_TOUR_TYPE    = "DELETE FROM `travel_agency`.`tour_type` WHERE tour_type_id = ? ";

	private final String HOTEL_TYPE_NAME  = "tour_type_name";
	private final String HOTEL_TYPE_ID    = "tour_type_id";

	@Override
	public void createTourType(String tourTypeName) {
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(CREATE_TOUR_TYPE)) {
			
			statement.setString(1, tourTypeName);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
	}

	@Override
	public TourType getTourType(int tourTypeId) {

		TourType tourType = new TourType();
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_TOUR_TYPE)) {
			
			statement.setInt(1, tourTypeId);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					tourType.setTypeName(resultSet.getString(HOTEL_TYPE_NAME));
					tourType.setTypeId(resultSet.getInt(HOTEL_TYPE_ID));
					return tourType;		
					
				}								 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return tourType;
	}

	@Override
	public List<TourType> getAllTourTypes() {
		
		this.connection = ConnectionFactory.getConnection();
		List<TourType> tourTypeList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TOUR_TYPE)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					TourType tourType = new TourType();
					tourType.setTypeName(resultSet.getString(HOTEL_TYPE_NAME));
					tourType.setTypeId(resultSet.getInt(HOTEL_TYPE_ID));
					tourTypeList.add(tourType);
					
				}
				return tourTypeList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return tourTypeList;
	}

	@Override
	public void updateTourType(TourType tourType) {
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_TOUR_TYPE)) {
			
			statement.setString(1, tourType.getTypeName());		
			statement.setInt(2,tourType.getTypeId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
	}

	@Override
	public void deleteTourType(int tourTypeId) {
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(DELETE_TOUR_TYPE)) {
			
			statement.setInt(1, tourTypeId);		
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}	
		
	}

	

}
