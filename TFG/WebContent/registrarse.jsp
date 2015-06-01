<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrarse</title>
<script src="js/sha512.js"></script>
<script src="js/validations.js"></script>

<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>

</head>
<body>

	<jsp:include page="menu.jsp" />


	<div class="container theme-showcase" role="main">
		<div class='jumbotron'>
			<div class="form-group">
				<form id='registro' method="POST" action="Registrar"
					enctype="multipart/form-data" role='form'>

					<label for="usuario">Nombre de usuario :</label> <input
						id="usuario" name="usuario" class='form-control' type="text"
						placeholder="usuariodeprueba" required='true'><br> <label
						for="email">Email :</label> <input id="email" name="email"
						type="email" class='form-control' placeholder="ejemplo@ejemplo.com" required='true'><br>
					<label for="contrasena1">Contraseña :</label> <input
						id="contrasena1" class='form-control' pattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{6,}"
						title="Six or more characters" name="contrasena1" type="password"
						placeholder="Introduce una contraseña" required='true'><br>
					<label for="contrasena2">Contraseña :</label> <input
						id="contrasena2" class='form-control' name="contrasena2" type="password"
						placeholder="Repite la contraseña"
						pattern="[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{6,}"
						title="Six or more characters" class='form-control' required='true'><br> <label
						for="pais">Pais :</label> <input id="pais" name="pais" type="text"
						placeholder="ej:Francia" required='true'><br> <label
						for="nacimiento">Fecha de nacimiento :</label> <input
						id="nacimiento" class='form-control' name="nacimiento" type="date"
						pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
						placeholder='yyyy-mm-dd' required='true'><br> <label
						for="about">Habla sobre ti :</label><br>
					<textarea id="about" name="about" rows="4" cols="50"
						required='true' class='form-control' spellcheck="true"></textarea>
					<br> <input id='file' name='file' type='file' accept='image/*'><br>
					<input id='submit' name='submit' type='submit'>

					<script>
presubmit();
</script>
				</form>
			</div>
		</div>
	</div>
</body>
</html>