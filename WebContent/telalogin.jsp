<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ include file="Menu.jsp" %>
<body>
		<div align="center">
			<table>
				<tr>
					<td>
						<label for="txtEmail">Login:</label>
					</td>
					<td>
						<input id="txtEmail" type="email" name="txtEmail">
					</td>
				</tr>
				<tr>
					<td>
						<label for="txtEmail">Senha:</label>
					</td>
					<td>
						<input id="txtSenha" type="password" name="txtSenha">
					</td>
				</tr>
				<tr>
					<td>
						<a type = "button" href="Cliente?operacao=LOGIN">Entrar</a>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>