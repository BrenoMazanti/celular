<%@ include file="./Componentes/EstruturaInicio.jsp"%>
<%@ include file="./CSS/telas.css"%>
<title>Selecionar endereço</title>
</head>
<body>
	<%@ include file="./Componentes/Cabecalho.jsp"%>
	<h1 align="center" margin=10px>SELECIONAR ENDEREÇO</h1>
	<table style="margin: 60px">
		<%
			//dar possibilidade de utilizar outro endereço ou cadastrar novo endereço em sua conta (2 botões).
			for (int i = 0; i < 1; i++) {
		%>
		<tr>
			<%
				for (int j = 0; j < 3; j++) {
			%>
			<td>
				<div class="card" align="center" style="width: 18rem;">
					<div class="card-body">
						<h5 class="card-title">Casa</h5>
						<p class="card-text">Rua 1, n 150</p>
						<a href="qtdecartoes.jsp" class="btn btn-primary">Selecionar</a> <a
							href="#" class="btn btn-primary">Editar</a>
					</div>
				</div>
			</td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>
	<footer>
		<div style="position: fixed; padding: 10px; float: right;">
			<a class="btn-floating btn-large waves-effect waves-light red"><i
				class="material-icons">add</i></a>
		</div>
	</footer>
</body>
</html>