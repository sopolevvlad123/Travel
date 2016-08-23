package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.sopolev.travelAgency.dao.TourStatusDAO;
import com.epam.sopolev.travelAgency.entity.HotelType;
import com.epam.sopolev.travelAgency.entity.Tour;
import com.epam.sopolev.travelAgency.entity.TourStatus;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;

public class TourStatusDAOImpl implements TourStatusDAO {
	
	private Connection connection;
	
	private final String CREATE_TOUR_STATUS    = "INSERT INTO `travel_agency`.`tour_status` (`status_name`) VALUES (?);";
	private final String GET_BY_ID_TOUR_STATUS = "SELECT * FROM `travel_agency`.`tour_status` WHERE status_id = ?";
	private final String GET_ALL_TOUR_STATUS   = "SELECT * FROM `travel_agency`.`tour_status;";
	private final String UPDATE_TOUR_STATUS    = "UPDATE `travel_agency`.`tour_status` SET status_name = ? WHERE status_id = ?;";
	private final String DELETE_TOUR_STATUS    = "DELETE FROM `travel_agency`.`tour_status` WHERE status_id = ? ";

	private final String HOTEL_STATUS_NAME  = "status_name";
	private final String HOTEL_STATUS_ID    = "status_id";

	@Override
	public void createTourStatus(String statusName) {
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(CREATE_TOUR_STATUS)) {
			
			statement.setString(1, statusName);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
	}

	@Override
	public TourStatus getTourStatus(int statusId) {

		TourStatus tourStatus = new TourStatus();
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_TOUR_STATUS)) {
			
			statement.setInt(1, statusId);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					tourStatus.setStatusName(resultSet.getString(HOTEL_STATUS_NAME));
					tourStatus.setStatusId(resultSet.getInt(HOTEL_STATUS_ID));
					return tourStatus;		
					
				}								 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return tourStatus;
	}

	@Override
	public List<TourStatus> getAlltourStatus() {
		
		this.connection = ConnectionFactory.getConnection();
		List<TourStatus> tourStatusList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TOUR_STATUS)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					TourStatus tourStatus = new TourStatus();					
					tourStatus.setStatusName(resultSet.getString(HOTEL_STATUS_NAME));
					tourStatus.setStatusId(resultSet.getInt(HOTEL_STATUS_ID));
					tourStatusList.add(tourStatus);
					
				}
				return tourStatusList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return tourStatusList;

	}

	@Override
	public void updateTourStatus(TourStatus tourStatus) {

		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_TOUR_STATUS)) {
			
			statement.setString(1,tourStatus.getStatusName());
			statement.setInt(2, tourStatus.getStatusId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
	}

	@Override
	public void deleteTourStatus(int statusId) {

		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(DELETE_TOUR_STATUS)) {
			
			statement.setInt(1, statusId);		
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}	
		
		
	}

}
