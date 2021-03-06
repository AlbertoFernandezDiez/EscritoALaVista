<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modificar Datos</title>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>

<script src="js/sha512.js"></script>
<script src="js/validations.js"></script>


<link rel="stylesheet" href="css/filedrag.css">
<script src="js/filedrag.js"></script>

<!-- MENSAJE COOKIES -->
<script src="js/Cookies.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/Cookies.css">

<script>

	var emailAntiguo; 

function checkEmail(){
	var email = $('#email').val();
	console.log(email);
	if (email != '' && email != emailAntiguo)
	{
		$.ajax({
			url : "api/ComprobarEmailAPI",
			type : 'POST',
			dataType: "json",
			data : {
				email : email
			},
			success : function(result) {
				if (result.value == true) {
					$('#comprobacionEmail').addClass("has-success"); 
					$('#usadoEmail').hide();
					correcto = true;
				} else {
					$('#usadoEmail').show();
					$('#comprobacionEmail').addClass("has-error");  
					correcto = false;
				}
			},
			error : function(request, error) {
				$("#bad").show(200);
			}
		}
		);
	}
	if (email == emailAntiguo){
	$('#comprobacionEmail').addClass("has-success"); 
	$('#usadoEmail').hide();
	}
}

	$().ready(function() {
		$('span').hide();
		$('span#fileselectinfo').show();
		$('#cambiar').on('click', cambiarContrasena);
		$('[data-toggle="tooltip"]').tooltip();
	
		emailAntiguo = $('#email').val()
		
		$('#email').on('blur', checkEmail);
		$('#email').on('focus', function() {
			$('#comprobacionEmail').removeClass("has-success");
			$('#comprobacionEmail').removeClass("has-error");
		});

	});
</script>
</head>
<body>
	<!-- Menu -->
	<jsp:include page="menu.jsp" />
	<!-- Modal para mostrar error tipo imagen -->
	<jsp:include page="fileError.jsp" />
	<!-- Mensaje de aviso de cookies -->
	<jsp:include page="Cookies.html" />

	<div class='jumbotron'>

	<div>
	<jsp:include page="breadCrumb.jsp" />
	</div>

		<div class="form-group">
			<form id='cambioContra'>
				<h3>Modifica tu contraseña</h3>
				<label for="contrasena1">Contraseña antigua :</label> <input
					id="contraV" class='form-control'
					pattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{6,}"
					name="contraV" type="password"
					placeholder="Introduce la vieja contraseña" required='true' data-toggle="tooltip" data-placement="bottom"
				title="La contraseña debe tener más de 6 carácteres, puede incluir a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-"><br>
				<label for="contrasena1">Contraseña nueva :</label> <input
					id="contraN" class='form-control'
					pattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{6,}"
					 name="contraN" type="password"
					placeholder="Introduce la nueva contraseña" required='true' data-toggle="tooltip" data-placement="bottom"
				title="La contraseña debe tener más de 6 carácteres, puede incluir a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-"><br>

				<button type="button" id='cambiar' class="btn btn-danger center-block" data-toggle="tooltip" data-placement="bottom"
				title="Pulsa aquí para cambiar tu contraseña">Cambiar</button>

				<span id='ok' class="alert alert-success" role="alert">Contraseña
					modificada</span> <span id='bad' class="alert alert-danger" role="alert">Error,
					no se ha podido modificar la contraseña</span> <span id='ok'
					class="alert alert-success" role="alert">Contraseña
					modificada</span> <span id='fail' class="alert alert-info" role="alert">Error,
					no has completado los campos o no tienen más de 6 caracteres</span>
		</div>
		</form>
		<h3>Modifica tus datos personales</h3>
		<div class="form-group">
			<form id='registro' method="POST" action="CambiarDatos"
				enctype="multipart/form-data" role='form'>
				<div id='comprobacionEmail' class='form-group'>
				<label for="email">Email :</label><br> <input id="email" name="email"
					type="email" class='form-control' placeholder="ejemplo@ejemplo.com"
					value='<c:out value="${requestScope.autor.email}"></c:out>'
					required='true' data-toggle="tooltip" data-placement="bottom"
				title="Escribe tu email"><br>
				<span id='usadoEmail' class="alert alert-danger" role="alert">Este
						email ya esta siendo utilizado</span>
				</div>
				 <label for="pais">Pais :</label>
				<input id="pais" name="pais" type="text" placeholder="ej:Francia"
					value='<c:out value="${requestScope.autor.pais}"></c:out>'
					required='true' data-toggle="tooltip" data-placement="bottom"
				title="Escribe el nombre del pais donde vives"><br> <label for="about">Habla
					sobre ti :</label><br>
				<textarea id="about" name="about" rows="4" cols="50" required='true'
					class='form-control' spellcheck="true" data-toggle="tooltip" data-placement="bottom"
				title="Cuenta algo sobre ti"><c:forEach
						items="${requestScope.autor.about}" var="par"><c:out value="${par}"></c:out>
					</c:forEach></textarea>
				<br>
				<!-- <input id='file' name='file' type='file' accept='image/*'><br>-->
				<div>
					<!-- <label for="fileselect">Elige los ficheros a subir:</label>-->
					<h4>Modifica tu imagen de perfil</h4>
					<div class="custom-input-file">
						<input type="file" class="input-file" id="fileselect" name='file'
							accept='image/*' /> Clicka o arrastra imagenes aquí
					</div>
					<span id='fileselectinfo'> </span>
					<script>
						gestorArchivos('fileselect');
					</script>
				</div>
				<input id='submit' name='submit' type='submit'
					value='Enviar' class="btn btn-success center-block" >

				<script>
				//	presubmit();
				</script>
			</form>
		</div>
	</div>
	</div>
</html>