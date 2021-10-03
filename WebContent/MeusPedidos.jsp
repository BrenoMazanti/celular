<%@ include file="./Componentes/EstruturaInicio.jsp"%>
<%@ include file="./CSS/telas.css"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<title>Meus Pedidos</title>
</head>
<body>
	<%@ include file="Componentes/Cabecalho.jsp"%>
	
	
	<h1 align="center" margin=10px>MEUS PEDIDOS</h1>
	<table class="table table-striped" style="margin-left: 5%; margin-right: 5%" id="pedidos">
		<thead class="thead-light">
			<tr>
				<th scope="col"></th>
				<th scope="col">ID</th>
				<th scope="col">Status</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<%
			session.setAttribute("pagina", pageContext.getPage().getClass().getSimpleName().replaceAll("_", "."));
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			if (cliente == null) {
				response.sendRedirect("telalogin.jsp");
			}
			//dar possibilidade de utilizar outro endereço ou cadastrar novo endereço em sua conta (2 botões).
			for (int i = 1; i < 2; i++) {
		%>
		<tr>
			<td style="width: 200px">
			<%
				out.println("<td id = " + i + ">");
					out.println("12373" + i);
			%>
			</td>
			<td style="width: 400px">
				<div class="form-group">
					<select class="form-control"
						id="txtStatus" name="txtStatus" disabled>
						<option value="Aguardando">Aguardando aprovação</option>
						<option value="Aprovado">Aprovado</option>
						<option value="Em transporte">Em transporte</option>
						<option value="Entregue" selected>Entregue</option>
					</select>
				</div>
			</td>
			<td style="width: 200px">
				
				<a href="Pedido.jsp"
				class="btn btn-primary" />Informações do Pedido</a>
				
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>