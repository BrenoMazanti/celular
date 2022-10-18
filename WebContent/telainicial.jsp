<%@ include file="Componentes/EstruturaInicio.jsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.fatec.celular.dominio.Celular"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@ include file="./CSS/telas.css"%>
</head>
<body>
	<%@ include file = "Componentes/Cabecalho.jsp"%>
	<%
		//session.setAttribute("pagina", pageContext.getPage().getClass().getSimpleName().replaceAll("_", "."));
		session.setAttribute("pagina", "iniciar.jsp");
	    List<Celular> celulares = new ArrayList<Celular>();
	    celulares = (List<Celular>) request.getAttribute("celulares");
	%>
	<table align="center">
	    <tr>
			<%
			    if (celulares != null && !celulares.isEmpty()){
				    int linha = 0;
				    for (Celular d : celulares) {
				    	if(linha == 4){
				    		linha = 0 ;
							out.println("<tr>");
				    	}
			%>
			<td>
				<div class="card" align="center" style="width: 18rem;">
					<img
						src="<%=d.getFoto()%>"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><%=d.getDescricao()%></h5>
						<p class="card-text">R$<%=d.getPreco()%></p>
						<a href="./Celular?operacao=CONSULTAR&codigo=<%=d.getId()%>&pagina=InfoProduto.jsp" class="btn btn-primary">Informações</a>
						<% 
							Cliente cliente = (Cliente) session.getAttribute("cliente");
							if (cliente != null) {
							
						%>
						<a href="./Carrinhoi?operacao=SALVAR&pagina=Carrinhoboot.jsp
						&celularid=<%=d.getId()%>
						&celularpreco=<%=d.getPreco()%>
						&celular=<%=d%>" class="btn btn-outline-secondary">Adicionar ao Carrinho</a>
						<%
							}
							else{
						%>
						<a href="telalogin.jsp" class="btn btn-outline-secondary">Adicionar ao Carrinho</a>
						<%
							}
						%>
					</div>
				</div>
			</td>
			<%
			    linha++;
				}
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
	
	<script type="text/javascript">
		function addCarrinho() {
			//var valor = document.getElementById("txtCep").value;
			alert("Produto Adicionado ao carrinho");
		}
	</script>
</body>
</html>