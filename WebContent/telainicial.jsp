<%@ include file="Componentes/EstruturaInicio.jsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.fatec.celular.dominio.Celular"%>
<%@ include file="./CSS/telas.css"%>
<body>
	<%@ include file = "Componentes/Cabecalho.jsp"%>
	<%
		session.setAttribute("pagina", pageContext.getPage().getClass().getSimpleName().replaceAll("_", "."));
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
						<a href="InfoProduto.jsp" class="btn btn-primary">Informações</a>
						<button onclick="addCarrinho();" style="margin-top:5px">Adicionar ao Carrinho</button>
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