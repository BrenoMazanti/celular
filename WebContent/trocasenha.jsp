<%@ include file="Componentes/EstruturaInicio.jsp"%>
</head>
<body>
	<%@ include file="Componentes/Cabecalho.jsp"%>

	<%
		if (request.getAttribute("resultado") != null) {
			out.println(
					"<input type = 'hidden' id = 'resultado' value = " + request.getAttribute("resultado") + ">");
		}
	%>

	<div align="center">
		<form action="Cliente" method="post">
			<input type="hidden" name="operacao" value="LOGIN">
			<table style="margin: 5%; align-self: center;">
				<tr>
					<td style="margin: 20px;"><label for="txtSenha">Senha Atual:</label></td>
					<td><input id="txtSenha" type="password" name="txtSenha"
						style="width: 200px" required></td>
				</tr>
				<tr>
					<td style="margin: 20px;"><label for="txtSenha">Nova Senha:</label></td>
					<td><input id="txtSenha" type="password" name="txtSenha"
						style="width: 200px" required></td>
				</tr>
				<tr>
					<td style="margin: 20px;"><label for="txtConfirmarSenha">Confirmar Nova Senha:</label></td>
					<td><input id="txtConfirmarSenha" type="password" name="txtConfirmarSenha"
						style="width: 200px" required></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="telainicial.jsp" class="btn btn-primary">Alterar Senha</a></td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			/*var resultado = document.getElementById("resultado").value;
			if(resultado != null)
				alert(resultado);*/
			if ($("#resultado").val() != null)
				alert($("#resultado").val());
		});
	</script>
</body>
</html>