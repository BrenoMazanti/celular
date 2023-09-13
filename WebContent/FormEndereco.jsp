<%@ include file="/Componentes/EstruturaInicio.jsp"%>
<%@page import="br.edu.fatec.celular.dominio.Endereco"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	<form action="Endereco" method="post" style = "margin-left: 5%; margin-right: 5%;">
	
	<%
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos = (List<Endereco>) request.getAttribute("enderecos");
		//Endereco endereco = (Endereco)request.getAttribute("endereco");
		Endereco endereco = null ;
		if (enderecos != null && !enderecos.isEmpty()) {
        	endereco = enderecos.get(0);
    	}
		if(endereco != null && endereco.getId() != null){ // endereço existe, será alteração
	
	%>
		<input type="hidden" id="txtId" name="txtId" value=<%=endereco.getId()%>>
		
		<div class="form-group">
			<label for="txtDescricao">Descrição</label> <input type="text"
				class="form-control" id="txtDescricao" name="txtDescricao"
				placeholder="Ex: Casa da vó"
				value="<%=endereco.getDescricao()%>">
		</div>
		
		<div class="form-group">
			<label for="txtLogradouro">Logradouro</label> <input type="text"
				class="form-control" id="txtLogradouro" name="txtLogradouro"
				placeholder="Ex: Rua 12"
				value="<%=endereco.getLogradouro()%>">
		</div>

		<div class="form-group">
			<label for="txtNumero">Numero</label> <input type="text"
				class="form-control" id="txtNumero" name="txtNumero"
				value="<%=endereco.getNumero()%>">
		</div>

		<div class="form-group">
			<label for="txtComplemento">Complemento</label> <input type="text"
				class="form-control" id="txtComplemento" name="txtComplemento"
				placeholder="Ex: Apartamento 3"
				value="<%=endereco.getComplemento()%>">
		</div>

		<div class="form-group">
			<label for="txtCep">CEP</label> <input type="text"
				class="form-control" id="txtCep" name="txtCep"
				placeholder="00000-000"
				value="<%=endereco.getCep()%>">
		</div>

		<div class="form-group">
			<label for="txtBairro">Bairro</label> <input type="text"
				class="form-control" id="txtBairro" name="txtBairro"
				value="<%=endereco.getBairro()%>">
		</div>

		<div class="form-group">
			<label for="txtCidade">Cidade</label> <input type="text"
				class="form-control" id="txtCidade" name="txtCidade"
				placeholder="Ex: São Paulo"
				value="<%=endereco.getCidade()%>">
		</div>

		<div class="form-group">
			<label for="txtEstado">Estado</label> <select class = "input-field col s28" id = "txtEstado" name = "txtEstado">
				<option value="" <%if (endereco.getUf().equals("")) { out.print("selected"); }%>>Selecione</option>
				<option value="AC"  <%if (endereco.getUf().equals("AC")) { out.print("selected"); }%>>Acre</option>
				<option value="AL" <%if (endereco.getUf().equals("AL")) { out.print("selected"); }%>>Alagoas</option>
				<option value="AP" <%if (endereco.getUf().equals("AP")) { out.print("selected"); }%>>Amapá</option>
				<option value="AM" <%if (endereco.getUf().equals("AM")) { out.print("selected"); }%>>Amazonas</option>
				<option value="BA" <%if (endereco.getUf().equals("BA")) { out.print("selected"); }%>>Bahia</option>
				<option value="CE" <%if (endereco.getUf().equals("CE")) { out.print("selected"); }%>>Ceará</option>
				<option value="DF" <%if (endereco.getUf().equals("DF")) { out.print("selected"); }%>>Distrito Federal</option>
				<option value="ES" <%if (endereco.getUf().equals("ES")) { out.print("selected"); }%>>Espirito Santo</option>
				<option value="GO" <%if (endereco.getUf().equals("GO")) { out.print("selected"); }%>>Goiás</option>
				<option value="MA" <%if (endereco.getUf().equals("MA")) { out.print("selected"); }%>>Maranhão</option>
				<option value="MS" <%if (endereco.getUf().equals("MS")) { out.print("selected"); }%>>Mato Grosso do Sul</option>
				<option value="MT" <%if (endereco.getUf().equals("MT")) { out.print("selected"); }%>>Mato Grosso</option>
				<option value="MG" <%if (endereco.getUf().equals("MG")) { out.print("selected"); }%>>Minas Gerais</option>
				<option value="PA" <%if (endereco.getUf().equals("PA")) { out.print("selected"); }%>>Pará</option>
				<option value="PB" <%if (endereco.getUf().equals("PB")) { out.print("selected"); }%>>Paraíba</option>
				<option value="PR" <%if (endereco.getUf().equals("PR")) { out.print("selected"); }%>>Paraná</option>
				<option value="PE" <%if (endereco.getUf().equals("PE")) { out.print("selected"); }%>>Pernambuco</option>
				<option value="PI" <%if (endereco.getUf().equals("PI")) { out.print("selected"); }%>>Piauí</option>
				<option value="RJ" <%if (endereco.getUf().equals("RJ")) { out.print("selected"); }%>>Rio de Janeiro</option>
				<option value="RN" <%if (endereco.getUf().equals("RN")) { out.print("selected"); }%>>Rio Grande do Norte</option>
				<option value="RS" <%if (endereco.getUf().equals("RS")) { out.print("selected"); }%>>Rio Grande do Sul</option>
				<option value="RO" <%if (endereco.getUf().equals("RO")) { out.print("selected"); }%>>Rondônia</option>
				<option value="RR" <%if (endereco.getUf().equals("RR")) { out.print("selected"); }%>>Roraima</option>
				<option value="SC" <%if (endereco.getUf().equals("SC")) { out.print("selected"); }%>>Santa Catarina</option>
				<option value="SP" <%if (endereco.getUf().equals("SP")) { out.print("selected"); }%>>São Paulo</option>
				<option value="SE" <%if (endereco.getUf().equals("SE")) { out.print("selected"); }%>>Sergipe</option>
				<option value="TO" <%if (endereco.getUf().equals("TO")) { out.print("selected"); }%>>Tocantins</option>
			</select>
		</div>
		
		<div class="form-group">
			<label for="principal">Salvar como endereço principal de entrega</label> <input type="checkbox"
				 id="principal" name="principal" checked="false">
		</div>
		
		<div class="form-group">
			<label for="cobranca">Salvar como endereço de cobrança</label> <input type="checkbox"
				 id="cobranca" name="cobranca" checked="false">
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='ALTERAR'
				class="btn btn-primary" />
		</div>
		
	<%  }
		    
		else if(endereco != null && endereco.getId() == null){
	%>
		<div class="form-group">
			<label for="txtDescricao">Descrição</label> <input type="text"
				class="form-control" id="txtDescricao" name="txtDescricao"
				placeholder="Ex: Casa da vó"
				required
				value=<%=endereco.getDescricao()%>>
		</div>
		
		<div class="form-group">
			<label for="txtLogradouro">Logradouro</label> <input type="text"
				class="form-control" id="txtLogradouro" name="txtLogradouro"
				placeholder="Ex: Rua 12"
				required
				value=<%=endereco.getLogradouro()%>>
		</div>

		<div class="form-group">
			<label for="txtNumero">Numero</label> <input type="text"
				class="form-control" id="txtNumero" name="txtNumero"
				required
				value=<%=endereco.getLogradouro()%>>
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
				placeholder="Ex: São Paulo"
				required>
		</div>

		<div class="form-group">
			<label for="txtEstado">Estado</label> <select class = "input-field col s28" id = "txtEstado" name = "txtEstado" required>
				<option value="" selected>Selecione</option>
				<option value="AC">Acre</option>
				<option value="AL">Alagoas</option>
				<option value="AP">Amapá</option>
				<option value="AM">Amazonas</option>
				<option value="BA">Bahia</option>
				<option value="CE">Ceará</option>
				<option value="DF">Distrito Federal</option>
				<option value="ES">Espirito Santo</option>
				<option value="GO">Goiás</option>
				<option value="MA">Maranhão</option>
				<option value="MS">Mato Grosso do Sul</option>
				<option value="MT">Mato Grosso</option>
				<option value="MG">Minas Gerais</option>
				<option value="PA">Pará</option>
				<option value="PB">Paraíba</option>
				<option value="PR">Paraná</option>
				<option value="PE">Pernambuco</option>
				<option value="PI">Piauí</option>
				<option value="RJ">Rio de Janeiro</option>
				<option value="RN">Rio Grande do Norte</option>
				<option value="RS">Rio Grande do Sul</option>
				<option value="RO">Rondônia</option>
				<option value="RR">Roraima</option>
				<option value="SC">Santa Catarina</option>
				<option value="SP">São Paulo</option>
				<option value="SE">Sergipe</option>
				<option value="TO">Tocantins</option>
			</select>
		</div>
		
		<div class="form-group">
			<label for="principal">Salvar como endereço principal de entrega</label> <input type="checkbox"
				 id="principal" name="principal">
		</div>
		
		<div class="form-group">
			<label for="cobranca">Salvar como endereço de cobrança</label> <input type="checkbox"
				 id="cobranca" name="cobranca">
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='SALVAR'
				class="btn btn-primary" />
		</div>
	<%} 
	  else {
    %>
    		<div class="form-group">
			<label for="txtDescricao">Descrição</label> <input type="text"
				class="form-control" id="txtDescricao" name="txtDescricao"
				placeholder="Ex: Casa da vó"
				required
				>
		</div>
		
		<div class="form-group">
			<label for="txtLogradouro">Logradouro</label> <input type="text"
				class="form-control" id="txtLogradouro" name="txtLogradouro"
				placeholder="Ex: Rua 12"
				required
				>
		</div>

		<div class="form-group">
			<label for="txtNumero">Numero</label> <input type="text"
				class="form-control" id="txtNumero" name="txtNumero"
				required
				>
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
				placeholder="Ex: São Paulo"
				required>
		</div>

		<div class="form-group">
			<label for="txtEstado">Estado</label> <select class = "input-field col s28" id = "txtEstado" name = "txtEstado" required>
				<option value="" selected>Selecione</option>
				<option value="AC">Acre</option>
				<option value="AL">Alagoas</option>
				<option value="AP">Amapá</option>
				<option value="AM">Amazonas</option>
				<option value="BA">Bahia</option>
				<option value="CE">Ceará</option>
				<option value="DF">Distrito Federal</option>
				<option value="ES">Espirito Santo</option>
				<option value="GO">Goiás</option>
				<option value="MA">Maranhão</option>
				<option value="MS">Mato Grosso do Sul</option>
				<option value="MT">Mato Grosso</option>
				<option value="MG">Minas Gerais</option>
				<option value="PA">Pará</option>
				<option value="PB">Paraíba</option>
				<option value="PR">Paraná</option>
				<option value="PE">Pernambuco</option>
				<option value="PI">Piauí</option>
				<option value="RJ">Rio de Janeiro</option>
				<option value="RN">Rio Grande do Norte</option>
				<option value="RS">Rio Grande do Sul</option>
				<option value="RO">Rondônia</option>
				<option value="RR">Roraima</option>
				<option value="SC">Santa Catarina</option>
				<option value="SP">São Paulo</option>
				<option value="SE">Sergipe</option>
				<option value="TO">Tocantins</option>
			</select>
		</div>
		
		<div class="form-group">
			<label for="principal">Salvar como endereço principal de entrega</label> <input type="checkbox"
				 id="principal" name="principal">
		</div>
		
		<div class="form-group">
			<label for="cobranca">Salvar como endereço de cobrança</label> <input type="checkbox"
				 id="cobranca" name="cobranca">
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='SALVAR'
				class="btn btn-primary" />
    
	<%  }%>
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