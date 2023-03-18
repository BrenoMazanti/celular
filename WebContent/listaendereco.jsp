<%@ include file="./Componentes/EstruturaInicio.jsp"%>
<%@ include file="./CSS/telas.css"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@page import="br.edu.fatec.celular.dominio.Endereco"%>
<title>Meus Endereços</title>
</head>
<body>
	<%@ include file="./Componentes/Cabecalho.jsp"%>
	<table style="margin: 60px">
		<%
			//session.setAttribute("pagina", pageContext.getPage().getClass().getSimpleName().replaceAll("_", "."));
			session.setAttribute("pagina", "/Endereco?operacao=CONSULTAR&pagina=" + pageContext.getPage().getClass().getSimpleName().replaceAll("_", "."));
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			if (cliente == null) {
				response.sendRedirect("telalogin.jsp");
			}
		%>
		<tr><th><h2 align="center" margin=10px>
			ENDEREÇOS DE <% if (cliente != null) { out.println(cliente.getNome()); }%>
		</h2></th></tr>
		<%
			out.println("<tr><td style='margin: 60px'><div style='margin: 10px'><a href='FormEndereco.jsp' class='btn btn-primary'>Novo+</a></div></td></tr><tr>");
			if (cliente != null){
				List<Endereco> enderecos = new ArrayList<Endereco>();
				enderecos = (List<Endereco>) request.getAttribute("enderecos");
				if (enderecos != null && !enderecos.isEmpty()){
					int linha = 0;
					for (Endereco d : enderecos) {
						if (linha == 4) {
							linha = 0 ;
							out.println("<tr>");
						}
			%>
					<td>
						<div class="card" align="center" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title"><%out.println(d.getDescricao());%></h5>
								<p class="card-text"><%out.println(d.getLogradouro()); out.println(", "); out.println(d.getNumero());%></p>
								<a href="./Endereco?operacao=CONSULTAR&pagina=FormEndereco.jsp" class="btn btn-primary">Editar</a> <a
									href="#" class="btn btn-primary">Excluir</a>
							</div>
						</div>
					</td>
				
				<% linha++;
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