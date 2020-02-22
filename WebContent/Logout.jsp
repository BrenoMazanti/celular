<%@page import="br.edu.fatec.celular.dominio.Administrador"%>
<%@page import="br.edu.fatec.celular.dominio.Cliente"%>

<%
	Administrador adm = (Administrador) session.getAttribute("adm");
	if (adm != null) {
		session.invalidate();
		response.sendRedirect("admLogin.jsp");	
	}
	
	else{
	Cliente cliente = (Cliente) session.getAttribute("cliente");
	if (cliente != null) {
		session.invalidate();
		response.sendRedirect("telainicial.jsp");
	}
	}
%>