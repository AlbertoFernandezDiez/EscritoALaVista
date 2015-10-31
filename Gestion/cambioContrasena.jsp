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

<title>Cambiar Contraseña Administrador</title>

<script>
	$(document).ready(function() {
		$('span').hide();
		$('#cambiar').on('click', cambiarContrasenaAdmin);
		$('[data-toggle="tooltip"]').tooltip();
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
							data-toggle="tooltip" data-placement="bottom"
							title="La contraseña debe tener más de 6 carácteres, puede incluir a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-"
							name="contraV" type="password"
							placeholder="Introduce la vieja contraseña" required='true'><br>
						<label for="contraN">Contraseña nueva :</label> <input
							id="contraN" class='form-control'
							pattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{6,}"
							data-toggle="tooltip" data-placement="bottom"
							title="La contraseña debe tener más de 6 carácteres, puede incluir a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-"
							name="contraN" type="password"
							placeholder="Introduce la nueva contraseña" required='true'><br>

						<button type="button" id='cambiar'
							class="btn btn-success center-block">Cambiar</button>
						
						<div class="modal fade" id="ok" role="dialog">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Aviso</h4>
									</div>
									<div class="modal-body">
										<p>La contraseña se ha cambiado correctamente</p>
									</div>
									<div class="modal-footer">
										<button id='botonok' type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade" id="bad" role="dialog">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Error</h4>
									</div>
									<div class="modal-body">
										<p>No se ha podido modificar la contraseña</p>
									</div>
									<div class="modal-footer">
										<button id='botonbad' type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade" id="fail" role="dialog">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Error</h4>
									</div>
									<div class="modal-body">
										<p>No has completado los campos o no tienen más de 6 caracteres</p>
									</div>
									<div class="modal-footer">
										<button id='botonfail' type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
					
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