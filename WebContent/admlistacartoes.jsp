<%@ include file="/Componentes/EstruturaInicio.jsp"%>
</head>
<body>
	<%@ include file="./Componentes/Cabecalho.jsp"%>
	<div class="row" align="center">
		<h2>Cartões de João</h2>
	</div>
	
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
							<a href="FormCartao.jsp" class="btn btn-primary">Editar</a>
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
					<a href="FormCartao.jsp" class="btn btn-primary" style="">Novo+</a>
		</div>
	</div>
</body>
</html>