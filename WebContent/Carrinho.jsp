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
	<h1 align="center">CARRINHO</h1>
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
					<input type="number" value="1" width=40px id="txtQtde" min="1" max="5" style = "width: 50px">
				</div>
				<div class="col-2">R$1500,00</div>
			</div>
		</div>
		<div class="col-4" style="background-color: #f5f2f2;">
			<div class="row">
				<div class="col-4">Calcular Frete</div>
				<div class="col-8">
					<input type="text" placeholder="Digite seu CEP" id="txtCep"><input
						type="button" class="btn btn-primary" value="Calcular"
						onclick="calcularFrete();">
				</div>
			</div>

			<div class="row" style="padding: 10px">
				<div class="col-4">Subtotal</div>
				<div class="col-8">
					R$<input type="text" id="txtSubtotal" value="1500.00"
						style="border-style: none; background-color: #f5f2f2;"
						disabled="disabled"></input>
				</div>
			</div>

			<div class="row" style="padding: 10px">
				<div class="col-4">Frete</div>
				<div class="col-8">
					R$<input type="text" id="txtFrete"
						style="border-style: none; background-color: #f5f2f2;"
						disabled="disabled"></input>
				</div>
			</div>
			<div class="row" style="padding: 10px">
				<div class="col-4">Total</div>
				<div class="col-8" id="total">
					R$<input type="text" id="txtTotal"
						style="border-style: none; background-color: #f5f2f2;"
						disabled="disabled"></input>
				</div>
			</div>
			<div class="row" style="padding: 10px">
				<div class="col-12" align="center">
					<a href="selecionarendereco.jsp" class="btn btn-primary">Finalizar
						Compra</a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function calcularFrete() {
			//var valor = document.getElementById("txtCep").value;
			var frete = 10;
			var subtotal = document.getElementById("txtSubtotal").value;
			
			document.getElementById("txtFrete").value = frete.toFixed(2).replace(".", ",");
			
			var total = frete + total;
			
			document.getElementById("txtTotal").value = "1510.00";
		}
	</script>

</body>
</html>