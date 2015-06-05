<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta charset="UTF-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<c:set var="autor" value="${requestScope.autor}" scope="request"></c:set>
<title><c:out value="${autor.nombre}"></c:out></title>
<link rel="stylesheet" href="css/style.css">


<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>

</head>
<body>

	<jsp:include page="menu.jsp" />

	<!-- <div class="container theme-showcase" role="main"> -->

	<div class='jumbotron'>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h1 class="panel-title">
					<c:out value="${autor.nombre}"></c:out>
				</h1>
			</div>
			<div class="panel-body">
				<h2>
					Pais:
					<c:out value="${autor.pais}"></c:out>
				</h2>
				<h2>
					Nacimiento:
					<c:out value="${autor.nacimiento}"></c:out>
				</h2>
				<img class="img-rounded perfilImg" alt="Foto de perfil"
					src="<c:out value="${autor.imagen}"></c:out>">
				<p class="text-justify">
					<c:out value="${autor.about}"></c:out>
				</p>
			</div>
		</div>



		<div class="panel panel-primary">
			<div class="panel-heading">
				<h1 class="panel-title">Mis Obras</h1>
			</div>
			<div class="panel-body obras">
				<c:forEach items="${requestScope.obras}" var="ob">
					<div class="row custom">
						<div class="col-sm-2"></div>
						<div class="col-sm-4">
							<a href="VerHistoria?op=0&hi=<c:out value="${ob.id}"></c:out>"><c:out
									value="${ob.titulo}"></c:out></a>
						</div>
						<div class="col-sm-2">
								<fmt:formatDate value="${ob.fecha_in}" pattern="dd/MM/yyyy" />	
						</div>
						<div class="col-sm-2">
							<fmt:formatDate value="${ob.fecha_mod}" pattern="dd/MM/yyyy" />
						</div>
						<div class="col-sm-2">
							<a href="VerAutor?autor=<c:out value="${ob.autor}"></c:out>"><c:out
									value="${autor.nombre}"></c:out></a>
						</div>
						<div class="col-sm-2">
							<img alt='Imagen de perfil'
								src='<c:out value="${ob.portada}"></c:out>'>
						</div>
						<div class="col-sm-10">
							<p class="text-justify">
								<c:out value="${ob.resumen}"></c:out>
							</p>
						</div>
					</div>
					<br>
				</c:forEach>

			</div>
		</div>
	</div>
	</div>
</body>
</html>