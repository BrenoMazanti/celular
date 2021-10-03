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
	<h1 align="center">SOLICITAÇÃO DE TROCA</h1>
	<h4 align="center">Selecione os produtos, a quantidade e digite o motivo da devolução</h4>
	<div class="row" style="margin-top: 30px">
		<div class="col-8">
			<div class="row" style="padding-bottom: 35px; font-weight: bold; background-color: light gray">
				<div class="col-1"><input type="checkbox" onClick="toggle(this)"> </div>
				<div class="col-3 offset-3">Descrição do produto</div>
				<div class="col-3">Quantidade</div>
				<div class="col-2">Preço Unitário</div>
			</div>
			<div class="row">
				<div class="col-1" class="text-center">
					<input type="checkbox" id="foo" name="foo">
				</div>
				<div class="col-3">
					<img
						src="./Imagens/samsung galaxy.jpg">
				</div>
				<div class="col-3">Smartphone Samsung Galaxy A7 32GB</div>
				<div class="col-3">
					<input type="number" value="1" width=40px id="txtQtde" min="1" max="5" style = "width: 50px">
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
	<br>
	<br>
	<div align="center">
		<textarea value="" placeholder="Digite aqui o motivo da troca" style="margin: 10px ; width: 500px ; height: 200px ;"></textarea>
	</div>
	<div align="center">
		<a href="MinhasTrocas.jsp"
				class="btn btn-primary" style="margin-left: 5%;"/>FINALIZAR TROCA</a>
	</div>
	
	<script type="text/javascript">
		function toggle(source) {
		  checkboxes = document.getElementsByName('foo');
		  for(var checkbox in checkboxes)
		    checkbox.checked = source.checked;
		}
	</script>
</body>
</html>