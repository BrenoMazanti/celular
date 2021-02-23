<%@ include file="/Componentes/EstruturaInicio.jsp"%>
</head>
<body>
	
	<div class="row" align="center">
		<div class="col-8">
			<div class="row">
				<%
					for (int j = 0; j < 3; j++) {
				%>
				<div class="col-3">
					<div class="card" align="center" style="width: 18rem;">
						<div class="card-body">
							<h5 class="card-title">Meu Cartão Visa</h5>
							<p class="card-text">****1505</p>
							<a href="formapagamento.jsp" class="btn btn-primary">Selecionar</a>
							<a href="#" class="btn btn-primary">Editar</a>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<div class="col-3">
					<a href="FormCartao.jsp" class="btn btn-primary" style="">Novo+</a>
				</div>
			</div>
			</div>
			<div class="col-4">
				<div class="row">
					<div class="col-4">Cupom</div>
					<div class="col-4">
						<input type="text"></input>
					</div>
					<div class="col-4">
						<a href="#" class="btn btn-primary">Adicionar</a>
					</div>
				</div>
			</div>
		</div>
</body>
</html>