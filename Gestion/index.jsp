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

<!-- MENSAJE COOKIES -->
<script src="js/Cookies.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/Cookies.css">
<script>
$().ready(function(){
		$('[data-toggle="tooltip"]').tooltip();
});
</script>
<title>Administración</title>

</head>
<body>

	<jsp:include page="../Cookies.html" />
	<!-- <div class="container theme-showcase" role="main"> -->

	<div class="container theme-showcase" role="main">

		<jsp:include page="title.jsp" />

		<div class='jumbotron'>
			<c:choose>
				<c:when test="${not empty requestScope.admin}">
					<h2>Bienvenido, Administrador</h2>
					<div class="error-actions">
						<center><a class="btn btn-danger" id='cambiocontrasena' href='CCA' role="button" data-toggle="tooltip" data-placement="bottom" title="Pulsa aquí para cambiar la contraseña del administrador">Cambiar
							Contraseña</a> <a id='deshabilitarusuario' class="btn btn-warning" href='EU' role="button" data-toggle="tooltip" data-placement="bottom" title="Pulsa aquí para deshabilitar autores">Gestionar
							Usuarios</a> <a id='deshabilitarobra' class="btn btn-warning" href='EliminarObra' role="button" data-toggle="tooltip" data-placement="bottom" title="Pulsa aquí para deshabilitar obras">Gestionar
							Obras</a> <a id='eliminardeshabilitado' class="btn btn-danger" href='EED' role="button" data-toggle="tooltip" data-placement="bottom" title="Pulsa aquí para eliminar autores y obras deshabilitadas">Eliminar Elementos Deshabilitados</a></center>
					</div>
				</c:when>
				<c:otherwise>
					<h2>No estas logeado</h2>
				</c:otherwise>
			</c:choose>

</div>
		</div>
	</div>
	
</body>
</html>