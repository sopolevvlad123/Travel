package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.sopolev.travelAgency.dao.TourStatusDAO;
import com.epam.sopolev.travelAgency.entity.HotelType;
import com.epam.sopolev.travelAgency.entity.Tour;
import com.epam.sopolev.travelAgency.entity.TourStatus;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;
import com.epam.sopolev.travelAgency.utils.SqlConstants;

public class TourStatusDAOImpl implements TourStatusDAO {
		
	
	@Override
	public boolean createTourStatus(String statusName) {		
	
		try (Connection connection = ConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_CREATE_TOUR_STATUS )) {
			
			statement.setString(1, statusName);
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "create tourStatus fail", e); 
			return false;
		} 
		
		return true;
	}

	@Override
	public TourStatus getTourStatus(int statusId) {

		TourStatus tourStatus = new TourStatus();
		
		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_BY_ID_TOUR_STATUS)) {
			
			statement.setInt(1, statusId);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {					
					
					return fitTourStatus(tourStatus, resultSet);		
					
				}								 
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getById tourStatus fail", e); 
		} 
		return tourStatus;
	}

	@Override
	public List<TourStatus> getAlltourStatus() {
		
		List<TourStatus> tourStatusList = new ArrayList<>();
		
		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_ALL_TOUR_STATUS)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					TourStatus tourStatus = new TourStatus();					
					tourStatusList.add(fitTourStatus(tourStatus, resultSet));
					
				}
				return tourStatusList;
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getAll tourStatus fail", e); 
		} 
		return tourStatusList;

	}

	@Override
	public boolean updateTourStatus(TourStatus tourStatus) {
		
		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_UPDATE_TOUR_STATUS)) {
			
			statement.setString(1,tourStatus.getStatusName());
			statement.setInt(2, tourStatus.getStatusId());
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "update tourStatus fail", e); 
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteTourStatus(int statusId) {
		
		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_DELETE_TOUR_STATUS)) {
			
			statement.setInt(1, statusId);		
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "delete tourStatus fail", e);
			return false;
		} 		
		
		return true;		
	}
	
	private TourStatus fitTourStatus(TourStatus tourStatus, ResultSet resultSet) throws SQLException{
		
		tourStatus.setStatusName(resultSet.getString(SqlConstants.SQL_HOTEL_STATUS_NAME));
		tourStatus.setStatusId(resultSet.getInt(SqlConstants.SQL_HOTEL_STATUS_ID));
		
		return tourStatus;
		
	}

}
