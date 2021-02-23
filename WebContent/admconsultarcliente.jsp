<%@page import="java.util.ArrayList"%>
<%@page import="br.edu.fatec.celular.util.Resultado"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@page import="java.util.List"%>
<%@ include file="Componentes/EstruturaInicio.jsp"%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Componentes/admCabecalho.jsp"%>
	<%
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes = (List<Cliente>) request.getAttribute("clientes");
	%>
	<table class="table table-striped" style="margin-left: 5%; margin-right: 5%; border: medium;"
		id="clientes">
		<thead class="thead-light">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Nome</th>
				<th scope="col">E-mail</th>
			</tr>
		</thead>
		<%
			for (Cliente cliente : clientes) {
		%>
		<tr>

			<%
				out.println("<td style=" + "'width: 400px'" + " id = " + cliente.getId() + " name = " + cliente.getId() +">");
				out.println(cliente.getId());
			%>
			</td>
			<td style="width: 400px">
				<%
					out.println(cliente.getNome());
				%>
			</td>
			<td style=" + "'width: 400px'" + " style="width: 400px">
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