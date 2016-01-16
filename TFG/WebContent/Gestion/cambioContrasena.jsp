<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>


<script src="js/sha512.js"></script>
<script src="js/validations.js"></script>

<!-- MENSAJE COOKIES -->
<script src="js/Cookies.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/Cookies.css">

<title>Administración</title>

<script>


$(document).ready(function(){
	$('span').hide();
	$('#cambiar').on('click',cambiarContrasenaAdmin);
});
</script>
</head>
<body>

	<jsp:include page="../Cookies.html" />
	<!-- <div class="container theme-showcase" role="main"> -->

	<div class="container theme-showcase" role="main">

		<jsp:include page="title.jsp" />

		<div class='jumbotron'>
			<c:choose>
				<c:when test="${not empty requestScope.admin}">
				<form id='cambioContra'>
				<h3>Modifica tu contraseña</h3>
				<label for="contraV">Contraseña antigua :</label> <input
					id="contraV" class='form-control'
					pattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{6,}"
					title="Six or more characters" name="contraV" type="password"
					placeholder="Introduce la vieja contraseña" required='true'><br>
				<label for="contraN">Contraseña nueva :</label> <input
					id="contraN" class='form-control'
					pattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{6,}"
					title="Six or more characters" name="contraN" type="password"
					placeholder="Introduce la nueva contraseña" required='true'><br>

				<button type="button" id='cambiar' class="btn btn-default">Cambiar</button>

				<span id='ok' class="alert alert-success" role="alert">Contraseña
					modificada</span> <span id='bad' class="alert alert-danger" role="alert">Error,
					no se ha podido modificar la contraseña</span> <span id='ok'
					class="alert alert-success" role="alert">Contraseña
					modificada</span> <span id='fail' class="alert alert-info" role="alert">Error,
					no has completado los campos o no tienen más de 6 caracteres</span>
		</div>
				</c:when>
				<c:otherwise>
					<h2>No estas logeado</h2>
				</c:otherwise>
			</c:choose>


		</div>
	</div>
	
</body>
</html>