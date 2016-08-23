package com.epam.sopolev.travelAgency.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	private static final String INIT_CONTEXT = "java:comp/env";
	private static final String ENVIROMENT_CONTEXT = "jdbc/travel_agency";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env/");
			DataSource ds = (DataSource) envContext.lookup("jdbc/travel_agency");			 
			return ds.getConnection();

		} catch (NamingException ex) {
			System.err.println(ex);
		} catch (SQLException ex) {
			System.err.println(ex);
		}
		return connection;

	}
	
	public static void closeConnection(Connection connection){
		if(connection == null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
