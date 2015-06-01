<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf8'>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/sha512.js"></script>
<script src="js/validations.js"></script>

<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>


<title>LogIn</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
<div class="container theme-showcase" role="main">
<div class='jumbotron'>
	<div class="form-group">
	<form id='login' method="POST" action="LogIn"
		enctype="multipart/form-data" role='form'>

		<label for="usuario">Nombre de usuario :</label> <input id="usuario"
			name="usuario" class='form-control' type="text" placeholder="usuariodeprueba"
			required='true'> <label for="contrasena">Contraseña:</label>
		<input id="contrasena" class='form-control' name="contrasena" type="password"
			placeholder="Introduce una contraseña" required='true'> 
			<br>
			<input
			type="submit" class='btn btn-default' name="submit" id="submit">
	</form>
</div>
</div>
</div>
	<script>
			loginSubmit();
			</script>
</body>
</html>