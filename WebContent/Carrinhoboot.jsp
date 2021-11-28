<%@ include file="Componentes/EstruturaInicio.jsp"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@page import="br.edu.fatec.celular.dominio.Carrinho"%>
<%@page import="br.edu.fatec.celular.dominio.Carrinhoi"%>
<%@page import="br.edu.fatec.celular.util.Resultado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="./CSS/carrinho.css"%>
</head>
<body>
	<%@ include file = "Componentes/Cabecalho.jsp"%>
	<div class="container padding-bottom-3x mb-1">
	    <!-- Alert-->
	    <% 
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			if (cliente == null) {
				response.sendRedirect("telalogin.jsp");
			}
			Carrinho carrinho = new Carrinho();
			carrinho = (Carrinho) request.getAttribute("carrinho");
			if (carrinho.getItens().isEmpty()){
		%>  	
				<div class="alert alert-info alert-dismissible fade show text-center" style="margin-bottom: 30px; margin-top: 20px;"><span class="alert-close" data-dismiss="alert"></span><img class="d-inline align-center" src="" width="18" height="18" alt="Medal icon">&nbsp;&nbsp; <strong>Seu carrinho está vazio! Adicione produtos! </strong> </div>
			
		<%  }
			else {
		%>
	    
	    <!--<div class="alert alert-info alert-dismissible fade show text-center" style="margin-bottom: 30px; margin-top: 20px;"><span class="alert-close" data-dismiss="alert"></span><img class="d-inline align-center" src="" width="18" height="18" alt="Medal icon">&nbsp;&nbsp; <strong> </strong> </div>-->
	    <!-- Shopping Cart-->
	    <div class="table-responsive shopping-cart">
	        <table class="table">
	            <thead>
	                <tr>
	                    <th>Produto</th>
	                    <th class="text-center">Quantidade</th>
	                    <th class="text-center">Subtotal</th>
	                    <th class="text-center">Desconto</th>
	                    <th class="text-center"><a class="btn btn-sm btn-outline-danger" href="./Carrinhoi?operacao=EXCLUIR&pagina=Carrinhoboot.jsp">Limpar Carrinho</a></th>
	                </tr>
	            </thead>
	            <tbody>
	            	<% 
						for (Carrinhoi d : carrinho.getItens()) {
					%>
		                <tr>
		                    <td>
		                        <div class="product-item">
		                            <a class="product-thumb" href="#"><img src="<%=d.getCelular().getFoto()%>" alt="Product"></a>
		                            <div class="product-info">
		                                <h4 class="product-title"><a href="#"><%=d.getCelular().getDescricao()%></a></h4><span><em>RAM:</em> <%=d.getCelular().getRam()%></span><span><em>Cor:</em> </span>
		                            </div>
		                        </div>
		                    </td>
		                    <td class="text-center">
		                        <div class="count-input">
		                            <input type="number" class="form-control" value = "<%=d.getQtde()%>" min="1" max="5">
		                        </div>
		                    </td>
		                    <td class="text-center text-lg text-medium">R$ <%=d.getPrecoUni()%></td>
		                    <td class="text-center text-lg text-medium">--</td>
		                    <td class="text-center"><a class="remove-from-cart" href="./Carrinhoi?operacao=EXCLUIR&pagina=Carrinhoboot.jsp
		                    &carrinhoiid=<%=d.getId()%>" data-toggle="tooltip" title="" data-original-title="Remove item"><i class="fa fa-trash"></i></a></td>
		                </tr>
		        	<%
		        		} 
		        	%>
	            </tbody>
	        </table>
	    </div>
	    <%
			}
	    %>
	    <div class="shopping-cart-footer">
	        <div class="column">
	            <form class="coupon-form" method="post">
	                <input class="form-control form-control-sm" type="text" placeholder="Coupon code" required="">
	                <button class="btn btn-outline-primary btn-sm" type="submit">Aplicar Cupom</button>
	            </form>
	        </div>
	        <div class="column text-lg">Subtotal: <span class="text-medium">R$ <%=carrinho.getPrecoTotal()%></span></div>
	    </div>
	    <div class="shopping-cart-footer">
	        <div class="column"><a class="btn btn-outline-secondary" href="./Celular?operacao=LISTAR&pagina=telainicial.jsp"><i class="icon-arrow-left"></i>&nbsp;Voltar para inicial</a></div>
	        <div class="column"><a class="btn btn-primary" href="#" data-toast="" data-toast-type="success" data-toast-position="topRight" data-toast-icon="icon-circle-check" data-toast-title="Your cart" data-toast-message="is updated successfully!">Update Cart</a><a class="btn btn-success" 
	        href="./Pedido?operacao=SALVAR&pagina=selecionarendereco.jsp">Finalizar pedido</a></div>
	    </div>
	</div>

	<script type="text/javascript">
		function calcularFrete() {
			//var valor = document.getElementById("txtCep").value;
			var frete = 10;
			var subtotal = document.getElementById("txtSubtotal").value;
			
			document.getElementById("txtFrete").value = frete.toFixed(2).replace(".", ",");
			
			var total = eval(frete) + eval(subtotal);
			
			document.getElementById("txtTotal").value = total ;
		}
	</script>
</body>
</html>