<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset='utf8'>
<c:set var="autor" value="${requestScope.autor}" scope="request"></c:set>
<title><c:out value="${autor.nombre}"></c:out></title>
<link rel="stylesheet" href="css/style.css">

<style>
/*
.perfilImg {
	clear: right;
	float: right;
	margin-left: 10px;
	margin-right: 5px;
	padding: 10px;
	display: block;
	max-height: 240px;
	max-width: 320px;
}

.col-sm-4 img {
	max-height: 140px;
	max-width: 130px;
}

.row{
 border-style: solid;
  border-width: 2px;
   border-radius: 15px 15px;
   width:98%; 
}

.obras{
 margin-left : 1%;
margin-right : 1%;
padding : 0px;
}*/

</style>
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
						<div class="row">
							<div class="col-sm-4" ></div>
							<div class="col-sm-2" >
								<a href="VerHistoria?op=0&hi=<c:out value="${ob.id}"></c:out>"><c:out
										value="${ob.titulo}"></c:out></a>
							</div>
							<div class="col-sm-2" >
								<c:out value="${ob.fecha_in}"></c:out>
							</div>
							<div class="col-sm-2" >
								<c:out value="${ob.fecha_mod}"></c:out>
							</div>
							<div class="col-sm-2" >
								<a href="VerAutor?autor=<c:out value="${ob.autor}"></c:out>"><c:out
										value="${autor.nombre}"></c:out></a>
							</div>
							<div class="col-sm-4" >
								<img alt='Imagen de perfil'
									src='<c:out value="${ob.portada}"></c:out>'>
							</div>
							<div class="col-sm-8" >
								<p class="text-justify"><c:out value="${ob.resumen}"></c:out></p>
							</div>
						</div>
						<br>
					</c:forEach>
				
				</div>
			</div>
</body>
</html>