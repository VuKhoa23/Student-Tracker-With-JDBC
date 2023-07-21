package com.vukhoa23.web.jdbc;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

public class StudentDbUtil {
	private DataSource dataSource;
	public StudentDbUtil(DataSource DS) {
		dataSource = DS;
	}
	public List<Student> getStudents() throws Exception{
		List<Student> students = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		// get a connection
		myConn = dataSource.getConnection();
		// create sql query
		String query = "select * from student order by last_name";
		
		myStmt = myConn.createStatement();
		// execute query
		myRs = myStmt.executeQuery(query);
		// process result set
		while(myRs.next()) {
			// retrieve data
			int id = myRs.getInt("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			// create new students obj
			Student temp = new Student(id, firstName, lastName, email);
			// add to list
			students.add(temp);
		}
		//close JDBC connects
		return students;

		}
		finally {
			try {
			// myConn.close doenst close the connection, it put the connection back to the pool so some1 else can use
			myConn.close();
			myStmt.close();
			myRs.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
