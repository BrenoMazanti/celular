<%@page import="br.edu.fatec.celular.dominio.Administrador"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>

<%

	if (request.getAttribute("resultado") != null) {
		out.println(
			"<input type = 'hidden' id = 'resultado' value = " + request.getAttribute("resultado") + ">");
	}

	response.sendRedirect("./Carrinho?operacao=CONSULTAR&pagina=Carrinhoboot.jsp");	
%>
<script type="text/javascript">
		$(document).ready(function() {
			/*var resultado = document.getElementById("resultado").value;
			if(resultado != null)
				alert(resultado);*/
			if ($("#resultado").val() != null)
				alert($("#resultado").val());
		});
</script>