package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.sopolev.travelAgency.dao.TourDAO;
import com.epam.sopolev.travelAgency.entity.Tour;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;
import com.epam.sopolev.travelAgency.utils.SqlConstants;

public class TourDAOImpl implements TourDAO{
	
	


	@Override
	public boolean createTour(String tourName, double price, int tourType, int peopleNumber, int hotelType,
								  int maxDiscount, int discountStep,int currentDiscount, int tourStatus) {
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_CREATE_TOUR)) {
			
			statement.setString(1, tourName);
			statement.setDouble(2, price);
			statement.setInt(3, tourType);
			statement.setInt(4, peopleNumber);
			statement.setInt(5, hotelType);
			statement.setInt(6, maxDiscount);
			statement.setInt(7, discountStep);
			statement.setInt(8, currentDiscount);
			statement.setInt(9, tourStatus);
			
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(e.getClass()).log(Level.ERROR, "create tour fail", e); 
			return false;
		} 		
		
		return true;
	}
	
	@Override
	public Tour getTourById(long tourId) {
		
		Tour tour = new Tour();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_BY_ID_TOUR)) {
			statement.setLong(1, tourId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					fitTourParams(tour, resultSet);					
				}								 
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getById tour fail", e); 
		} 
		
		return tour;
	}

	@Override
	public Tour getTourByName(String tourName) {
		
		Tour tour = new Tour();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_BY_NAME_TOUR)) {
			statement.setString(1, tourName);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					fitTourParams(tour, resultSet);					
				}								 
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getById tour fail", e); 
		} 
		
		return tour;
	}


	@Override
	public List<Tour> getAllTours() {	
		
		List<Tour> tourList = new ArrayList<>();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_ALL_TOURS)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while (resultSet.next()) {

				Tour tour = new Tour();
					tourList.add(fitTourParams(tour, resultSet));

				}
				
				return tourList;
			}
		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getAll tour fail", e); 
		} 
		return tourList;
	}
	
	
	@Override
	public List<Tour> getFreeTours() {
		List<Tour> tourList = new ArrayList<>();
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_GET_FREE_TOURS)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while (resultSet.next()) {

				Tour tour = new Tour();
					tourList.add(fitTourParams(tour, resultSet));

				}
				
				System.out.println("Free tours   " + tourList);
				return tourList;
			}
		} catch (SQLException e) { 
			System.out.println("Hello");
			e.printStackTrace();
			Logger.getLogger(e.getClass()).log(Level.ERROR, "getAll tour fail", e); 
		} 
		
		return tourList;
	}

	

	
	@Override
	public boolean updateTour(Tour tour) {
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_UPDATE_TOUR)) {		
			
			statement.setString(1, tour.getTourName());
			statement.setDouble(2, tour.getPrice());
			statement.setInt(3, tour.getTourType());
			statement.setInt(4, tour.getPeopleNumber());
			statement.setInt(5, tour.getHotelType());
			statement.setInt(6, tour.getMaxDiscount());
			statement.setInt(7, tour.getDicsountStep());
			statement.setInt(8, tour.getCurrentDiscount());
			statement.setInt(9, tour.getTourStatus());
			statement.setLong(10, tour.getTourId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(e.getClass()).log(Level.ERROR, "update tour fail", e); 
			return false;
		} 
		
		return true;
	}

	@Override
	public boolean deleteTour(long tourId) {
		
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_DELETE_TOUR)) {			
			statement.setLong(1, tourId);
			statement.execute();

		} catch (SQLException e) {
			Logger.getLogger(e.getClass()).log(Level.ERROR, "delete tour fail", e); 
			return false;
		}		
		
		return true;
	}
	
	
	private Tour fitTourParams(Tour tour, ResultSet resultSet) throws SQLException{
		
		tour.setTourId(resultSet.getInt(SqlConstants.SQL_TOUR_ID));
		tour.setTourName(resultSet.getString(SqlConstants.SQL_TOUR_NAME));
		tour.setPrice(resultSet.getDouble(SqlConstants.SQL_TOUR_PRICE));
		tour.setTourType(resultSet.getInt(SqlConstants.SQL_TOUR_TYPE ));
		tour.setHotelType(resultSet.getInt(SqlConstants.SQL_HOTEL_TYPE));
		tour.setMaxDiscount(resultSet.getInt(SqlConstants.SQL_MAX_DISCOUNT));
		tour.setPeopleNumber(resultSet.getInt(SqlConstants.SQL_PEOPLE_NUM));
		tour.setDicsountStep(resultSet.getInt(SqlConstants.SQL_DISCOUNT_STEP));
		tour.setCurrentDiscount(resultSet.getInt(SqlConstants.SQL_CUR_DISCOUNT));
		tour.setTourStatus(resultSet.getInt(SqlConstants.SQL_STATUS));
		
		return tour;
	}

	

	

}
