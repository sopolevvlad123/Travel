package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.sopolev.travelAgency.dao.TourTypeDAO;
import com.epam.sopolev.travelAgency.entity.HotelType;
import com.epam.sopolev.travelAgency.entity.TourStatus;
import com.epam.sopolev.travelAgency.entity.TourType;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;
import com.epam.sopolev.travelAgency.utils.SqlConstants;


public class TourTypeDAOImpl implements TourTypeDAO {
	
	@Override
	public boolean createTourType(String tourTypeName) {
		
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_CREATE_TOUR_TYPE)) {
			
			statement.setString(1, tourTypeName);
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "create tourType fail", e); 
			return false;
		} 
		
		return true;
	}

	@Override
	public TourType getTourType(int tourTypeId) {

		TourType tourType = new TourType();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_BY_ID_TOUR_TYPE)) {		
			
			statement.setInt(1, tourTypeId);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					return fitTourType(tourType, resultSet);		
					
				}								 
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getById tourType fail", e); 
		}
		
		return tourType;
	}

	@Override
	public List<TourType> getAllTourTypes() {
		
		List<TourType> tourTypeList = new ArrayList<>();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_ALL_TOUR_TYPE)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					TourType tourType = new TourType();
					tourTypeList.add(fitTourType(tourType, resultSet));
					
				}
				return tourTypeList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getAll tourType fail", e); 
		} 
		
		return tourTypeList;			
	}

	@Override
	public boolean updateTourType(TourType tourType) {
	
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_UPDATE_TOUR_TYPE)) {		
			
			statement.setString(1, tourType.getTypeName());		
			statement.setInt(2,tourType.getTypeId());
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "update tourType fail", e); 
			return false;
		} 		
		
		return true;
	}

	@Override
	public boolean deleteTourType(int tourTypeId) {
				
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_DELETE_TOUR_TYPE)) {
			
			statement.setInt(1, tourTypeId);		
			statement.execute();
			
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "delete tourType fail", e); 
			return false;
		}
		
		return true;
	}

	private TourType fitTourType(TourType tourType, ResultSet resultSet) throws SQLException{
		
		tourType.setTypeName(resultSet.getString(SqlConstants.SQL_TOUR_TYPE_NAME));
		tourType.setTypeId(resultSet.getInt(SqlConstants.SQL_TOUR_TYPE_ID));
		
		return tourType;
	}
	

}
