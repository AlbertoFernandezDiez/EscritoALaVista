<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset='UTF-8'>
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<script src="js/loadSelect.js"></script>

<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/filedrag.css">
<script src="js/filedrag.js"></script>

<!-- MENSAJE COOKIES -->
<script src="js/Cookies.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/Cookies.css">

<script src="js/validations.js"></script>

<title>Upload Book</title>

<script>
var correcto = false;

$().ready(function(){
$("#usado").hide();
	
	$('#titOb').on('blur',checkTitulo);
	$('#titOb').on('focus',function(){
		$('#comprobacionTitulo').removeClass("has-success");
		$('#comprobacionTitulo').removeClass("has-error");
		
		presubmitBook();
});
});
</script>

</head>
<body>

	<jsp:include page="menu.jsp" />
	<!-- Modal para mostrar error tipo imagen -->
	<jsp:include page="fileError.jsp" />
	<!-- Mensaje de aviso de cookies -->
	<jsp:include page="Cookies.html" />

	<!-- <div class="container theme-showcase" role="main"> -->

	<div class='jumbotron'>

		<div>
			<jsp:include page="breadCrumb.jsp" />
		</div>

		<div class="form-group">
			<form action="uploadBookChapter" method="POST"
				enctype="multipart/form-data" class="form-group" id='obra'>

				<input type='hidden' name='autor' class='form-control' id='autor'
					value='<c:out value="${requestScope.autor}"></c:out>'> <select
					id="selectObra" class='form-control' name="selectObra" size=1
					onChange="loadChapterSel($('#selectObra').val())">
					<option name=default value=0>Nueva Obra</option>
					<c:forEach items="${requestScope.obras}" var="ob">
						<option name="<c:out value="${ob.titulo}"></c:out>"
							value="<c:out value="${ob.id}"></c:out>"><c:out
								value="${ob.titulo}"></c:out></option>
					</c:forEach>
				</select> <select id="selectCapitulo" class='form-control'
					name="selectCapitulo" size=1
					onChange="loadChapter($('#selectCapitulo').val())">>
					<option name=default value=0>Nuevo Capitulo</option>
				</select> <br> <label for="titOb">Titulo Obra :</label>
				<div id='comprobacionTitulo' class='form-group'>

					<input type="text" id="titOb" name="titOb" pattern="[a-zA-Z0-9 ]+"
						title="No se aceptan acentos y ñ en el titulo"
						class='form-control' maxlength='50'> <br>
					<span id='usado' class="alert alert-danger" role="alert">Este
						Titulo ya esta siendo usado</span>
				</div>
				<label for="resumen">Resumen :</label>
				<textarea rows="5" cols="20" class='form-control' id="resumen"
					name="resumen" spellcheck="true" maxlength='512'></textarea>
				<div class="custom-input-file">
					<input id="fileObra" class='input-file' type="file" name="fileObra"
						size="50" accept='image/*' />Clicka o arrastra imagenes aquí
				</div>
				<span id='fileObrainfo'> </span>
				<script>
					gestorArchivos('fileObra');
				</script>
				<br> <label for="titCap">Titulo Capitulo :</label> <input
					type="text" class='form-control' id="titCap" name="titCap"
					maxlength='50' spellcheck="true"> <br> <label
					for="capitulo">Capitulo :</label>
				<textarea rows="5" class='form-control' cols="20" id="capitulo"
					name="capitulo" spellcheck="true"></textarea>
				<br> <label for="comentarios" spellcheck="true">Comentarios
					:</label>
				<textarea rows="5" class='form-control' cols="20" id="comentarios"
					name="comentarios" spellcheck="true"></textarea>
				<div class="custom-input-file">
					<input id="fileCapi" class='input-file' type="file" name="fileCapi"
						size="50" accept='image/*' /> Clicka o arrastra imagenes aquí
				</div>
				<span id='fileCapiinfo'> </span>
				<script>
					gestorArchivos('fileCapi');
				</script>
				<br> <input type="submit" class='btn btn-default'
					value="Enviar" />
			</form>
		</div>
	</div>
	</div>

</body>
</html>