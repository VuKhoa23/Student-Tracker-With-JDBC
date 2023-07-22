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
      <form action="StudentControllerServlet">
        <input type="text" name="searchValue"/>
        <input type="hidden" name="command" value="SEARCH"/>
        <button class="add-student-button" type="submit">Search </button>
      </form>
      <table>
        <tr>
          <th>First name</th>
          <th>Last name</th>
          <th>Email</th>
          <th>Action</th>
        <c:forEach var="student" items="${student_list}">
          <!-- Set up a update link for each student using url tag -->
          <c:url var="studentLink" value="StudentControllerServlet">
            <c:param name="command" value="LOAD"/>
            <c:param name="studentID" value="${student.id }" />
          </c:url>
          <!-- Set up a delete link for each student using url tag -->
          <c:url var="studentDeleteLink" value="StudentControllerServlet">
            <c:param name="command" value="DELETE"/>
            <c:param name="studentID" value="${student.id }" />
          </c:url>
          <tr>
              <!-- These will call the approriate getters -->
              <td>${student.firstName}</td>
              <td>${student.lastName }</td>
              <td>${student.email }</td>
              <td>
                <a href="${studentLink}">Update</a>
                |
                <a href="${studentDeleteLink}"
                    onclick="if(!(confirm('Do you want to delete the student?'))) return false"
                >Delete</a>
              </td>
           </tr>
         </c:forEach>
       </table>
    </div>
  </div>
</body>

</html>