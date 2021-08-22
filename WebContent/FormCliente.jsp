
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ include file="/Componentes/EstruturaInicio.jsp"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>

</head>
<body>
	<%
		if (request.getAttribute("resultado") != null) {
			out.println("<div id = 'resultado'>");
			out.println(request.getAttribute("resultado") + "</div>");
		}
	%>
	<%@ include file="/Componentes/Cabecalho.jsp"%>
	
	<form action="Cliente" method="post" style = "margin-left: 5%; margin-right: 5%;">
		
		<%
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			if(cliente != null){ //se existir será alteração
  		%>
		
		<div class="form-group">
			<label for="txtEmail">E-mail</label> <input type="email"
				class="form-control" id="txtEmail" name="txtEmail"
				placeholder="nome@exemplo.com" 
				value="<%=cliente.getEmail()%>">
		</div>

		<div class="form-group">
			<label for="txtSenha">Senha</label> <input type="password"
				class="form-control" id="txtSenha" name="txtSenha"
				placeholder="Digite uma senha">
		</div>

		<div class="form-group">
			<label for="txtConfirmarSenha">Confirmar Senha</label> <input
				type="password" class="form-control" id="txtConfirmarSenha"
				name="txtConfirmarSenha" placeholder="Digite a senha novamente">
		</div>

		<div class="form-group">
			<label for="txtCpf">CPF</label> <input type="text"
				class="form-control" id="txtCpf" name="txtCpf"
				value="<%=cliente.getCpf()%>">
		</div>

		<div class="form-group">
			<label for="txtNome">Nome Completo</label> <input type="text"
				class="form-control" id="txtNome" name="txtNome"
				value="<%=cliente.getNome()%>">
		</div>

		<div class="form-group">
			<label for="txtDataNascimento">Data de Nascimento</label> <input
				type="date" class="form-control" id="txtDataNascimento"
				name="txtDataNascimento"
				value="<%=cliente.getDataNascimento()%>">
		</div>

		<div class="form-group">
			<label for="txtSexo">Sexo</label> <select class="form-control"
				id="txtSexo" name="txtSexo">
				<option value="M">Masculino</option>
				<option value="F">Feminino</option>
				<option value="I">Indefinido</option>
				<option selected value="<%=cliente.getSexo()%>"><%=cliente.getSexo()%></option>
			</select>
		</div>

		<div class="form-group">
			<label for="txtTelefone">Telefone</label> <input type="text"
				class="form-control" id="txtTelefone" name="txtTelefone"
				placeholder="(00)0000-0000">
		</div>

		<div class="form-group">
			<label for="txtCelular">Celular</label> <input type="text"
				class="form-control" id="txtCelular" name="txtCelular"
				placeholder="(00)00000-0000">
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='ALTERAR'
				class="btn btn-primary" />
		</div>		
		
		<%  }
		    
			else{
		%>
		    
					
		<div class="form-group">
			<label for="txtEmail">E-mail</label> <input type="email"
				class="form-control" id="txtEmail" name="txtEmail"
				placeholder="nome@exemplo.com">
		</div>

		<div class="form-group">
			<label for="txtSenha">Senha</label> <input type="password"
				class="form-control" id="txtSenha" name="txtSenha"
				placeholder="Digite uma senha">
		</div>

		<div class="form-group">
			<label for="txtConfirmarSenha">Confirmar Senha</label> <input
				type="password" class="form-control" id="txtConfirmarSenha"
				name="txtConfirmarSenha" placeholder="Digite a senha novamente">
		</div>

		<div class="form-group">
			<label for="txtCpf">CPF</label> <input type="text"
				class="form-control" id="txtCpf" name="txtCpf">
		</div>

		<div class="form-group">
			<label for="txtNome">Nome Completo</label> <input type="text"
				class="form-control" id="txtNome" name="txtNome">
		</div>

		<div class="form-group">
			<label for="txtDataNascimento">Data de Nascimento</label> <input
				type="date" class="form-control" id="txtDataNascimento"
				name="txtDataNascimento">
		</div>

		<div class="form-group">
			<label for="txtSexo">Sexo</label> <select class="form-control"
				id="txtSexo" name="txtSexo">
				<option value="M">Masculino</option>
				<option value="F">Feminino</option>
				<option value="I">Indefinido</option>
			</select>
		</div>

		<div class="form-group">
			<label for="txtTelefone">Telefone</label> <input type="text"
				class="form-control" id="txtTelefone" name="txtTelefone"
				placeholder="(00)0000-0000">
		</div>

		<div class="form-group">
			<label for="txtCelular">Celular</label> <input type="text"
				class="form-control" id="txtCelular" name="txtCelular"
				placeholder="(00)00000-0000">
		</div>
		
		<div class="form-group" align="center">
			<input type='submit' id='operacao' name='operacao' value='SALVAR'
				class="btn btn-primary" />
		</div>		
		
		<%	
			}
  	    %>
		
	</form>

	<script type="text/javascript">
		$("#resultado").show();
		setTimeout(function() {
			$("#resultado").hide();
		}, 5000);

		$(document).ready(function() {
			
			$("#txtTelefone").mask("(00)0000-0000");
			$("#txtCelular").mask("(00)00000-0000");
			$("#txtCpf").mask("000.000.000-00", {
				reverse : true
			});
		});
	</script>
</body>
</html>