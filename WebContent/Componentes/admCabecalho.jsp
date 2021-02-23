<%@page import="br.edu.fatec.celular.dominio.Administrador"%>

<%
	Administrador adm = (Administrador) session.getAttribute("adm");
	if (adm == null) {
		response.sendRedirect("admLogin.jsp");
	}
%>

<div class="row">
	<div class="col-3" style="">
		<a href="admInicial.jsp"><img src="./Imagens/cell.png"
			style="height: 150px; width: 150px;"><label
			style="font-size: 26pt; font-family: Magneto;">Celulares</label></a>
	</div>

	<div class="col-9" style="margin-top: 3%">
		<ul class="nav justify-content-right">
			<li class="nav-item"><a class="nav-link active"
				href="./Cliente?operacao=LISTAR">Clientes</a></li>
			<li class="nav-item"><a class="nav-link"
				href="./Celular?operacao=LISTAR">Produtos</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="admPedidos.jsp">Pedidos</a>
			<li class="nav-item"><a class="nav-link active" href="admAnalise.jsp">Análise</a>
			<li class="nav-item " style="padding-left: 60%;">
				<button
					class="btn btn-secondary dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Conta</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="Logout.jsp">Sair</a>
					</div></li>




		</ul>
	</div>