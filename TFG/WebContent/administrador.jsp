<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/sha512.js"></script>
<script src="js/validations.js"></script>

<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>

<!-- MENSAJE COOKIES -->
<script src="js/Cookies.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/Cookies.css">

<title>LogIn</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<!-- Mensaje de aviso de cookies -->
	<jsp:include page="Cookies.html" />
	<!-- <div class="container theme-showcase" role="main"> -->

	<div class='jumbotron'>
		<div class="form-group">
			<form id='login' method="POST" action="LogInAdmin"
				enctype="multipart/form-data" role='form'>
				<h2>Inicio de sesión de administrador</h2>
				<label for="contrasena">Contraseña:</label> <input id="contrasena"
					class='form-control' name="contrasena" type="password"
					placeholder="Introduce una contraseña" required='true'> <input
					id="contra" class='form-control' name="contrasena" type="hidden">
				<br> <input type="submit" class='btn btn-default' name="submit"
					id="submit">
			</form>
		</div>
	</div>
	<script>
		loginSubmit();
	</script>
	</div>
</body>
</html>