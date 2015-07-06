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



<title>Gestionar Obras</title>

<script>
	function clickHabilitarObra() {
		var id = $(this).val();

		$('#confirmHabilitar').modal({
			backdrop : 'static',
			keyboard : false
		}).one('click', '#delete', function(e) {
			habilitarObra(id);
		});

		return false;
	}

	function habilitarObra(id) {
		$.ajax({
			url : "HDOA",
			type : 'POST',
			data : {
				id : id,
				tipo : 1,
				opcion : 1
			},
			success : function(result) {
				if (result == 'true')
					location.reload();
			},
			error : function(request, error) {

			}
		});
	}

	function clickDeshabilitarObra() {
		var id = $(this).val();

		$('#confirmDeshabilitar').modal({
			backdrop : 'static',
			keyboard : false
		}).one('click', '#delete', function(e) {
			deshabilitarObra(id);

		});
		return false;
	}

	function deshabilitarObra(id) {
		console.log('he entrado');
		$.ajax({
			url : "HDOA",
			type : 'POST',
			data : {
				id : id,
				tipo : 1,
				opcion : 0
			},
			success : function(result) {
				console.log(result);
				if (result == 'true')
					location.reload();
			},
			error : function(request, error) {

			}
		});
	}
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
						<p>¿Quieres eliminar la Obra?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-primary"
							id="delete">Eliminar</button>
						<button type="button" data-dismiss="modal" class="btn">Cancelar</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="confirmHabilitar" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">¿Estas seguro?</h4>
					</div>
					<div class="modal-body">
						<p>¿Quieres habilitar la Obra?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-primary"
							id="delete">Habilitar</button>
						<button type="button" data-dismiss="modal" class="btn">Cancelar</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="confirmDeshabilitar" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">¿Estas seguro?</h4>
					</div>
					<div class="modal-body">
						<p>¿Quieres deshabilitar la Obra?</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-primary"
							id="delete">Desabilitar</button>
						<button type="button" data-dismiss="modal" class="btn">Cancelar</button>
					</div>
				</div>
			</div>
		</div>

		<c:set var="autor" value="${requestScope.autores}" scope="request"></c:set>

		<div class='jumbotron'>
			<table class="table table-hover">
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
							<td><c:choose>
									<c:when test="${aut.active == 1}">
										<button type="button" class='btn btn-danger deshabilitar active'
											value='<c:out value="${aut.id}"></c:out>'>Deshabilitar</button>
											<button type="button" class='btn btn-success habilitar disabled'
											value='<c:out value="${aut.id}"></c:out>'>Habilitar</button>
									</c:when>
									<c:when test="${aut.active == 0}">
									<button type="button" class='btn btn-danger deshabilitar disabled'
											value='<c:out value="${aut.id}"></c:out>'>Deshabilitar</button>
										<button type="button" class='btn btn-success habilitar active'
											value='<c:out value="${aut.id}"></c:out>'>Habilitar</button>
									</c:when>
								</c:choose>
								<!-- <button type="button" class='btn btn-default eliminar'
									value='<c:out value="${aut.id}"></c:out>'>Eliminar</button>--></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<script>
				$('.eliminar').on('click', clickEliminarObra);
				$('.habilitar').on('click', clickHabilitarObra);
				$('.deshabilitar').on('click',clickDeshabilitarObra);
			</script>

		</div>
	</div>
	<div id="confirm" class="modal hide fade" role="dialog">
		<div class="modal-content">
			<div class="modal-body">Delete?</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary"
					id="delete">Delete</button>
				<button type="button" data-dismiss="modal" class="btn">Cancel</button>
			</div>
		</div>
	</div>
	</div>
</body>
</html>