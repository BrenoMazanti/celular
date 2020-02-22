<%@page import="java.util.ArrayList"%>
<%@page import="br.edu.fatec.celular.util.Resultado"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@page import="java.util.List"%>
<%@ include file="Componentes/EstruturaInicio.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Componentes/admCabecalho.jsp"%>
	<%
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes = (List<Cliente>) request.getAttribute("clientes");
	%>
	<table style="margin-left: 5%; margin-right: 5%" id="clientes">
		<%
			for (Cliente cliente : clientes) {
		%>
		<tr>

			<%
				out.println("<td id = " + cliente.getId() + ">");
				out.println(cliente.getId());
			%>
			</td>
			<td>
				<%
					out.println(cliente.getNome());
				%>
			</td>
			<td>
				<%
					out.println(cliente.getEmail());
				%>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>