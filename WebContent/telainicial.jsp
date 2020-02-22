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
		<%
			for (int i = 0; i < 4; i++) {
		%>
		<tr>
			<%
				for (int j = 0; j < 4; j++) {
			%>
			<td>
				<div class="card" align="center" style="width: 18rem;">
					<img
						src="./Imagens/samsung galaxy.jpg"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Smartphone Samsung Galaxy A7 32GB</h5>
						<p class="card-text">R$1500,00</p>
						<a href="#" class="btn btn-primary">Comprar</a>
					</div>
				</div>
			</td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>

	<ul class="nav justify-content-center">
		<li class="nav-item"><a class="nav-link active" href="#">1</a></li>
		<li class="nav-item"><a class="nav-link" href="#">2</a></li>
		<li class="nav-item"><a class="nav-link" href="#">3</a></li>
		<li class="nav-item"><a class="nav-link disabled" href="#"
			tabindex="-1" aria-disabled="true">4</a></li>
	</ul>

	<script
		src="bootstrap/css/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
</body>
</html>