<%@ include file="Componentes/EstruturaInicio.jsp"%>

<body>
	<%@ include file="Componentes/Cabecalho.jsp"%>
	<div class="row">
		<%
			for (int j = 0; j < 2; j++) {
		%>
		<div class="col-3">
			<div class="card" align="center" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">Cupom</h5>
					<p class="card-text">****4FT7</p>
					<p class="card-text">R$ 100.00</p>
					<input type="checkbox"></input>
				</div>
			</div>
		</div>
		<%
			}
		%>

		<div class="col-4" style="background-color: #f5f2f2;">
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
					R$<input type="text" id="txtFrete" value="10.00"
						style="border-style: none; background-color: #f5f2f2;"
						disabled="disabled"></input>
				</div>
			</div>
			<div class="row" style="padding: 10px">
				<div class="col-4">Total</div>
				<div class="col-8" id="total">
					R$<input type="text" id="txtTotal" value="1510.00"
						style="border-style: none; background-color: #f5f2f2;"
						disabled="disabled"></input>
				</div>
			</div>
			<div class="row" style="padding: 10px">
				<div class="col-12" align="center">
					<a href="telalogincarrinho.jsp" class="btn btn-primary">Finalizar
						Compra</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>