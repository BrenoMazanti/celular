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
		<table style="float: left;">
			<tr>
				<th></th>
				<th style="padding-bottom: 35px; font-weight: bold; text-align: center;">Descrição do produto</th>
				<th style="padding-bottom: 35px; font-weight: bold; text-align: center;">Quantidade</th>
				<th style="padding-bottom: 35px; font-weight: bold; text-align: center;" >Preço</th>
			<tr>
				<td>
					<img src="./Imagens/samsung galaxy.jpg">
				</td>
				<td style="padding-bottom: 35px; font-weight: bold; text-align: center;">Smartphone Samsung Galaxy A7 32GB</td>
				<td style="padding-bottom: 35px; font-weight: bold; text-align: center;">
					<input type="number" width=40px id="txtQtde" min="1" max="5" style = "width: 50px">
				</td>
				<td style="padding-bottom: 35px; font-weight: bold; text-align: center;">R$1500,00</td>
			</tr>
		</table>
		<table style="float: left; background-color: #f5f2f2;">
			<tr style="padding: 10px;">
				<td>Calcular Frete</td>
				<td>
					<input type="text" placeholder="Digite seu CEP" id="txtCep">
				</td>
				<td>
					<input type="button" class="btn btn-primary" value="Calcular"
					onclick="calcularFrete();">
				</td>
			</tr>
			<tr style="padding: 10px;">
				<td>Subtotal</td>
				<td>R$<input type="text" id="txtSubtotal" value="1500.00"
								style="border-style: none; background-color: #f5f2f2;"
								disabled="disabled"></input>
				</td>
		
			<tr style="padding: 10px;">
				<td>Frete</td>
				<td>
					R$<input type="text" id="txtFrete"
						style="border-style: none; background-color: #f5f2f2;"
						disabled="disabled"></input>
				</td>
			</tr>
			<tr style="padding: 10px;">
				<td>Total</td>
				<td>
					R$<input type="text" id="txtTotal"
						style="border-style: none; background-color: #f5f2f2;"
						disabled="disabled"></input>
				</td>
			</tr>
			<tr style="padding: 10px;">
				<td colspan="3" align="center">
					<a href="selecionarendereco.jsp" class="btn btn-primary">Finalizar
						Compra</a>
				</td>
			</tr>
		</table>
	
	<script type="text/javascript">
		function calcularFrete() {
			//var valor = document.getElementById("txtCep").value;
			var frete = 10;
			var subtotal = document.getElementById("txtSubtotal").value;
			
			document.getElementById("txtFrete").value = frete.toFixed(2).replace(".", ",");
			
			var total = eval(frete) + eval(subtotal);
			
			document.getElementById("txtTotal").value = total ;
		}
	</script>

</body>
</html>