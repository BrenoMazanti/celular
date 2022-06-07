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
											<a href="FormEndereco.jsp" class="btn btn-primary">Editar</a> <a
											href="#" class="btn btn-primary">Excluir</a>
										</div>
									</div>
	                                <div class="row mt-4">
	                                    <div class="col">
	                                        <p class="text-muted mb-2">PAYMENT DETAILS</p>
	                                        <hr class="mt-0">
	                                    </div>
	                                </div>
	                                <div class="form-group"> <label for="NAME" class="small text-muted mb-1">NAME ON CARD</label> <input type="text" class="form-control form-control-sm" name="NAME" id="NAME" aria-describedby="helpId" placeholder="BBBootstrap Team"> </div>
	                                <div class="form-group"> <label for="NAME" class="small text-muted mb-1">CARD NUMBER</label> <input type="text" class="form-control form-control-sm" name="NAME" id="NAME" aria-describedby="helpId" placeholder="4534 5555 5555 5555"> </div>
	                                <div class="row no-gutters">
	                                    <div class="col-sm-6 pr-sm-2">
	                                        <div class="form-group"> <label for="NAME" class="small text-muted mb-1">VALID THROUGH</label> <input type="text" class="form-control form-control-sm" name="NAME" id="NAME" aria-describedby="helpId" placeholder="06/21"> </div>
	                                    </div>
	                                    <div class="col-sm-6">
	                                        <div class="form-group"> <label for="NAME" class="small text-muted mb-1">CVC CODE</label> <input type="text" class="form-control form-control-sm" name="NAME" id="NAME" aria-describedby="helpId" placeholder="183"> </div>
	                                    </div>
	                                </div>
	                                <div class="row mb-md-5">
	                                    <div class="col"> <button type="button" name="" id="" class="btn btn-lg btn-block ">PURCHASE $37 SEK</button> </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-md-5">
	                        <div class="card border-0 ">
	                            <div class="card-header card-2">
	                                <p class="card-text text-muted mt-md-4 mb-2 space">YOUR ORDER <span class=" small text-muted ml-2 cursor-pointer">EDIT SHOPPING BAG</span> </p>
	                                <hr class="my-2">
	                            </div>
	                            <div class="card-body pt-0">
	                                <div class="row justify-content-between">
	                                    <div class="col-auto col-md-7">
	                                        <div class="media flex-column flex-sm-row"> <img class=" img-fluid" src="https://i.imgur.com/6oHix28.jpg" width="62" height="62">
	                                            <div class="media-body my-auto">
	                                                <div class="row ">
	                                                    <div class="col-auto">
	                                                        <p class="mb-0"><b>EC-GO Bag Standard</b></p><small class="text-muted">1 Week Subscription</small>
	                                                    </div>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div class=" pl-0 flex-sm-col col-auto my-auto">
	                                        <p class="boxed-1">2</p>
	                                    </div>
	                                    <div class=" pl-0 flex-sm-col col-auto my-auto ">
	                                        <p><b>179 SEK</b></p>
	                                    </div>
	                                </div>
	                                <hr class="my-2">
	                                <div class="row justify-content-between">
	                                    <div class="col-auto col-md-7">
	                                        <div class="media flex-column flex-sm-row"> <img class=" img-fluid " src="https://i.imgur.com/9MHvALb.jpg" width="62" height="62">
	                                            <div class="media-body my-auto">
	                                                <div class="row ">
	                                                    <div class="col">
	                                                        <p class="mb-0"><b>EC-GO Bag Standard</b></p><small class="text-muted">2 Week Subscription</small>
	                                                    </div>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div class="pl-0 flex-sm-col col-auto my-auto">
	                                        <p class="boxed">3</p>
	                                    </div>
	                                    <div class="pl-0 flex-sm-col col-auto my-auto">
	                                        <p><b>179 SEK</b></p>
	                                    </div>
	                                </div>
	                                <hr class="my-2">
	                                <div class="row justify-content-between">
	                                    <div class="col-auto col-md-7">
	                                        <div class="media flex-column flex-sm-row"> <img class=" img-fluid " src="https://i.imgur.com/6oHix28.jpg" width="62" height="62">
	                                            <div class="media-body my-auto">
	                                                <div class="row ">
	                                                    <div class="col">
	                                                        <p class="mb-0"><b>EC-GO Bag Standard</b></p><small class="text-muted">2 Week Subscription</small>
	                                                    </div>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div class="pl-0 flex-sm-col col-auto my-auto">
	                                        <p class="boxed-1">2</p>
	                                    </div>
	                                    <div class="pl-0 flex-sm-col col-auto my-auto">
	                                        <p><b>179 SEK</b></p>
	                                    </div>
	                                </div>
	                                <hr class="my-2">
	                                <div class="row ">
	                                    <div class="col">
	                                        <div class="row justify-content-between">
	                                            <div class="col-4">
	                                                <p class="mb-1"><b>Subtotal</b></p>
	                                            </div>
	                                            <div class="flex-sm-col col-auto">
	                                                <p class="mb-1"><b>179 SEK</b></p>
	                                            </div>
	                                        </div>
	                                        <div class="row justify-content-between">
	                                            <div class="col">
	                                                <p class="mb-1"><b>Shipping</b></p>
	                                            </div>
	                                            <div class="flex-sm-col col-auto">
	                                                <p class="mb-1"><b>0 SEK</b></p>
	                                            </div>
	                                        </div>
	                                        <div class="row justify-content-between">
	                                            <div class="col-4">
	                                                <p><b>Total</b></p>
	                                            </div>
	                                            <div class="flex-sm-col col-auto">
	                                                <p class="mb-1"><b>537 SEK</b></p>
	                                            </div>
	                                        </div>
	                                        <hr class="my-0">
	                                    </div>
	                                </div>
	                                <div class="row mb-5 mt-4 ">
	                                    <div class="col-md-7 col-lg-6 mx-auto"><button type="button" class="btn btn-block btn-outline-primary btn-lg">ADD GIFT CODE</button></div>
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