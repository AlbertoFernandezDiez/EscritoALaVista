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

<title>EliminarObra</title>

<script>

function clickEliminarObra()
{
	alert($(this).val());
	
	
	//Eliminamos la linea
	$(this).closest('tr').remove();
    return false;
	}

</script>

</head>
<body>

	<jsp:include page="../Cookies.html" />
	<!-- <div class="container theme-showcase" role="main"> -->

	<div class="container theme-showcase" role="main">

		<jsp:include page="title.jsp" />

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
						<tr>
							<td><c:out value="${aut.titulo}"></c:out></td>
							<td><c:out value="${autor[aut.autor]}"></c:out></td>
							<td><fmt:formatDate value="${aut.fecha_in}"
									pattern="dd/MM/yyyy" />
								</t>
							<td><fmt:formatDate value="${aut.fecha_mod}"
									pattern="dd/MM/yyyy" /></td>
							<td><button type="button" class='btn btn-default'
									value='<c:out value="${aut.autor}"></c:out>'
									>Eliminar</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<script>
			$('.btn').on('click',clickEliminarObra);
			</script>

		</div>
	</div>

</body>
</html>