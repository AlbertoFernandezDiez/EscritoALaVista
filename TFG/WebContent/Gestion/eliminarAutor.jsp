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

<!-- EMODAL -->
<script src="js/eModal.min.js"></script>

<title>EliminarObra</title>

<script>

	function doActionTrue(res,id) {
		
		$.ajax({
			url : "EU",
			type : 'POST',
			data : {
				id : id
			},
			success : function(result) {
if (result == true)
	$('tr#' + id).remove();
			},
			error : function(request, error) {

			}
		});
		//Eliminamos la linea
		
	}

	function doActionFalse() {

	}

	function clickEliminarObra() {
		var id = $(this).val();
		var options = {
			message : "¿Deseas eliminar el usuario y sus obras?",
			title : '¿Es cierto?',
			size : 'sm',
			callback : function(result) {
				result ? doActionTrue(result,id) : doActionFalse();
			},
			label : "Aceptar" // use the possitive lable as key
		//...
		};

		eModal.confirm(options);
		//  callback: function(result) { result ? doActionTrue(result) :    doActionFalse(); },

		return false;
	}
</script>

</head>
<body>

	<jsp:include page="../Cookies.html" />
	<!-- <div class="container theme-showcase" role="main"> -->

	<div class="container theme-showcase" role="main">

		<jsp:include page="title.jsp" />


		<div class='jumbotron'>
			<table class="table table-hover">
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
									pattern="dd/MM/yyyy" /> </td>
							<td><c:out value="${aut.email}"></c:out></td>

							<td><button type="button" class='btn btn-default'
									value='<c:out value="${aut.id}"></c:out>'>Eliminar</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<script>
				$('.btn').on('click', clickEliminarObra);
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