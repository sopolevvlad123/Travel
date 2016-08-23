package com.epam.sopolev.travelAgency.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.sopolev.travelAgency.dao.TourDAO;
import com.epam.sopolev.travelAgency.entity.Tour;
import com.epam.sopolev.travelAgency.entity.User;
import com.epam.sopolev.travelAgency.utils.ConnectionFactory;

public class TourDAOImpl implements TourDAO{
	private Connection connection;
	private final String CREATE_TOUR    = "INSERT INTO `travel_agency`.`tours` (`tour_name`, `price`, `tour_type`, `peope_num`, `hotel_type`, `max_discount`, `current_discount`, `status`)"
											+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private final String GET_BY_ID_TOUR = "SELECT * FROM travel_agency.tours WHERE tour_id = ?";
	private final String GET_ALL_TOURS  = "SELECT * FROM travel_agency.tours;";
	private final String UPDATE_TOUR    = "UPDATE `travel_agency`.`tours` SET `tour_name`=?, `price`=?, `tour_type`=?, `peope_num`=?, `hotel_type`=?, "
											+ "`max_discount`=?, `current_discount`=?', `status`=? WHERE `tour_id`=?;";
	private final String DELETE_TOUR    = "DELETE FROM `travel_agency`.`users` WHERE user_id = ? ";
	
	private final String TOUR_ID      = "tour_id";
	private final String TOUR_NAME    = "tour_name";
	private final String TOUR_PRICE   = "price";
	private final String TOUR_TYPE    = "tour_type";
	private final String PEOPLE_NUM   = "peope_num";
	private final String HOTEL_TYPE   = "hotel_type";
	private final String MAX_DISCOUNT = "max_discount";
	private final String CUR_DISCOUNT = "current_discount";
	private final String STATUS       = "status";
	


	@Override
	public void createTour(String tourName, double price, int tourType, int peopleNumber, int hotelType,
								  int maxDiscount, int currentDiscount, int tourStatus) {
		this.connection = ConnectionFactory.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(CREATE_TOUR)) {
			
			statement.setString(1, tourName);
			statement.setDouble(2, price);
			statement.setInt(3, tourType);
			statement.setInt(4, hotelType);
			statement.setInt(5, maxDiscount);
			statement.setInt(6, currentDiscount);
			statement.setInt(7, tourStatus);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
	}
	
	@Override
	public Tour getTour(int tourId) {
		
		Tour tour = new Tour();
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_TOUR)) {
			statement.setInt(1, tourId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					fitTourParams(tour, resultSet);					
				}								 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return tour;
	}


	@Override
	public List<Tour> getAllTours() {
		
		this.connection = ConnectionFactory.getConnection();
		List<Tour> tourList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TOURS)) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while (resultSet.next()) {

				Tour tour = new Tour();
					tourList.add(fitTourParams(tour, resultSet));

				}
				
				return tourList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
		return tourList;
	}

	
	@Override
	public void updateTour(Tour tour) {
		
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_TOUR)) {
			
			statement.setString(1, tour.getTourName());
			statement.setDouble(2, tour.getPrice());
			statement.setInt(3, tour.getTourType());
			statement.setInt(4, tour.getPeopleNumber());
			statement.setInt(5, tour.getHotelType());
			statement.setInt(6, tour.getMaxDiscount());
			statement.setInt(7, tour.getCurrentDiscount());
			statement.setInt(8, tour.getTourStatus());
			statement.setInt(8, tour.getTourId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
	}

	@Override
	public void deleteTour(int tourId) {
		this.connection = ConnectionFactory.getConnection();
		
		try (PreparedStatement statement = connection.prepareStatement(DELETE_TOUR)) {
			
			statement.setInt(1, tourId);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		
	}
	
	
	private Tour fitTourParams(Tour tour, ResultSet resultSet) throws SQLException{
		tour.setTourId(resultSet.getInt(TOUR_ID));
		tour.setTourName(resultSet.getString(TOUR_NAME));
		tour.setPrice(resultSet.getDouble(TOUR_PRICE));
		tour.setTourType(resultSet.getInt(TOUR_TYPE));
		tour.setHotelType(resultSet.getInt(HOTEL_TYPE));
		tour.setMaxDiscount(resultSet.getInt(MAX_DISCOUNT));
		tour.setCurrentDiscount(resultSet.getInt(CUR_DISCOUNT));
		tour.setTourStatus(resultSet.getInt(STATUS));
		return tour;
	}


	

}
