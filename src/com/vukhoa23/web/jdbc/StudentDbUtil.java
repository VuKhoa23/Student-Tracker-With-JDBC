package com.vukhoa23.web.jdbc;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

public class StudentDbUtil {
	private DataSource dataSource;

	public StudentDbUtil(DataSource DS) {
		dataSource = DS;
	}

	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		// declare jdbc objects
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
			while (myRs.next()) {
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
			// close JDBC connects
			return students;

		} finally {
			try {
				// myConn.close doenst close the connection, it put the connection back to the
				// pool so some1 else can use
				myConn.close();
				myStmt.close();
				myRs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public void addStudent(Student temp) throws SQLException {
		// declare jdbc objects
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// get a connection
			myConn = dataSource.getConnection();
			// create sql query
			String query = "insert into student " + "(first_name, last_name, email) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(query);
			myStmt.setString(1, temp.getFirstName());
			myStmt.setString(2, temp.getLastName());
			myStmt.setString(3, temp.getEmail());
			// execute query, base 1
			myStmt.execute();
		} finally {
			try {
				myConn.close();
				myStmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public Student getStudent(String studentID) throws Exception {
		Student student = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id;

		try {
			// convert string id to int id
			id = Integer.parseInt(studentID);
			// get connection to the DB
			myConn = dataSource.getConnection();
			// create sql query
			String query = "select * from student where id=?";
			// create prepared statement
			myStmt = myConn.prepareStatement(query);
			// set params
			myStmt.setInt(1, id);
			// execute query
			myRs = myStmt.executeQuery();
			// retrieve the datas
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				// create new student base on retrieved infos
				student = new Student(id, firstName, lastName, email);
			} else {
				throw new Exception("Can not find student with id = " + id);
			}
			return student;
		} finally {
			try {
				myConn.close();
				myStmt.close();
				myRs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void updateStudent(Student student) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// get connection
			myConn = dataSource.getConnection();
			// create update query
			String query = "update student " + "set first_name=?, last_name=?, email=? " + "where id=? ";
			// prepare myStmt
			System.out.println(student.toString());
			myStmt = myConn.prepareStatement(query);
			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());
			myStmt.setInt(4, student.getId());
			// set params
			myStmt.execute();
		} finally {
			try {
				myConn.close();
				myStmt.close();
				myRs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteStudent(int id) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String query = "delete from student where id=?";
			myStmt = myConn.prepareStatement(query);
			myStmt.setInt(1, id);

			myStmt.execute();
		} finally {
			try {
				myConn.close();
				myStmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Student> searchStudent(String searchValue) throws SQLException {
		List<Student> students = new ArrayList<>();
		// declare jdbc objects
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// get a connection
			myConn = dataSource.getConnection();
			// create sql query
			if (searchValue != null && searchValue.trim().length() > 0) {
				String query = "select * from student where lower(first_name) like ? or lower(last_name) like ?";
				myStmt = myConn.prepareStatement(query);
				String searchValueLike = "%" + searchValue.toLowerCase() + "%";
				myStmt.setString(1, searchValueLike);
				myStmt.setString(2, searchValueLike);
			} else {
				String query = "select * from student order by last_name";
				// create prepared statement
				myStmt = myConn.prepareStatement(query);
			}
			myRs = myStmt.executeQuery();
			while(myRs.next()) {
				int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
                
                Student student = new Student(id, firstName, lastName, email);
                
                students.add(student);
			}
			return students;

		} finally {
			try {
				// myConn.close doenst close the connection, it put the connection back to the
				// pool so some1 else can use
				myConn.close();
				myStmt.close();
				myRs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
