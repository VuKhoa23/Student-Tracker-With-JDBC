package com.vukhoa23.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class TestJDBC
 */
@WebServlet("/TestJDBC")
public class TestJDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestJDBC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set up print writer
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		// get a connection to db
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
		// create sql statement
			String sql = "select * from student";
			myStmt = myConn.createStatement();
		
		// execute sql statment
			
			myRs = myStmt.executeQuery(sql);
			
		// process result set
			
			while(myRs.next()) {
				String emailString = myRs.getString("email");
				out.println(emailString);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
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
