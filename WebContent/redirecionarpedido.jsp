<%@page import="br.edu.fatec.celular.dominio.Administrador"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>

<%

	if (request.getAttribute("resultado") != null) {
		out.println(
			"<input type = 'hidden' id = 'resultado' value = " + request.getAttribute("resultado") + ">");
	}

	response.sendRedirect("./Pedido?operacao=CONSULTAR&pagina=alterarpedido.jsp&confirmado=false");	
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