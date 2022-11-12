<%@ include file="Componentes/EstruturaInicio.jsp"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@page import="br.edu.fatec.celular.dominio.Pedido"%>
<%@page import="br.edu.fatec.celular.dominio.Pedidoi"%>
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
		%>  	
	    <div class="row justify-content-center ">
	        <div class="col-xl-10">
	            <div class="card shadow-lg ">
	                <div class="row justify-content-around">
	                    <div class="col-md-5">
	                        <div class="card border-0">
	                            <div class="card-header pb-0">
	                                <h2 class="card-title space ">Resumo do pedido</h2>
	                                <p class="card-text text-muted mt-4 space">Endereço</p>
	                                <hr class="my-0">
	                            </div>
	                            <div class="card-body">
	                                <div class="card" align="center" style="width: 18rem;">
										<div class="card-body">
											<h5 class="card-title"><%out.println(pedido.getEndereco().getLogradouro());%></h5>
											<p class="card-text"><%out.println(pedido.getEndereco().getLogradouro()); out.println(", "); out.println(pedido.getEndereco().getNumero());%></p>
											<p class="card-text"><%out.println(pedido.getEndereco().getBairro());%></p>
											<p class="card-text"><%out.println(pedido.getEndereco().getCep());%></p>
											<a href="FormEndereco.jsp" class="btn btn-primary">Editar</a> <a
											href="#" class="btn btn-primary">Excluir</a>
										</div>
									</div>
	                                <div class="row mt-4">
	                                    <div class="col">
	                                        <p class="text-muted mb-2">PAGAMENTO</p>
	                                        <hr class="mt-0">
	                                    </div>
	                                </div>
	                                <div class="col-md-7 col-lg-6 mx-auto"><button type="button" class="btn btn-block btn-outline-primary btn-lg">SELECIONAR CARTÃO</button></div>
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
	                                <hr class="my-2">
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
	                            </table>
	                            
	                
	                                                <div class="row ">
	                                                    <div class="col-auto col-md-6">
	                                                        <p class="mb-0"><b>Descrição</b></p><!--  <small class="text-muted">1 Week Subscription</small>-->
	                                                    </div>
	                                                <div class="col-auto col-md-3">
	                                        			<p><b>Quantidade</b></p>
	                                   				 </div>
	                                    			<div class="col-auto col-md-3">
	                                       				<p><b>Total Item</b></p>
	                                    			</div>
	                                                   
	                                                </div>
	                                    
	                                
	                                <hr class="my-2">
	                            	<% for (Pedidoi d : pedido.getItens()) {%>
	                                    <div class="col-auto col-md-5">
	                                        <div class="media flex-column flex-sm-row"> <img class=" img-fluid" src="<%out.println(d.getCelular().getFoto());%>" width="62" height="62">
	                                            <div class="media-body my-auto">
	                                                <div class="row ">
	                                                    <div class="col-auto col-md-8">
	                                                        <p class="mb-0"><b><%out.println(d.getCelular().getDescricao());%></b></p><!--  <small class="text-muted">1 Week Subscription</small>-->
	                                                    </div>
	                                                    <div class="col-auto col-md-2">
	                                        				<p class="boxed-1"><%out.println(d.getQtde());%></p>
	                                    				</div>
	                                    				<div class="col-auto col-md-5">
	                                        				<p><b>R$ <%out.println(d.getTotalItem());%></b></p>
	                                    				</div>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    
	                                    
	                                </div>
	                                <hr class="my-2">
	                                <%} %>
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
	                                <div class="row mb-5 mt-4 ">
	                                    <div class="col"> <button type="button" name="" id="" class="btn btn-lg btn-block ">FINALIZAR PEDIDO</button> </div>
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