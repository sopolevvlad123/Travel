package com.epam.sopolev.travelAgency.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.sopolev.travelAgency.dao.UserRoleDAO;
import com.epam.sopolev.travelAgency.dao.implement.UserDAOImpl;
import com.epam.sopolev.travelAgency.dao.implement.UserRoleDAOImpl;
import com.epam.sopolev.travelAgency.entity.User;
import com.epam.sopolev.travelAgency.entity.UserRole;

/**
 * Servlet implementation class DAOTestServlet
 */
@WebServlet("/DAOTestServlet")
public class DAOTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DAOTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAOImpl roleDAOImpl = new UserDAOImpl();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter writer = response.getWriter();
		roleDAOImpl.createUser("Vasyl","Vasylsev", "vasya@gmail.com", "22", 1, false);
		roleDAOImpl.createUser("Степан","Степанцов", "дщд@gmail.com", "666", 2, false);
		roleDAOImpl.createUser("Иван","Петров", "petroFF@gmail.com", "321", 2, false);
		List<User> users = roleDAOImpl.getAllUsers();
		writer.append(users.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
