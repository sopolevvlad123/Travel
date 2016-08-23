package com.epam.sopolev.travelAgency.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.epam.sopolev.travelAgency.utils.DummyDAO;

/**
 * Servlet implementation class TestServlet
 */
//@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter writer = response.getWriter();
	        try {
	            Context initContext = new InitialContext();
	            Context envContext = (Context) initContext.lookup("java:comp/env");
	            System.out.println(envContext);
	            DataSource ds = (DataSource) envContext.lookup("jdbc/travel_agency");
	            Connection conn = ds.getConnection();
	             System.out.println(conn);
		            String sql = "INSERT INTO `travel_agency`.`time_test` (`time_testcol`) VALUES (?);";
		            java.util.Date dt = new java.util.Date();

		    		java.text.SimpleDateFormat sdf = 
		    		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    		String currentTime = sdf.format(dt);
		    		
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setString(1, currentTime);
	            statement.execute();
	            
	                 
	        } catch (NamingException ex) {
	            System.err.println(ex);
	        } catch (SQLException ex) {
	            System.err.println(ex);
	        }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
