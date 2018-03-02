<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script type="text/javascript" src="../../js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../js/login/login.js"></script>
</head>

 <body onload=""> 


	<div id="container">
		<div>
			<p><b>Show Ad Login: . </b></p>
		</div>
		<form method="post" id="registrationForm">
			<table>
				<tbody>
					<tr>
						<td>Email</td>
						<td>
						<input name="productid" id="productid" type="hidden">
						<input name="email" id="email" type="text">
						</td>
					</tr>
					
					<tr>
						<td>Password</td>
						<td>
						<input name="productid" id="productid" type="hidden">
						<input name="password" id="password" type="text">
						</td>
					</tr>
					
					<tr>
						<td><input type=submit value="Login" id="subButton" onclick="return methodCall()"></td>
					</tr>
					
					
				</tbody>
			</table>
		</form>
		<div id="productFormResponse"><table></table></div>
	</div>
</body>
</html>