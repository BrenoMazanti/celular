<%@ include file="Componentes/EstruturaInicio.jsp"%>
</head>
<body>
	<div class="row">
		<div class="col-3" style="">
			<img src="./Imagens/cell.png" style="height: 150px; width: 150px;"><label
				style="font-size: 26pt; font-family: Magneto;">Celulares</label>
		</div>
	</div>

	<%
		if (request.getAttribute("resultado") != null) {
			out.println(
					"<input type = 'hidden' id = 'resultado' value = " + request.getAttribute("resultado") + ">");
		}
	%>

	<div align="center">
		<form action="Administrador" method="post">
			<input type="hidden" name="operacao" value="LOGIN">
			<table style="margin: 5%;">
				<tr>
					<td><label for="txtEmail">Login:</label></td>
					<td><input id="txtEmail" type="email" name="txtEmail"
						style="width: 200px" required></td>
				</tr>
				<tr>
					<td><label for="txtSenha">Senha:</label></td>
					<td><input id="txtSenha" type="password" name="txtSenha"
						style="width: 200px" required></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn btn-primary"
						value="Entrar" onclick></td>
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