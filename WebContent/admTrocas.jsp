<%@ include file="Componentes/EstruturaInicio.jsp"%>
</head>
<body>
	<%@ include file="Componentes/admCabecalho.jsp"%>
	
	<h1 align="center" margin=10px>TROCAS</h1>
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
			for (int i = 1; i < 2; i++) {
		%>
		<tr>
			<td style="width: 200px"></td>
			<%
				out.println("<td id = " + i + ">");
					out.println("03468" + i);
			%>
			</td>
			<%
				out.println("<td id = " + i + ">");
					out.println("8 - Pedro");
			%>
			<td style="width: 400px">
				<div class="form-group">
					<select class="form-control"
						id="txtStatus" name="txtStatus">
						<option value="Aguardando">Aguardando aprovação</option>
						<option value="Aprovado">Aguardando envio dos produtos</option>
						<option value="Finalizada" disabled>Finalizada</option>
					</select>
				</div>
			</td>
			<td style="width: 200px">
				
				<input type='button' id='operacao' name='operacao' value='ALTERAR'
				class="btn btn-primary" />
				
			</td>
			<td style="width: 200px">
				
				<a href="admFormTroca.jsp"
				class="btn btn-primary" />Informações da Troca</a>
				
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>