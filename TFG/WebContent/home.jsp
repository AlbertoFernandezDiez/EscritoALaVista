<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title><c:out value="${requestScope.tit}"></c:out></title>
<link rel="stylesheet" href="css/style.css">
<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$("#exportar").hide();
		$("#formtitle").click(function() {
			$("#exportar").toggle(500);
		});
	});
</script>

</head>
<body>
	<jsp:include page="menu.jsp" />

	<div class='jumbotron'>


		<button type="button" class="btn btn-default" id='formtitle'
			value='Exportar'>Exportar</button>

		<form id='exportar' name='exportar' class="form-inline" method='GET'
			role="form" action='Itext'>
			<div class="form-group">
				 <label class="radio-inline"></label><input type="radio" name="formato" value="Itext" checked="checked">Itext</label>
				 <label class="radio-inline"><input type="radio" name="formato" value="EpubCreator">Epub</label>
				 <label class="radio-inline"><input type="radio" name="formato" value="LatexCreator">Latex</label>
				 <label class="radio-inline"><input type="hidden" name="id"
					value="<c:out value="${requestScope.id}" ></c:out>"> <input
					type='submit' class="btn btn-default" name='exportar'>
			</div>
		</form>

		<script>
			$("input[name=formato]:radio").change(function() {
				$("#exportar").attr('action', $(this).val());
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
			</div>
		</div>
	</div>
	</div>
</body>
</html>