<%@ include file="Componentes/EstruturaInicio.jsp"%>
<%@page import="br.edu.fatec.celular.util.Resultado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@page import="br.edu.fatec.celular.dominio.Pedido"%>
<%@page import="br.edu.fatec.celular.dominio.Pedidoi"%>
<%@page import="br.edu.fatec.celular.dominio.Pagamento"%>
</head>
<body>
	<%@ include file="Componentes/admCabecalho.jsp"%>
	
	<h1 align="center" margin=10px>PEDIDOS</h1>
	<table class="table table-striped" style="margin-left: 5%; margin-right: 5%" id="pedidos">
		<thead class="thead-light">
			<tr>
				<th scope="col"></th>
				<th scope="col">ID</th>
				<th scope="col">Cliente</th>
				<th scope="col">Status</th>
				<th scope="col"></th>
				<th scope="col"></th>
			</tr>
		</thead>
		<%
			List<Pedido> pedidos = new ArrayList<Pedido>();
			pedidos = (List<Pedido>) request.getAttribute("pedidos");
			for (Pedido i : pedidos) {
		%>
		<tr>
			<td style="width: 200px"></td>
			<%
				out.println("<td id = " + i.getId() + ">");
					out.println("" + i.getId());
			%>
			</td>
			<%
				out.println("<td id = " + i.getId() + ">");
					out.println(i.getCliente().getId());
			%>
			<td style="width: 400px">
				<div class="form-group">
					<select class="form-control"
						id="txtStatus" name="txtStatus">
						<option value=1>Aguardando aprovação</option>
						<option value=2>Em preparo</option>
						<option value=3>Em transporte</option>
						<option value=4>Entregue</option>
					</select>
				</div>
			</td>
			<td style="width: 200px">
				
				<input type='button' id='operacao' name='operacao' value='ALTERAR'
				class="btn btn-primary" />
				
			</td>
			<td style="width: 200px">
				
				<a href="#"
				class="btn btn-primary" />Informações do Pedido</a>
				
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>