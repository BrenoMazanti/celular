<%@ include file="Componentes/EstruturaInicio.jsp"%>
</head>
<body>
	<%@ include file="Componentes/admCabecalho.jsp"%>

	<table class="table table-striped" style="margin-left: 5%; margin-right: 5%" id="pedidos">
		<thead class="thead-light">
			<tr>
				<th scope="col"></th>
				<th scope="col">ID</th>
				<th scope="col">Status</th>
				<th scope="col"></th>
				<th scope="col"></th>
				<th scope="col"></th>
			</tr>
		</thead>
		<%
			for (int i = 0; i < 10; i++) {
		%>
		<tr>
			<td style="width: 200px">
			<%
				out.println("<td id = " + i + ">");
					out.println("000000" + i);
			%>
			</td>
			<td style="width: 400px">
				<div class="form-group">
					<select class="form-control"
						id="txtStatus" name="txtStatus">
						<option value="Aguardando">Aguardando aprovação</option>
						<option value="Aprovado">Aprovado</option>
						<option value="Em transporte">Em transporte</option>
						<option value="Entregue">Entregue</option>
					</select>
				</div>
			</td>
			<td style="width: 200px">
				
				<input type='button' id='operacao' name='operacao' value='Salvar'
				class="btn btn-primary" />
				
			</td>
			<td style="width: 200px">
				<input type='button' id='operacao' name='operacao' value='Editar status'
				class="btn btn-primary" style="margin: 2%"/>
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