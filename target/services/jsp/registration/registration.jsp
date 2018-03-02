<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/registration/registration.js"></script>
</head>

<body onload="loadObjects()">
	<div id="container">
		<div>
			<p><b>Show Ad Registration: Create the user of the system. </b></p>
		</div>
		<form method="post" id="registrationForm">
			<table>
				<tbody>
					<tr>
						<td>Email</td>
						<td>
						<input name="userId" id="userId" type="hidden">
						<input name="email" id="email" type="text">
						</td>
					</tr>
					
					<tr>
						<td>Password</td>
						<td>
						<input name="password" id="password" type="text">
						</td>
					</tr>
					
					<tr>
						<td>Organistation Name</td>
						<td>
						<input name="organisationName" id="organisationName" type="text">
						</td>
					</tr>
					
					
					<tr>
						<td>Organistation Type</td>
						<td>
						<input name="organisationType" id="organisationType" type="text">
						</td>
					</tr>
					
					<tr>
						<td>Are you Owner, Advertiser</td>
						<td>
						<input name="userType" id="userType" type="text">
						</td>
					</tr>
					<tr>
	
						<td><input type=submit value="Register User" id="subButton" onclick="return methodCall()"></td>
					</tr>
					
					
				</tbody>
			</table>
		</form>
		<div id="productFormResponse"></div>
	</div>
</body>
</html>