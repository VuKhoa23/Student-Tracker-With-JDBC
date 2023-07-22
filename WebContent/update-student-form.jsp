<html>
	<head>
		<title>Update student</title>
		 <link rel="stylesheet" type="text/css" href="styles/styles.css" >
         <link rel="stylesheet" type="text/css" href="styles/add-student-style.css" >
	</head>
	<body>
		<div id="header-container">
            <h2 id="header">Student tracker</h2>
        </div>
        <div id="container">
            <h3>Update student</h3>
            <form action="StudentControllerServlet" method="POST" name="studentForm" onSubmit="return validateForm()">
            
                <input type="hidden" name="command" value="UPDATE"/>
                <input type="hidden" name="studentID" value="${student.id}"/>
                <table>
                  <tbody>
                    <tr>
                      <td><label>First name: </label></td>
                      <td><input type="text" name="firstName" value="${student.firstName}"/></td>
                    </tr>
                    <tr>
                      <td><label>Last name: </label></td>
                      <td><input type="text" name="lastName" value="${student.lastName}"/></td>
                    </tr>
                    <tr>
                      <td><label>Email: </label></td>
                      <td><input type="text" name="email" value="${student.email}"/></td>
                    </tr>
                    <tr>
                      <td><label></label></td>
                      <td><input type="submit" value="Save" class="Save"/></td>
                    </tr>
                  </tbody>
                </table>
            </form>
        </div>
        
        <div style="clear: both;"></div>
        <a href="StudentControllerServlet?command=LIST">Back to student view</a>
        <script type="text/javascript" src="js/student-validation.js"></script>  
	</body>
</html>