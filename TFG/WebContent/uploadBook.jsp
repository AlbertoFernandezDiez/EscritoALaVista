
<%@page import="packClases.Capitulo"%>
<%@page import="packClases.ListaCapitulos"%>
<%@page import="packClases.Obra"%>
<%@page import="java.util.Iterator"%>
<%@page import="packClases.ListaObras"%>
<%@page import="packBD.GestorBD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset = 'UTF-8'>
<script src="js/loadSelect.js"></script>
<script
	src="js/jquery-1.10.2.js">
<!-- src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js">-->
</script>
 
<title>Upload Book</title>

</head>
<body>
	<input type="hidden" id="url"
		value=<%=getServletContext().getInitParameter("server-url")%>>
	<form action="uploadBookChapter" method="POST" enctype="multipart/form-data">
			
		<select id="selectObra" name="selectObra" size=1
			onChange="loadChapterSel($('#selectObra').val())">
			<option name=default value=0>Nueva Obra</option>
			<%
				ListaObras lista = GestorBD.getGestorBD().getObras();
				Iterator<packClases.Obra> it = lista.getIterator();

				packClases.Obra aux = null;
				while (it.hasNext()) {
					aux = it.next();
			%>
			<option name=<%=aux.getTitulo()%> value=<%=aux.getId()%>>
				<%=aux.getTitulo()%>
			</option>
			<%
				}
			%>
		</select> <select id="selectCapitulo" name="selectCapitulo" size=1
			onChange="loadChapter($('#selectCapitulo').val())">>
			<option name=default value=0>Nuevo Capitulo</option>
		</select> <br> <label for="titOb">Titulo Obra :</label><input type="text"
			id="titOb" name="titOb"> <br> <label for="resumen">Resumen
			:</label>
		<textarea rows="5" cols="20" id="resumen" name="resumen" spellcheck="true"></textarea>
		<input id="fileObra" type="file" name="fileObra" size="50" />
		<input id='rutaP' type='text' name='rutaP'/>
		<input type='button' id='cambiarP' value='Cambiar Portada' onclick='$("#rutaP").val("");'>
		<br> <label for="titCap">Titulo Capitulo :</label> <input
			type="text" id="titCap" name="titCap" spellcheck="true"> <br> <label
			for="capitulo">Capitulo :</label>
		<textarea rows="5" cols="20" id="capitulo" name="capitulo" spellcheck="true"></textarea>
		<br> <label for="comentarios" spellcheck="true">Comentarios :</label>
		<textarea rows="5" cols="20" id="comentarios" name="comentarios" spellcheck="true"></textarea>
		<input id="fileCapi" type="file" name="fileCapi" size="50" />
		<input id='rutaC' type='text' name='rutaC' />
		<input type='button' id='cambiarP' value='Cambiar Portada' onclick='$("#rutaC").val("");'>
		
		
		
		<br> <input type="submit" value="enviar" />
	</form>
	
</body>
</html>