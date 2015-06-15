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

</head>
<body>
	<!-- Menu -->
	<jsp:include page="menu.jsp" />
	<!-- Modal para mostrar error tipo imagen -->
	<jsp:include page="fileError.jsp" />
	<!-- Mensaje de aviso de cookies -->
	<jsp:include page="Cookies.html" />

	<div class='jumbotron'>
		<h3>Modifica tus datos personales</h3>
		<div class="form-group">
			<form id='registro' method="POST" action="CambiarDatos"
				enctype="multipart/form-data" role='form'>
				<label for="email">Email :</label> <input id="email" name="email"
					type="email" class='form-control' placeholder="ejemplo@ejemplo.com"
					value='<c:out value="${requestScope.autor.email}"></c:out>'
					required='true'><br> <label for="pais">Pais :</label>
				<input id="pais" name="pais" type="text" placeholder="ej:Francia"
					value='<c:out value="${requestScope.autor.pais}"></c:out>'
					required='true'><br> <label for="about">Habla
					sobre ti :</label><br>
				<textarea id="about" name="about" rows="4" cols="50" required='true'
					class='form-control' spellcheck="true"><c:forEach items="${requestScope.autor.about}" var="par"><c:out value="${par}"></c:out></c:forEach></textarea>
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
				<input id='submit' name='submit' type='submit'>

				<script>
					presubmit();
				</script>
			</form>
<c:out value="${requestScope.autor.email}"></c:out>
		</div>
	</div>
	</div>
</html>