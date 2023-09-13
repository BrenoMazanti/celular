<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.fatec.celular.dominio.Cartao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/Componentes/EstruturaInicio.jsp"%>
<meta charset="ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
</head>
<body>
	<%@ include file="./Componentes/Cabecalho.jsp"%>
	<%@ include file="./Componentes/LoginBlock.jsp"%>
	<%
		if (request.getAttribute("resultado") != null) {
			out.println("<div id = 'resultado'>");
			out.println(request.getAttribute("resultado") + "</div>");
		}
	%>
	<form action="Cartao" method="post" style = "margin-left: 5%; margin-right: 5%;">
		
		<%
		    List<Cartao> cartoes = new ArrayList<Cartao>();
		    cartoes = (List<Cartao>) request.getAttribute("cartoes");
		    // Cartao cartao = (Cartao)request.getAttribute("cartao");
		    Cartao cartao = null;
		    if (cartoes != null && !cartoes.isEmpty()) {
		        cartao = cartoes.get(0);
		    }
		    if (cartao != null && cartao.getId() != null) { // cartao existe, será alteração
		%>
		
		<input type="hidden" id="txtId" name="txtId" value=<%=cartao.getId()%>>
		
		<div class="form-group">
			<label for="txtDescricao">Descrição</label> <input
				type="text" class="form-control" id="txtDescricao"
				name="txtDescricao" placeholder="Ex: Meu Cartão" >
		</div>
		
		<div class="form-group">
			<label for="txtNumeroCartao">Número do Cartão</label> <input
				type="text" class="form-control" id="txtNumeroCartao"
				name="txtNumeroCartao" placeholder="0000.0000.0000.0000" >
		</div>

		<div class="form-group">
			
			<label for="txtMes">Validade</label> 
				
				<select class="form-control" id="txtMes" name="txtMes">
					
				<% for(int i = 1; i <= 12; i++){
					out.println("<option>" + i + "</option>");
				}%>
				
				</select>

				<select class="form-control" id="txtAno" name="txtAno">
					
			    <%
			      GregorianCalendar cal = new GregorianCalendar();
			      for(int i = cal.get(Calendar.YEAR); i < (cal.get(Calendar.YEAR) + 8); i++){
			    	  
					out.println("<option value = " + i + ">" + i + "</option>");
					
					} %>
				</select>
		</div>
		
		<div class="form-group">
			<label for="txtCodigo">Código de Segurança</label> <input
				type="text" class="form-control" id="txtCodigo"
				name="txtCodigo" maxlength = "3" >
		</div>
		
		<div class="form-group">
			<label for="txtNomeTitular">Nome Impresso no Cartão</label> <input
				type="text" class="form-control" id="txtNomeTitular"
				name="txtNomeTitular" placeholder="" >
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='ALTERAR'
				class="btn btn-primary" />
		</div>

		<%
		    }
		    
		    else if(cartao != null && cartao.getId() == null){
		%>

		<div class="form-group">
			<label for="txtDescricao">Descrição</label> <input
				type="text" class="form-control" id="txtDescricao"
				name="txtDescricao" placeholder="Ex: Meu Cartão" required>
		</div>
		
		<div class="form-group">
			<label for="txtNumeroCartao">Número do Cartão</label> <input
				type="text" class="form-control" id="txtNumeroCartao"
				name="txtNumeroCartao" placeholder="0000.0000.0000.0000" required>
		</div>

		<div class="form-group">
			
			<label for="txtMes">Validade</label> 
				
				<select class="form-control" id="txtMes" name="txtMes">
					
				<% for(int i = 1; i <= 12; i++){
					out.println("<option>" + i + "</option>");
				}%>
				
				</select>

				<select class="form-control" id="txtAno" name="txtAno">
					
			    <%
			      GregorianCalendar cal = new GregorianCalendar();
			      for(int i = cal.get(Calendar.YEAR); i < (cal.get(Calendar.YEAR) + 8); i++){
			    	  
					out.println("<option value = " + i + ">" + i + "</option>");
					
					} %>
				</select>
		</div>
		
		<div class="form-group">
			<label for="txtCodigo">Código de Segurança</label> <input
				type="text" class="form-control" id="txtCodigo"
				name="txtCodigo" maxlength = "3" required>
		</div>
		
		<div class="form-group">
			<label for="txtNomeTitular">Nome Impresso no Cartão</label> <input
				type="text" class="form-control" id="txtNomeTitular"
				name="txtNomeTitular" placeholder="" required>
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='SALVAR'
				class="btn btn-primary" />
		</div>

		<%
			}
		    else {
		%>

			<div class="form-group">
			<label for="txtDescricao">Descrição</label> <input
				type="text" class="form-control" id="txtDescricao"
				name="txtDescricao" placeholder="Ex: Meu Cartão" required>
		</div>
		
		<div class="form-group">
			<label for="txtNumeroCartao">Número do Cartão</label> <input
				type="text" class="form-control" id="txtNumeroCartao"
				name="txtNumeroCartao" placeholder="0000.0000.0000.0000" required>
		</div>

		<div class="form-group">
			
			<label for="txtMes">Validade</label> 
				
				<select class="form-control" id="txtMes" name="txtMes">
					
				<% for(int i = 1; i <= 12; i++){
					out.println("<option>" + i + "</option>");
				}%>
				
				</select>

				<select class="form-control" id="txtAno" name="txtAno">
					
			    <%
			      GregorianCalendar cal = new GregorianCalendar();
			      for(int i = cal.get(Calendar.YEAR); i < (cal.get(Calendar.YEAR) + 8); i++){
			    	  
					out.println("<option value = " + i + ">" + i + "</option>");
					
					} %>
				</select>
		</div>
		
		<div class="form-group">
			<label for="txtCodigo">Código de Segurança</label> <input
				type="text" class="form-control" id="txtCodigo"
				name="txtCodigo" maxlength = "3" required>
		</div>
		
		<div class="form-group">
			<label for="txtNomeTitular">Nome Impresso no Cartão</label> <input
				type="text" class="form-control" id="txtNomeTitular"
				name="txtNomeTitular" placeholder="" required>
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='SALVAR'
				class="btn btn-primary" />
		</div>
			
		<%  }%>
	</form>
	
	<script type="text/javascript">
		$("#resultado").show();
		setTimeout(function() {
			$("#resultado").hide();
		}, 5000);

		$(document).ready(function() {
			
			$("#txtNumeroCartao").mask("0000.0000.0000.0000");
			$("#txtCodigo").mask("000");
		});
	</script>

</body>
</html>