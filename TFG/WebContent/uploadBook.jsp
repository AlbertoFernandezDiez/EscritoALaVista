
<%@page import="packClases.Capitulo"%>
<%@page import="packClases.ListaCapitulos"%>
<%@page import="packClases.Obra"%>
<%@page import="java.util.Iterator"%>
<%@page import="packClases.ListaObras"%>
<%@page import="packBD.GestorBD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/loadSelect.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"> </script>
<title>Upload Book</title>
</head>
<body>
<input type="hidden" id="url" value=<%=getServletContext().getInitParameter("server-url") %>>
<form action="uploadBook.jsp" method="POST" >
<select id="selectObra" name="selectObra" size=1 onChange="loadChapters($('#selectObra').val(),$('#selectCapitulo').val())">
<option name=default value=0> Nueva Obra </option>
<%
ListaObras lista = GestorBD.getGestorBD().getObras();
Iterator<Obra>it = lista.getIterator();

Obra aux = null;
while (it.hasNext()){
aux = it.next();%>
<option name=<%=aux.getTitulo() %> value=<%=aux.getId() %>> <%=aux.getTitulo() %> </option>
<%}%>
</select>

<select id="selectCapitulo" name="selectCapitulo" size=1>
<option name=default value=0> Nuevo Capitulo </option>
</select>
<input type="submit" value="enviar" />
</form>
</body>
</html>