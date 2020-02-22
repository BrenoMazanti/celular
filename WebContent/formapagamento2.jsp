<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ include file="qtdecartoes.jsp"%>
<body>
	<div class="row" align="center">
		<div class="col-8">
			<div class="row">
				<%
					for (int j = 0; j < 3; j++) {
				%>
				<div class="col-3">
					<div class="card" align="center" style="width: 18rem;">
						<div class="card-body">
							<h5 class="card-title">Meu Cartão Visa</h5>
							<p class="card-text">****1505</p>
							<a href="formapagamento.jsp" class="btn btn-primary">Selecionar</a>
							<a href="#" class="btn btn-primary">Editar</a>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<div class="col-3">
					<div class="row">
						<div class="col-12">
							<a href="FormCartao.jsp" class="btn btn-primary" style="margin: 5px;">Novo+</a>
						</div>
					</div>
					<div class="row">
						<div class="col-12" style = "margin: 5px;">
							<a href="FormCartao.jsp" class="btn btn-primary" style="">Usar Outro Cartão</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-4">
			<div class="row">
				<div class="col-4">Cupom</div>
				<div class="col-4">
					<input type="text"></input>
				</div>
				<div class="col-4">
					<a href="#" class="btn btn-primary">Adicionar</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>