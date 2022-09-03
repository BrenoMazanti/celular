<%@page import="br.edu.fatec.celular.dominio.Cliente"%>
<% 
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			if (cliente == null) {
				response.sendRedirect("telalogin.jsp");
			}
%>  	