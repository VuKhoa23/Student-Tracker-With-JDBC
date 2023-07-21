package com.vukhoa23.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;
	// resouce injection to dataSource
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		// create instance of studentDButil
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			// read the command param
			String command = request.getParameter("command");
			// if reading the command failed
			if(command == null) {
				command="LIST";
			}
			switch (command) {
			case "LIST":
				listStudent(request, response);
				break;
			
			default:
				listStudent(request, response);
			//
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			// read the command param
			String command = request.getParameter("command");
			// if reading the command failed
			if(command == null) {
				command="LIST";
			}
			switch (command) {
			case "ADD":
				addStudent(request, response);
				break;
			
			default:
				listStudent(request, response);
			//
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get student from dbUtil
		List<Student> students = studentDbUtil.getStudents();
		// add students to the request
		request.setAttribute("student_list", students);
		// send data to JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-students-jstl.jsp");
		dispatcher.forward(request, response);	
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		// create new student
		Student temp = new Student(firstName, lastName, email);
		// add student to db
		studentDbUtil.addStudent(temp);
			
		
		// send redirect
		response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
		
	}



}
