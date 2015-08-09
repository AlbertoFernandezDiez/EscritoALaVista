<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>

<!-- MENSAJE COOKIES -->
<script src="js/Cookies.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/Cookies.css">



<title>Eliminar Elementos Deshabilitados</title>

<script>
	function eliminarAutor(id) {

		$.ajax({
			url : "EU",
			type : 'POST',
			data : {
				id : id
			},
			success : function(result) {
				if (result == 'true')
					$('tr#' + id).remove();
			},
			error : function(request, error) {

			}
		});
		//Eliminamos la linea

	}

	function clickEliminarAutor() {
		var id = $(this).val();

		$('#confirm').modal({
			backdrop : 'static',
			keyboard : false
		}).one('click', '#delete', function(e) {
			eliminarAutor(id);
		});

		return false;
	}

	function eliminarObra(id) {

		$.ajax({
			url : "EO",
			type : 'POST',
			data : {
				id : id
			},
			success : function(result) {
				if (result == 'true')
					$('tr#' + id).remove();
			},
			error : function(request, error) {

			}
		});
	}

	function clickEliminarObra() {
		var id = $(this).val();

		$('#confirm').modal({
			backdrop : 'static',
			keyboard : false
		}).one('click', '#delete', function(e) {
			eliminarObra(id);
		});

		return false;
	}

	$(document).ready(function() {
		$('table').hide();
		$('#botonAutor').on('click', function() {
			$('#autores').toggle(500);
		});
		$('#botonObra').on('click', function() {
			$('#obras').toggle(500);
		});
			$('[data-toggle="tooltip"]').tooltip();
	});
</script>

</head>
<body>

	<jsp:include page="../Cookies.html" />
	<!-- <div class="container theme-showcase" role="main"> -->



	<div class="container theme-showcase" role="main">

		<jsp:include page="title.jsp" />

		<div class="modal fade" id="confirm" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">¿Estas seguro?</h4>
					</div>
					<div class="modal-body">
						<p>¿Quieres eliminar el Autor?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-primary"
							id="delete">Eliminar</button>
						<button type="button" data-dismiss="modal" class="btn">Cancelar</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="confirmObra" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">¿Estas seguro?</h4>
					</div>
					<div class="modal-body">
						<p>¿Quieres eliminar la Obra ?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-primary"
							id="delete">Habilitar</button>
						<button type="button" data-dismiss="modal" class="btn">Cancelar</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="confirmAutor" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">¿Estas seguro?</h4>
					</div>
					<div class="modal-body">
						<p>¿Quieres eliminar el Autor?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-primary"
							id="delete">Desabilitar</button>
						<button type="button" data-dismiss="modal" class="btn">Cancelar</button>
					</div>
				</div>
			</div>
		</div>

		<div class='jumbotron'>
			<div id='botonAutor' data-toggle="tooltip" data-placement="left" title="Pulsa aquí para desplegar la lista de usuarios deshabilitados">
				<h3>
					Autores Deshabilitados <span class="glyphicon glyphicon-plus"></span>
				</h3>
			</div>
			<table class="table table-hover" id='autores'>
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Pais</th>
						<th>Nacimiento</th>
						<th>Email</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="aut" items="${requestScope.autores}">
						<tr id='<c:out value="${aut.id}"></c:out>'>
							<td><c:out value="${aut.nombre}"></c:out></td>
							<td><c:out value="${aut.pais}"></c:out></td>
							<td><fmt:formatDate value="${aut.nacimiento}"
									pattern="dd/MM/yyyy" /></td>
							<td><c:out value="${aut.email}"></c:out></td>

							<td><button type="button"
									class='btn btn-danger eliminarAutor'
									value='<c:out value="${aut.id}"></c:out>' data-toggle="tooltip" data-placement="bottom" title="Pulsa aquí para eliminar el usuario">Eliminar</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<script>
				$('.eliminarAutor').on('click', clickEliminarAutor);
			</script>
			<c:set var="autor" value="${requestScope.autoresHash}" scope="request"></c:set>

			<div id='botonObra' data-toggle="tooltip" data-placement="left" title="Pulsa aquí para desplegar la lista de obras deshabilitadas">
				<h3>
					Obras Deshabilitadas <span class="glyphicon glyphicon-plus"></span>
				</h3>
			</div>
			<table class="table table-hover" id='obras'>
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Autor</th>
						<th>Fecha Inicio</th>
						<th>Ultima modificación</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="aut" items="${requestScope.obras}">
						<tr id='<c:out value="${aut.id}"></c:out>'>
							<td><c:out value="${aut.titulo}"></c:out></td>
							<td><c:out value="${autor[aut.autor]}"></c:out></td>
							<td><fmt:formatDate value="${aut.fecha_in}"
									pattern="dd/MM/yyyy" /></td>
							<td><fmt:formatDate value="${aut.fecha_mod}"
									pattern="dd/MM/yyyy" /></td>
							<td><button type="button"
									class='btn btn-danger eliminarObra'
									value='<c:out value="${aut.id}"></c:out>' data-toggle="tooltip" data-placement="bottom" title="Pulsa aquí para eliminar la obra">Eliminar</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<script>
				$('.eliminarObra').on('click', clickEliminarObra);
			</script>
		</div>
	</div>


	</div>
</body>
</html>