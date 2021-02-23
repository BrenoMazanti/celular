<%@ include file="Componentes/EstruturaInicio.jsp"%>
<style>
img {
	height: 200px;
	width: 200px;
}
</style>
<body>
	<%@ include file = "Componentes/Cabecalho.jsp"%>

	<table align="center">
		<tr>
			<td>
				<div class="card" align="center" style="width: 18rem;">
					<img
						src="./Imagens/samsung galaxy.jpg"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Smartphone Samsung Galaxy A7 32GB</h5>
						<p class="card-text">R$1500,00</p>
						<button onclick="alert("Eu sou um alert!");" style="margin-top:5px">Adicionar ao Carrinho</button>
					</div>
				</div>
			</td>
		</tr>
	</table>

	<ul class="nav justify-content-center">
		<li class="nav-item"><a class="nav-link active" href="#">1</a></li>
		<li class="nav-item"><a class="nav-link" href="#">2</a></li>
		<li class="nav-item"><a class="nav-link" href="#">3</a></li>
		<li class="nav-item"><a class="nav-link disabled" href="#"
			tabindex="-1" aria-disabled="true">4</a></li>
	</ul>

	
</body>
</html>