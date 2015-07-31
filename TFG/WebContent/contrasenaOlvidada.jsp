<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

<title>Recuperar Contraseña</title>

<script>
	function contrasenaOlvidada() {
		var email = $('#email').val();

		if (email == '')
			return;
		
		$.ajax({
			url : "api/ContrasenaOlvidadaAPI",
			type : 'POST',
			dataType : "json",
			data : {
				email : email
			},
			success : function(result) {
				if (result.value == true) {
					$('#cambioCorrecto').modal('show');
				} else {
					$('#cambioIncorrecto').modal('show');
				}

			},
			error : function() {
				alert('error');
			}
		});
	}

	$().ready(function() {
		$('#enviar').on('click', contrasenaOlvidada);
	});
</script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<!-- Mensaje de aviso de cookies -->
	<jsp:include page="Cookies.html" />
	<!-- <div class="container theme-showcase" role="main"> -->

	<div class='jumbotron'>
		<div class="modal fade" id="cambioCorrecto" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Aviso!!</h4>
					</div>
					<div class="modal-body">
						<p>En breve recibiras en tu correo la nueva contraseña,
							recuerda cambiarla desde la pantalla de modificación de perfil.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">Aceptar</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="cambioIncorrecto" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Error!!</h4>
					</div>
					<div class="modal-body">
						<p>El correo que has introducido no existe en nuestra Base de
							Datos, revisa el email.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Aceptar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="email">Email :</label> <input id="email"
				class='form-control' name="email" type="email"
				placeholder="example@example.com" required='true'> <br>
			<button class="btn btn-default" id='enviar' type="button">Enviar</button>

		</div>
	</div>
	</div>
	<script>
		
	</script>
</body>
</html>