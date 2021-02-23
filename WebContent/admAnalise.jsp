<%@ include file="Componentes/EstruturaInicio.jsp"%>
</head>
<body>
	<%@ include file="Componentes/admCabecalho.jsp"%>
	<div class="row" style="padding: 10px; margin-left: 5%">
		<div class="col-4">
			<div class="form-group">
			<label for="txtProduto1">Produto 1</label> <input type="text"
				class="form-control" id="txtProduto1" name="txtProduto1"
				placeholder="Digite um produto" value="Samsung Galaxy S10">
			</div>
		</div>
		<div class="col-4">
			<div class="form-group">
			<label for="txtProduto1">Produto 2</label> <input type="text"
				class="form-control" id="txtProduto1" name="txtProduto1"
				placeholder="Digite um produto" value="Iphone X">
			</div>
		</div>
		<div class="col-4">
			<div class="form-group">
			<label for="txtProduto1">Produto 3</label> <input type="text"
				class="form-control" id="txtProduto1" name="txtProduto1"
				placeholder="Digite um produto" value="Iphone 11 Pro Max">
			</div>
		</div>
		<div class="col-5">
			<div class="form-group">
			<label for="txtDataAnalise1">Período - Mês/Ano</label> <input
				type="text" class="form-control" id="txtDataAnalise1"
				name="txtDataAnalise1" value="09/2019">
			</div>
		</div>
		<div class="col-5">
			<div class="form-group">
			<label for="txtDataAnalise2"></label> <input
				type="text" class="form-control" id="txtDataAnalise2"
				name="txtDataAnalise2" value="02/2020">
			</div>
		</div>
		
		<div class="col-2" style="margin-top: 3%">
		<input type='submit' id='operacao' name='operacao' value='Visualizar'
				class="btn btn-primary" />
		</div>
		
		<img src="./Imagens/grafico.png">
	
	<script type="text/javascript">

		$(document).ready(function() {
			
			$("#txtDataAnalise1").mask("00-0000");
			$("#txtDataAnalise2").mask("00-0000");

		});
	</script>
</body>
</html>