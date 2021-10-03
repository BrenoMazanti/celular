<%@page import="br.edu.fatec.celular.dominio.Administrador"%>

<%
	Administrador adm = (Administrador) session.getAttribute("adm");
	if (adm == null) {
		response.sendRedirect("admLogin.jsp");
	}
%>

<div class="navbar-fixed">
	<nav>
		<div class="navbar-nav mr-auto" style="background-color: #008B8B">
			<a href="admInicial.jsp"><img src="./Imagens/cell.png"
				style="height: 60px; width: 60px;"> <label
				style="font-size: 26pt; font-family: Magneto;">Celulares</label></a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="./Cliente?operacao=LISTAR">Clientes</a></li>
				<li><a href="./Celular?operacao=LISTAR">Produtos</a></li>
				<li><a href="admPedidos.jsp">Pedidos</a></li>
				<li><a href="admTrocas.jsp">Trocas</a></li>
				<li><a href="admAnalise.jsp">Análise</a></li>

				<!-- Dropdown menu -->
				<li><a class='dropdown-trigger btn' href='#'
					data-target='dropdown1'>Conta</a>
					<ul id='dropdown1' class='dropdown-content'>
						<li><a href="Logout.jsp"><i class="material-icons">highlight_off</i>Sair</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
</div>