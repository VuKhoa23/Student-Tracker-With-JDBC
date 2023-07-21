<html>
	<head>
		<title>Add student</title>
		 <link rel="stylesheet" type="text/css" href="styles/styles.css" >
         <link rel="stylesheet" type="text/css" href="styles/add-student-style.css" >
	</head>
	<body>
		<div id="header-container">
            <h2 id="header">Student tracker</h2>
        </div>
        <div id="container">
            <h3>Add student</h3>
            <form action="StudentControllerServlet" method="POST">
            
                <input type="hidden" name="command" value="ADD"/>
                <table>
                  <tbody>
                    <tr>
                      <td><label>First name: </label></td>
                      <td><input type="text" name="firstName" placeholder="Enter first name"/></td>
                    </tr>
                    <tr>
                      <td><label>Last name: </label></td>
                      <td><input type="text" name="lastName" placeholder="Enter last name"/></td>
                    </tr>
                    <tr>
                      <td><label>Email: </label></td>
                      <td><input type="text" name="email" placeholder="Enter email"/></td>
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
        <a href="StudentControllerServlet ">Back to student view</a>
	</body>
</html>