<%@ include file="/CSS/telas.css"%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="telainicial.jsp"> <img
		src="./Imagens/cell.png" style="height: 150px; width: 150px;"><label
		style="font-size: 26pt; font-family: Magneto;">Celulares</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#conteudoNavbarSuportado"
		aria-controls="conteudoNavbarSuportado" aria-expanded="false"
		aria-label="Alterna navegação">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search" style="width: 600px"
						placeholder="Pesquisar" aria-label="Pesquisar">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
				</form>
			</li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Minha Conta </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					
					<%if(request.getSession().getAttribute("cliente") != null) {%>

						<a class="dropdown-item" href="FormCliente.jsp">Meus Dados</a>
						<a class="dropdown-item" href="trocasenha.jsp">Alterar Senha</a>
                        <a class="dropdown-item" href="listaendereco.jsp">Meus Endereços</a>
                        <a class="dropdown-item" href="listacartoes.jsp">Meus Cartões</a> 
						<a class="dropdown-item" href="#">Meus Pedidos</a>
						<a class="dropdown-item" href="Logout.jsp">Sair</a>

					<% } 
					
					else {%>
					
						<a class="dropdown-item" href="telalogin.jsp">Entrar</a>
						<a class="dropdown-item" href="FormCliente.jsp">Cadastrar</a>
						<a class="dropdown-item" href="telalogin.jsp">Meus Dados</a> <a
							class="dropdown-item" href="listaendereco.jsp">Meus Endereços</a> <a
							class="dropdown-item" href="#">Meus Cartões</a> <a
							class="dropdown-item" href="#">Meus Pedidos</a>
							
					<% }%>


					<div class="dropdown-divider"></div>
				</div>
			</li>
			<li class="nav-item"><a class="nav-link" href="Carrinho.jsp"
				style="color: black"><i style="font-size:40px;" class="fas fa-shopping-cart"></i></a>
			</li>
		</ul>
	</div>
</nav>

<script src="https://kit.fontawesome.com/d4b7dd0609.js" crossorigin="anonymous"></script>