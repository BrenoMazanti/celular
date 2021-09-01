<%@ include file="/Componentes/EstruturaInicio.jsp"%>
</head>
<body>
	<%@ include file="./Componentes/Cabecalho.jsp"%>
	<h2 align="center" margin=10px>CARTÕES DE JOÃO</h2>
	
	<div class="row" align="center">
		<div class="col-8">
			<div class="row">
				<%
					for (int j = 0; j < 4; j++) {
				%>
				<div class="col-3">
					<div class="card" align="center" style="width: 18rem;">
						<div class="card-body">
							<h5 class="card-title">Meu Cartão Visa</h5>
							<p class="card-text">****1505</p>
							<a href="FormCartaoprot.jsp" class="btn btn-primary">Editar</a>
							<a href="#" class="btn btn-primary">Excluir</a>
						</div>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
		<div class="col-4">
					<a href="FormCartaoprot.jsp" class="btn btn-primary" style="">Novo+</a>
		</div>
	</div>
</body>
</html>