<%@ include file="./Componentes/EstruturaInicio.jsp"%>
<%@ include file="./CSS/telas.css"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@page import="br.edu.fatec.celular.dominio.Cartao"%>
<title>Meus Cartões</title>
</head>
<body>
	<%@ include file="./Componentes/Cabecalho.jsp"%>
	<table style="margin: 60px">
		<%
			//session.setAttribute("pagina", pageContext.getPage().getClass().getSimpleName().replaceAll("_", "."));
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			if (cliente == null) {
				response.sendRedirect("telalogin.jsp");
			}
			List<Cartao> cartoes = new ArrayList<Cartao>();
			cartoes = (List<Cartao>) request.getAttribute("cartoes");
		%>
		<tr><th><h2 align="center" margin=10px>
			ENDEREÇOS DE <% if (cliente != null) { out.println(cliente.getNome()); }%>
		</h2></th></tr>
		<%
			out.println("<tr><td style='margin: 60px'><div style='margin: 10px'><a href='FormEndereco.jsp' class='btn btn-primary'>Novo+</a></div></td></tr>");
			if (cliente != null){
				if (cartoes != null && !cartoes.isEmpty()){
					int linha = 0;
					for (Cartao d : cartoes) {
			%>
				<tr>
					<td>
						<div class="card" align="center" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title"><%out.println(d.getDescricao());%></h5>
								<p class="card-text"><%out.println(d.getNumero().substring(8));%></p>
								<a href="FormEndereco.jsp" class="btn btn-primary">Editar</a> <a
									href="#" class="btn btn-primary">Excluir</a>
							</div>
						</div>
					</td>
				<tr>
				<%
					}
				}
			}	
		%>
	</table>
	<footer>
		
	</footer>
	<script>
	</script>
</body>
</html>