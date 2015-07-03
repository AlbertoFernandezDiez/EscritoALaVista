<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title><c:out value="${requestScope.tit}"></c:out></title>
<link rel="stylesheet" href="css/style.css">

<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>


<!-- MENSAJE COOKIES -->
<script src="js/Cookies.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/Cookies.css">

<script>
	$(document).ready(function() {
		$("#exportar").hide();
		$("#setTipo").hide();
		$("#formtitle").click(function() {
			$("#exportar").toggle(500);
		});
		$("#ok").hide();
		$("#bad").hide();
		$('#seguir').on('change', seguirHistoria);
	});

	function sendComment() {
		var obra = $('#obra').val();
		var capitulo = $('#capitulo').val();
		var texto = $('#comentario').val();

		$.ajax({
			url : "UC",
			type : 'POST',
			data : {
				obra : obra,
				capitulo : capitulo,
				texto : texto
			},
			success : function(result) {

				if (result == 'false') {
					$("#ok").hide(200);
					$("#bad").show(200);
				} else {
					$("#ok").show(200);
					$("#bad").hide(200);
					$('#comentario').val('');
					$('#comentarios hr').remove();
					$('#comentarios .row').remove();
					console.log(result);
					var jSonArray = $.parseJSON(result);

					for ( var json in jSonArray) {

						$('#comentarios').append(
								'<div class="row"><div class="col-sm-4"><a href="VerAutor?autor='
										+ jSonArray[json].autor + '">'
										+ jSonArray[json].nombre
										+ '</a></div><div class="col-sm-8">'
										+ jSonArray[json].fechaComentario
										+ '</div><div class="col-sm-12">'
										+ jSonArray[json].texto
										+ '</div></div><hr>');

					}
				}
			},
			error : function(request, error) {
				$("#ok").hide(200);
				$("#bad").show(200);
			}
		});

	}

	function seguirHistoria() {
		var obra = $('#obra').val();
		var checked = $('#seguir').is(':checked');
		console.log(checked);

		$.ajax({
			url : "SH",
			type : 'POST',
			data : {
				obra : obra,
				checked : checked,
			},
			success : function(result) {

				if (result == 'false') {
					$("#ok").hide(200);
					$("#bad").show(200);
				} else {
					$("#ok").show(200);
					$("#bad").hide(200);
				}
			},
			error : function(request, error) {
				$("#ok").hide(200);
				$("#bad").show(200);
			}
		});
	}
</script>

</head>
<body>
	<jsp:include page="menu.jsp" />

	<!-- Mensaje de aviso de cookies -->
	<jsp:include page="Cookies.html" />

	<div class='jumbotron'>

		<div>
			<jsp:include page="breadCrumb.jsp" />
		</div>

		<input id='obra' type="hidden"
			value='<c:out value="${requestScope.id}"></c:out>' /> <input
			id='capitulo' type="hidden"
			value='<c:out value="${requestScope.chapter}"></c:out>' />
		<button type="button" class="btn btn-default" id='formtitle'
			value='Exportar'>Exportar</button>

		<div class="checkbox pull-right">
			<label> <input type="checkbox" id='seguir' value="Seguir"
				<c:if test="${empty requestScope.userId}">disabled</c:if>
				<c:if test="${requestScope.checked}">checked</c:if> /> Seguir
			</label>
		</div>



		<form id='exportar' name='exportar' class="form" method='GET'
			role="form" action='Itext'>
			<div class="form-group">
				<label class="radio-inline"><input type="radio"
					name="formato" value="Itext" checked="checked">Itext</label> <label
					class="radio-inline"><input type="radio" name="formato"
					value="EpubCreator">Epub</label> <label class="radio-inline"><input
					type="radio" name="formato" value="LatexCreator">Latex</label> <label
					class="radio-inline"><input type="hidden" name="id"
					value="<c:out value="${requestScope.id}" ></c:out>"></label> <input
					type='submit' class="btn btn-default" name='exportar'
					value='Exportar'>
			</div>
			<div class="form-group" id='setTamano'>
				<h4>Selecciona el tamaño</h4>
				<label class="radio-inline"><input type="radio"
					name="tamano" value="small" checked="checked">Pequeño</label> <label
					class="radio-inline"><input type="radio" name="tamano"
					value="big">Grande</label>
			</div>

			<div class="form-group" id='setTipo'>
				<h4>Selecciona el formato</h4>
				<label class="radio-inline"><input type="radio" name="tipo"
					value="book" checked="checked">Libro</label> <label
					class="radio-inline"><input type="radio" name="tipo"
					value="report">Informe</label>
						<label> <input type="checkbox" name='libro' id='libro' value="true"/>Formato libro</label>
			</div>
		</form>

		<script>
			$("input[name=formato]:radio").change(function() {
				$("#exportar").attr('action', $(this).val());
				if ($(this).val() == "EpubCreator") {
					$('#setTamano').hide(500);
					$('#setTipo').hide(500);
				} 
				if ($(this).val() == "LatexCreator") {
					$('#setTamano').hide(500);
					$('#setTipo').show(500);
				} else {
					$('#setTipo').hide(500);
				}
				if ($(this).val() == "Itext") {
					$('#setTamano').show(500);
					$('#setTipo').hide(500);
				} else {
					$('#setTamano').hide(500);
				}
			});
		</script>
		<div class="row">

			<div class="col-sm-2">

				<div class="list-group">
					<h3>
						<a href="#">Capitulos:</a>
					</h3>
					<c:forEach items="${requestScope.chapterList}" var="emp">
						<c:choose>
							<c:when test="${emp.id eq requestScope.chapter}">
								<c:set var="chap" value="${emp}" scope="request"></c:set>
								<a class="list-group-item active"
									href="VerHistoria?op=1&hi=<c:out value="${requestScope.id}"></c:out>&ca=<c:out value="${emp.id}"></c:out>"><c:out
										value="${emp.nombre}"></c:out></a>
							</c:when>
							<c:otherwise>
								<a class="list-group-item"
									href="VerHistoria?op=1&hi=<c:out value="${requestScope.id}"></c:out>&ca=<c:out value="${emp.id}"></c:out>"><c:out
										value="${emp.nombre}"></c:out></a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="col-sm-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h1>
							<c:out value="${chap.nombre}"></c:out>
						</h1>
					</div>
					<div class="panel-body">
						<c:forEach items="${chap.text}" var="par">
							<p>
								<c:out value="${par}"></c:out>
							</p>
						</c:forEach>

						<c:if test="${chap.imagen ne null}">
							<img alt="imagen capítulo" class="img-rounded perfilImg"
								src="<c:out value="${chap.imagen}"></c:out>">
						</c:if>
					</div>
					<div class="panel-footer">
						<c:out value="${chap.comentarios_autor }"></c:out>
					</div>
				</div>
				<div id='zonaComentarios'>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3>Comentarios</h3>
						</div>
						<div class="panel-body">
							<c:choose>
								<c:when test="${not empty requestScope.userId}">
									<form id='formcomentarios' class="form-inline" role="form">
										<div class="form-group">
											<textarea id="comentario" name="comentario" rows="4"
												cols='100' required='true' class='form-control'
												placeholder="Escribe aquí tu comentario" spellcheck="true"></textarea>
										</div>
										<button type="button" onclick='sendComment()'
											class="btn btn-default">Comentar</button>

									</form>
									<div id='ok' class="alert alert-success" role="alert">
										<strong>Enhorabuena! </strong> ,tu comentario se ha enviado
										correctamente
									</div>
									<div id='bad' class="alert alert-danger" role="alert">
										<strong>Ohh!</strong>, ha habido un error y tu comentario no
										se ha podido guardar
									</div>

								</c:when>
								<c:otherwise>
					Debes identificarte para poder realizar comentarios
					</c:otherwise>
							</c:choose>
							<div id='comentarios'>
								<hr>
								<c:forEach items="${requestScope.comentarios}" var="com">
									<div class="row">
										<div class="col-sm-4">

											<a href="VerAutor?autor=<c:out value="${com.autor}"></c:out>"><c:out
													value="${autor[com.autor]}"></c:out></a>
										</div>
										<div class="col-sm-8">
											<fmt:formatDate value="${com.fecha_comentario}"
												pattern="dd/MM/yyyy hh:mm" />

										</div>
										<div class="col-sm-12">
											<c:out value="${com.texto}"></c:out>

										</div>
									</div>
									<hr>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>