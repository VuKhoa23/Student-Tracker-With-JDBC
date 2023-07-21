<%@ page import="java.util.*, com.vukhoa23.web.jdbc.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


  <%
    // get the students from the request object (sent by controller servlet)  
    List<Student> students = (List<Student>) request.getAttribute("student_list");
  %>

<!DOCTYPE html>
<html>

<head>
  <title>Student Tracker</title>
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
      <table>
        <tr>
          <th>First name</th>
          <th>Last name</th>
          <th>Email</th>
        </tr>  
        <% for (Student temp: students){%>
          <tr>
            <td><%=temp.getFirstName() %></td>
            <td><%=temp.getLastName() %></td>
            <td><%=temp.getEmail() %> </td>
          </tr>
          
        <%}%>   
       </table>
    </div>
  </div>
</body>

</html>