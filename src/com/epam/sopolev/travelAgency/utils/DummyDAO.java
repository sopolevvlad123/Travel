package com.epam.sopolev.travelAgency.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DummyDAO {
	
	public static void getUser() {
	Connection connection = ConnectionFactory.getConnection();
	System.out.println(connection);
	  Statement statement;
	try {
		statement = connection.createStatement();
		 String sql = "SELECT * FROM travel_agency.users";
	      System.out.println("Hello");
	      ResultSet rs = statement.executeQuery(sql);
	       while(rs.next()){
	    	   System.out.println(rs.getString("first_name"));
	       }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
	}
	public static void main(String[] args) {
		getUser();
	}

}
