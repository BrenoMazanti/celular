<%@ include file="Componentes/EstruturaInicio.jsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.fatec.celular.dominio.Celular"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<%@ include file="./CSS/telas.css"%>
<style>
img {
	height: 200px;
	width: 200px;
}
ul > li{margin-right:25px;font-weight:lighter;cursor:pointer}
li.active{border-bottom:3px solid silver;}

.item-photo{display:flex;justify-content:center;align-items:center;border-right:1px solid #f6f6f6;}
.menu-items{list-style-type:none;font-size:11px;display:inline-flex;margin-bottom:0;margin-top:20px}
.btn-success{width:100%;border-radius:0;}
.section{width:100%;margin-left:-15px;padding:2px;padding-left:15px;padding-right:15px;background:#f8f9f9}
.title-price{margin-top:30px;margin-bottom:0;color:black}
.title-attr{margin-top:0;margin-bottom:0;color:black;}
.btn-minus{cursor:pointer;font-size:7px;display:flex;align-items:center;padding:5px;padding-left:10px;padding-right:10px;border:1px solid gray;border-radius:2px;border-right:0;}
.btn-plus{cursor:pointer;font-size:7px;display:flex;align-items:center;padding:5px;padding-left:10px;padding-right:10px;border:1px solid gray;border-radius:2px;border-left:0;}
div.section > div {width:100%;display:inline-flex;}
div.section > div > input {margin:0;padding-left:5px;font-size:10px;padding-right:5px;max-width:18%;text-align:center;}
.attr,.attr2{cursor:pointer;margin-right:5px;height:20px;font-size:10px;padding:2px;border:1px solid gray;border-radius:2px;}
.attr.active,.attr2.active{ border:1px solid orange;}

@media (max-width: 426px) {
    .container {margin-top:0px !important;}
    .container > .row{padding:0 !important;}
    .container > .row > .col-xs-12.col-sm-5{
        padding-right:0 ;    
    }
    .container > .row > .col-xs-12.col-sm-9 > div > p{
        padding-left:0 !important;
        padding-right:0 !important;
    }
    .container > .row > .col-xs-12.col-sm-9 > div > ul{
        padding-left:10px !important;
        
    }            
    .section{width:104%;}
    .menu-items{padding-left:0;}
}
</style>
<body>
	<%@ include file = "Componentes/Cabecalho.jsp"%>
    <% 
    	List<Celular> celulares = new ArrayList<Celular>();
		celulares = (List<Celular>) request.getAttribute("celulares");
    	Celular celular = celulares.get(0);
    	
    	if(celular != null) {
    %>
	<div class="container">
        	<div class="row">
               <div class="col-xs-4 item-photo">
                    <img style="max-width:100%;" src="<%=celular.getFoto()%>" />
                </div>
                <div class="col-xs-5" style="border:0px solid gray">
                    <!-- Nome do Produto -->
                    <h3><%=celular.getDescricao()%></h3>    
        
                    <h3 style="margin-top:0px;">R$ <%=celular.getPreco()%></h3>
        
                    <!-- Detalles especificos del producto -->
                    <div class="section">
                         <!--<h6 class="title-attr" style="margin-top:15px;" ><small>COR</small></h6>                    
                        <div>
                            <div class="attr" style="width:25px;background:#5a5a5a;"></div>
                            <div class="attr" style="width:25px;background:white;"></div>
                        </div>-->
                    </div>
                    <div class="section" style="padding-bottom:5px;">
                        <h6 class="title-attr"><small>RAM</small></h6>                    
                        <div>
                            <div ><%=celular.getRam()%></div>
                        </div>
                    </div>   
                    <div class="section" style="padding-bottom:20px;">
                        <h6 class="title-attr"><small>QUANTIDADE</small></h6>                    
                        <div>
                            <div class="btn-minus"><span class="glyphicon glyphicon-minus"></span></div>
                            <input value="1" />
                            <div class="btn-plus"><span class="glyphicon glyphicon-plus"></span></div>
                        </div>
                    </div>                
        
                    <!-- Botones de compra -->
                    <div class="section" style="padding-bottom:20px;">
                    
                        <% 
							Cliente cliente = (Cliente) session.getAttribute("cliente");
							if (cliente != null) {
							
						%>
                        <a href="./Carrinhoi?operacao=SALVAR&pagina=Carrinhoboot.jsp
                        &celularid=<%=celular.getId()%>
						&celularpreco=<%=celular.getPreco()%>
						&celular=<%=celular%>" 
                        class="btn btn-primary">Adicionar ao Carrinho</a>
                        
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
        
                <div class="col-xs-9">
                    <ul class="menu-items">
                        <li class="active">Detalhes do produto</li>
                    </ul>
                    <div style="width:100%;border-top:1px solid silver">
                        <p style="padding:15px;">
                        </p>
                            <ul>
	                                <li>Altura - <%=celular.getAltura()%></li>
	                                <li>Largura - <%=celular.getLargura()%></li>
	                                <li>Comprimento - <%=celular.getComprimento()%></li>
	                                <li>Peso - <%=celular.getPeso()%></li>
	                                <li>Câmera Frontal - <%=celular.getCameraFrontal()%></li>
	                                <li>Câmera Traseira - <%=celular.getCameraTraseira()%></li>
	                                <li>Processador - <%=celular.getProcessador()%></li>
	                                <li>RAM - <%=celular.getRam()%></li>
	                                <li>Resolução - <%=celular.getResolucao()%></li>
	                                <li>Tamanho da Tela - <%=celular.getTamanhoTela()%></li>
	                                <li>Tipo de Chip - <%=celular.getTipoChip()%></li>
	                                <li>Componentes - <%=celular.getComponentes()%></li>
                            </ul>  
                    </div>
                </div>		
            </div>
        </div>

	<% } %>

</body>
</html>