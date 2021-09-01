<%@ include file="/Componentes/EstruturaInicio.jsp"%>
<%@page import="br.edu.fatec.celular.dominio.Endereco"%>
</head>
<body>
	<%@ include file="./Componentes/Cabecalho.jsp"%>
	<%
		
	
		if (request.getAttribute("resultado") != null) {
			out.println("<div id = 'resultado'>");
			out.println(request.getAttribute("resultado") + "</div>");
		}
	%>
	<form action="Endereco" method="post" style = "margin-left: 5%; margin-right: 5%;">
	
	<%
	
		Endereco endereco = (Endereco) session.getAttribute("endereco");
		if(endereco != null){ 
	
	%>
		
		<div class="form-group">
			<label for="txtDescricao">Descri��o</label> <input type="text"
				class="form-control" id="txtDescricao" name="txtDescricao"
				placeholder="Ex: Casa da v�"
				value=<%=endereco.getLogradouro()%>>
		</div>
		
		<div class="form-group">
			<label for="txtLogradouro">Logradouro</label> <input type="text"
				class="form-control" id="txtLogradouro" name="txtLogradouro"
				placeholder="Ex: Rua 12"
				required>
		</div>

		<div class="form-group">
			<label for="txtNumero">Numero</label> <input type="text"
				class="form-control" id="txtNumero" name="txtNumero"
				required>
		</div>

		<div class="form-group">
			<label for="txtComplemento">Complemento</label> <input type="text"
				class="form-control" id="txtComplemento" name="txtComplemento"
				placeholder="Ex: Apartamento 3">
		</div>

		<div class="form-group">
			<label for="txtCep">CEP</label> <input type="text"
				class="form-control" id="txtCep" name="txtCep"
				placeholder="00000-000"
				required>
		</div>

		<div class="form-group">
			<label for="txtBairro">Bairro</label> <input type="text"
				class="form-control" id="txtBairro" name="txtBairro"
				required>
		</div>

		<div class="form-group">
			<label for="txtCidade">Cidade</label> <input type="text"
				class="form-control" id="txtCidade" name="txtCidade"
				placeholder="Ex: S�o Paulo"
				required>
		</div>

		<div class="form-group">
			<label for="txtEstado">Estado</label> <select class = "input-field col s28" id = "txtEstado" name = "txtEstado" required>
				<option value="" selected>Selecione</option>
				<option value="AC">Acre</option>
				<option value="AL">Alagoas</option>
				<option value="AP">Amap�</option>
				<option value="AM">Amazonas</option>
				<option value="BA">Bahia</option>
				<option value="CE">Cear�</option>
				<option value="DF">Distrito Federal</option>
				<option value="ES">Espirito Santo</option>
				<option value="GO">Goi�s</option>
				<option value="MA">Maranh�o</option>
				<option value="MS">Mato Grosso do Sul</option>
				<option value="MT">Mato Grosso</option>
				<option value="MG">Minas Gerais</option>
				<option value="PA">Par�</option>
				<option value="PB">Para�ba</option>
				<option value="PR">Paran�</option>
				<option value="PE">Pernambuco</option>
				<option value="PI">Piau�</option>
				<option value="RJ">Rio de Janeiro</option>
				<option value="RN">Rio Grande do Norte</option>
				<option value="RS">Rio Grande do Sul</option>
				<option value="RO">Rond�nia</option>
				<option value="RR">Roraima</option>
				<option value="SC">Santa Catarina</option>
				<option value="SP">S�o Paulo</option>
				<option value="SE">Sergipe</option>
				<option value="TO">Tocantins</option>
			</select>
		</div>
		
		<div class="form-group">
			<label for="principal">Salvar como endere�o principal de entrega</label> <input type="checkbox"
				 id="principal" name="principal" checked="false">
		</div>
		
		<div class="form-group">
			<label for="cobranca">Salvar como endere�o de cobran�a</label> <input type="checkbox"
				 id="cobranca" name="cobranca" checked="false">
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='ALTERAR'
				class="btn btn-primary" />
		</div>
		
	<%  }
		    
		else{
	%>
		<div class="form-group">
			<label for="txtDescricao">Descri��o</label> <input type="text"
				class="form-control" id="txtDescricao" name="txtDescricao"
				placeholder="Ex: Casa da v�"
				required>
		</div>
		
		<div class="form-group">
			<label for="txtLogradouro">Logradouro</label> <input type="text"
				class="form-control" id="txtLogradouro" name="txtLogradouro"
				placeholder="Ex: Rua 12"
				required>
		</div>

		<div class="form-group">
			<label for="txtNumero">Numero</label> <input type="text"
				class="form-control" id="txtNumero" name="txtNumero"
				required>
		</div>

		<div class="form-group">
			<label for="txtComplemento">Complemento</label> <input type="text"
				class="form-control" id="txtComplemento" name="txtComplemento"
				placeholder="Ex: Apartamento 3">
		</div>

		<div class="form-group">
			<label for="txtCep">CEP</label> <input type="text"
				class="form-control" id="txtCep" name="txtCep"
				placeholder="00000-000"
				required>
		</div>

		<div class="form-group">
			<label for="txtBairro">Bairro</label> <input type="text"
				class="form-control" id="txtBairro" name="txtBairro"
				required>
		</div>

		<div class="form-group">
			<label for="txtCidade">Cidade</label> <input type="text"
				class="form-control" id="txtCidade" name="txtCidade"
				placeholder="Ex: S�o Paulo"
				required>
		</div>

		<div class="form-group">
			<label for="txtEstado">Estado</label> <select class = "input-field col s28" id = "txtEstado" name = "txtEstado" required>
				<option value="" selected>Selecione</option>
				<option value="AC">Acre</option>
				<option value="AL">Alagoas</option>
				<option value="AP">Amap�</option>
				<option value="AM">Amazonas</option>
				<option value="BA">Bahia</option>
				<option value="CE">Cear�</option>
				<option value="DF">Distrito Federal</option>
				<option value="ES">Espirito Santo</option>
				<option value="GO">Goi�s</option>
				<option value="MA">Maranh�o</option>
				<option value="MS">Mato Grosso do Sul</option>
				<option value="MT">Mato Grosso</option>
				<option value="MG">Minas Gerais</option>
				<option value="PA">Par�</option>
				<option value="PB">Para�ba</option>
				<option value="PR">Paran�</option>
				<option value="PE">Pernambuco</option>
				<option value="PI">Piau�</option>
				<option value="RJ">Rio de Janeiro</option>
				<option value="RN">Rio Grande do Norte</option>
				<option value="RS">Rio Grande do Sul</option>
				<option value="RO">Rond�nia</option>
				<option value="RR">Roraima</option>
				<option value="SC">Santa Catarina</option>
				<option value="SP">S�o Paulo</option>
				<option value="SE">Sergipe</option>
				<option value="TO">Tocantins</option>
			</select>
		</div>
		
		<div class="form-group">
			<label for="principal">Salvar como endere�o principal de entrega</label> <input type="checkbox"
				 id="principal" name="principal">
		</div>
		
		<div class="form-group">
			<label for="cobranca">Salvar como endere�o de cobran�a</label> <input type="checkbox"
				 id="cobranca" name="cobranca">
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='SALVAR'
				class="btn btn-primary" />
		</div>
	<%} %>
	</form>

	<script type="text/javascript">
		$("#resultado").show();
		setTimeout(function() {
			$("#resultado").hide();
		}, 10000);

		$(document).ready(function() {
			
			$("#txtNumero").mask("000000");
			$("#txtCep").mask("00000-000");
			
		});
	</script>

</body>
</html>