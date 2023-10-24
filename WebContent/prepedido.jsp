<%@ include file="Componentes/EstruturaInicio.jsp"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@page import="br.edu.fatec.celular.dominio.Pedido"%>
<%@page import="br.edu.fatec.celular.dominio.Pedidoi"%>
<%@page import="br.edu.fatec.celular.dominio.Pagamento"%>
<%@page import="br.edu.fatec.celular.util.Resultado"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="./CSS/alterarpedido.css"%>
</head>
<body>
<%@ include file = "Componentes/Cabecalho.jsp"%>
	<div class=" container-fluid my-5 ">
		<% 
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			if (cliente == null) {
				response.sendRedirect("telalogin.jsp");
			}
			Pedido pedido = new Pedido();
			pedido = (Pedido) request.getAttribute("pedido");
			
			if (pedido.getId() != null) {
				
				session.setAttribute("pedidoid", pedido.getId());
				
			}
		%>
	    <div class="row justify-content-center ">
	        <div class="col-xl-10">
	            <div class="card shadow-lg ">
	                <div class="row justify-content-around">
	                    <div class="col-md-5">
	                        <div class="card border-0">
	                            <div class="card-header pb-0">
	                                <h2 class="card-title space ">Resumo do pedido</h2>
	                                <p class="card-text text-muted mt-4 space">ENDEREÇO</p>
	                                <hr class="my-0">
	                            </div>
	                            <div class="card-body">
	                                <div class="card" align="center" style="width: 18rem;">
										<div class="card-body">
											<h5 class="card-title"><%out.println(pedido.getEndereco().getLogradouro());%></h5>
											<p class="card-text"><%out.println(pedido.getEndereco().getLogradouro()); out.println(", "); out.println(pedido.getEndereco().getNumero());%></p>
											<p class="card-text"><%out.println(pedido.getEndereco().getBairro());%></p>
											<p class="card-text"><%out.println(pedido.getEndereco().getCep());%></p>
											<a href="FormEndereco.jsp" class="btn btn-primary">Editar</a>
										</div>
									</div>
	                                <div class="row mt-4">
	                                    <div class="col">
	                                        <p class="text-muted mb-2">PAGAMENTO</p>
	                                        <hr class="mt-0">
	                                    </div>
	                                </div>
	                                
	                                <% for (Pagamento d : pedido.getPagamentos()) {%>
	                                
		                                <div class="card" align="center" style="width: 18rem;">
											<div class="card-body">
												<h5 class="card-title"><%out.println(d.getCartao().getDescricao());%></h5>
												<p class="card-text"><%out.println(d.getVlTotal());%></p>
												<p class="card-text"><%out.println(d.getQtdeParcelas());out.println("x de ");out.println(d.getVlParcela());%></p>
												<a href="FormEndereco.jsp" class="btn btn-primary">Editar</a>
												<a href="./Pagamento?operacao=EXCLUIR&codigo=<%=d.getId()%>" class="btn btn-primary">Excluir</a>
											</div>
										</div>
									<%} %>
	                                
	                                <div class="col-md-7 col-lg-6 mx-auto"><a href="./Cartao?operacao=CONSULTAR&pagina=selecionarcartao.jsp"><button type="button" class="btn btn-block btn-outline-primary btn-lg">ADICIONAR CARTÃO</button></a></div>
	                                <div class="row mb-md-5">
	                                    <div class="col-md-7 col-lg-6 mx-auto"><button type="button" class="btn btn-block btn-outline-primary btn-lg">ADICIONAR CUPOM</button></div>
	                                </div>
	                                
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-md-5">
	                        <div class="card border-0 ">
	                            <div class="card-header card-2">
	                                <p class="card-text text-muted mt-md-4 mb-2 space">PRODUTOS <span class=" small text-muted ml-2 cursor-pointer"><a href="./Carrinho?operacao=CONSULTAR&pagina=Carrinhoboot.jsp">Editar</a></span> </p>
	                                <!-- <hr class="my-2"> -->
	                            </div>
	                            <table>
		                            <tr>
			                            <td colspan="1"> 
			                            <td colspan="3"><p class="mb-0"><b>Descrição</b></p>
			                            <td colspan="1"><p><b>Quantidade</b></p>
			                            <td colspan="1"><p><b>Preço Unitário</b></p>
		                            	<td colspan="1"><p><b>Total Item</b></p>
	                            		</td>
	                            	</tr>
	                            <% for (Pedidoi d : pedido.getItens()) {%>
	                            	<tr>
	                            		<td colspan="1"><img class=" img-fluid" src="<%out.println(d.getCelular().getFoto());%>" width="62" height="62"> 
	                            		<td colspan="3"><p class="mb-0"><b><%out.println(d.getCelular().getDescricao());%></b></p>
	                            		<td colspan="1"><p><b><%out.println(d.getQtde());%></b></p>
	                            		<td colspan="1"><p><b>R$ <%out.println(d.getTotalItem());%></b></p>
	                            		<td colspan="1"><p><b>R$ <%out.println(d.getTotalItem());%></b></p>
	                            		</td>
	                            	</tr>
	                            <% } %>
	                            	<hr class="my-2">
	                            	<tr>
	                            		<td colspan="1"><p class="mb-1"><b>Subtotal</b></p>
	                            		<td colspan="3">
	                            		<td colspan="2">
	                            		<td colspan="1"><p class="mb-0"><b>R$ <%out.println(pedido.getTotalItens());%></b></p></td>
	                            	</tr>
	                            	<tr>
	                            		<td colspan="1"><p class="mb-1"><b>Frete</b></p> 
	                            		<td colspan="3">
	                            		<td colspan="2">
	                            		<td colspan="1"><p class="mb-0"><b>R$ <%out.println(pedido.getValorFrete());%></b></p></td>
	                            	</tr>
	                            	<tr>
	                            		<td colspan="1"><p class="mb-1"><b>Total</b></p>
	                            		<td colspan="3"> 
	                            		<td colspan="2">
	                            		<td colspan="1"><p class="mb-0"><b>R$ <%out.println(pedido.getPrecoTotal());%></b></p></td>
	                            	</tr>
	                            </table>
	                                    
	                                <!-- <hr class="my-2">
	                                <div class="row ">
	                                    <div class="col">
	                                        <div class="row justify-content-between">
	                                            <div class="col-4">
	                                                <p class="mb-1"><b>Subtotal</b></p>
	                                            </div>
	                                            <div class="flex-sm-col col-auto">
	                                                <p class="mb-1"><b>R$ <%out.println(pedido.getTotalItens());%></b></p>
	                                            </div>
	                                        </div>
	                                        <div class="row justify-content-between">
	                                            <div class="col">
	                                                <p class="mb-1"><b>Frete</b></p>
	                                            </div>
	                                            <div class="flex-sm-col col-auto">
	                                                <p class="mb-1"><b>R$ <%out.println(pedido.getValorFrete());%></b></p>
	                                            </div>
	                                        </div>
	                                        <div class="row justify-content-between">
	                                            <div class="col-4">
	                                                <p><b>Total</b></p>
	                                            </div>
	                                            <div class="flex-sm-col col-auto">
	                                                <p class="mb-1"><b>R$ <%out.println(pedido.getPrecoTotal());%></b></p>
	                                            </div>
	                                        </div>
	                                        <hr class="my-0">
	                                    </div>
	                                </div>
	                                 -->
	                                <div class="row mb-5 mt-4 ">
	                                    <div class="col"> <a href="./Pedido?operacao=ALTERAR&confirmado=true&codigo=<%=pedido.getId()%>&pagina=finalizarpedido.jsp"><button type="button" name="" id="" class="btn btn-lg btn-block ">FINALIZAR PEDIDO</button> </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>