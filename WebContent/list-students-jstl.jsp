<%@ page import="java.util.*, com.vukhoa23.web.jdbc.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>

<head>
  <title>Student Tracker JSTL Tags</title>
  <link rel="stylesheet" type="text/css" href="styles/styles.css" >
  
</head>

<body>
  <div id="header-container">
    <div id="header">
      <h2>Student tracker</h2>
    </div>
  </div>
  
  <div id="main-container">
    <div id="main-content">
      <button class="add-student-button" onclick="window.location.href='add-student-form.jsp'">Add student</button>
      <table>
        <tr>
          <th>First name</th>
          <th>Last name</th>
          <th>Email</th>
        <c:forEach var="student" items="${student_list}">
        <tr>
            <!-- These will call the approriate getters -->
            <td>${student.firstName}</td>
            <td>${student.lastName }</td>
            <td>${student.email }</td>
         </tr>
         </c:forEach>
       </table>
    </div>
  </div>
</body>

</html>