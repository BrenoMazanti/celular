<%@ include file="/CSS/telas.css"%>

<div class="row">
	<div class="col-3" style="">
		<a href="telainicial.jsp"><img src="./Imagens/cell.png"
			style="height: 150px; width: 150px;"><label
			style="font-size: 26pt; font-family: Magneto;">Celulares</label></a>
	</div>

	<div class="col-6">
		<div class="input-group mb-3">
			<input type="text" class="form-control"
				style="margin-left: 2%; margin-top: 6%; margin-bottom: 2%; margin-right: 1%; width: 50px;"
				placeholder="Pesquise um produto" aria-label="Pesquise um produto"
				aria-describedby="button-addon2">
			<div class="input-group-append"
				style="margin-top: 6%; margin-bottom: 2%; margin-right: 10%;">
				<button class="btn btn-outline-secondary" type="button"
					id="button-addon2">
					Pesquisar <i class="fas fa-search"></i>
				</button>
			</div>
		</div>
	</div>
	<div class="col-3" style="margin-top: 2%;">
		<ul class="nav justify-content-center">
			<li class="nav-item" style="margin-top: 3%;">
				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">Minha Conta</button>
					
					<%if(request.getSession().getAttribute("cliente") != null) {%>

					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="FormAlterarCliente.jsp">Meus Dados</a> <a
							class="dropdown-item" href="#">Meus Endereços</a> <a
							class="dropdown-item" href="#">Meus Cartões</a> <a
							class="dropdown-item" href="#">Meus Pedidos</a>
							<a class="dropdown-item" href="Logout.jsp">Sair</a>
					</div>

					<% } 
					else {%>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="telalogin.jsp">Entrar</a>
						<a class="dropdown-item" href="FormCliente.jsp">Cadastrar</a>
						<a class="dropdown-item" href="FormAlterarCliente.jsp">Meus Dados</a> <a
							class="dropdown-item" href="#">Meus Endereços</a> <a
							class="dropdown-item" href="#">Meus Cartões</a> <a
							class="dropdown-item" href="#">Meus Pedidos</a>
					</div>
					<% }%>


				</div>
			</li>
			<li class="nav-item"><a class="nav-link" href="Carrinho.jsp"
				style="color: black"><i style="font-size:40px;" class="fas fa-shopping-cart"></i></a></li>
		</ul>
	</div>
</div>

<script src="https://kit.fontawesome.com/d4b7dd0609.js" crossorigin="anonymous"></script>
