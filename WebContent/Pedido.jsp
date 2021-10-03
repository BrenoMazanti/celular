<%@ include file="Componentes/EstruturaInicio.jsp"%>
<style>
img {
	height: 150px;
	width: 150px; 
}
</style>
</head>
<body>
	<%@ include file="Componentes/Cabecalho.jsp"%>
	<h1 align="center">INFORMAÇÕES DO PEDIDO</h1>
	<div>
		<a href="FormTroca.jsp"
				class="btn btn-primary" style="margin-left: 5%;"/>Realizar uma Troca</a>
	</div>
	<div class="row">
		<div class="col-8">
			<div class="row" style="padding-bottom: 35px; font-weight: bold;">
				<div class="col-3 offset-3">Descrição do produto</div>
				<div class="col-4">Quantidade</div>
				<div class="col-2">Preço Unitário</div>
			</div>
			<div class="row">
				<div class="col-3">
					<img
						src="./Imagens/samsung galaxy.jpg">
				</div>
				<div class="col-3">Smartphone Samsung Galaxy A7 32GB</div>
				<div class="col-4">
					<input type="number" value="1" width=40px id="txtQtde" min="1" max="5" style = "width: 50px" disabled>
				</div>
				<div class="col-2">R$1500,00</div>
			</div>
		</div>
			<div class="row" style="padding: 10px">
				<div class="col-3">Total</div>
				<div class="col-9" id="total">
					R$<input type="text" id="txtTotal" value="1500.00"
						style="border-style: none; background-color: #f5f2f2;"
						disabled="disabled"></input>
				</div>
			</div>
		</div>
	</div>

</body>
</html>