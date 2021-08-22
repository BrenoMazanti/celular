<%@ include file="Componentes/EstruturaMate.jsp"%>
</head>
<body>
	<%@ include file="Componentes/admCabecalho.jsp"%>
	<div class="col-3" style="">
		<a href="telainicial.jsp"><label
			style="font-size: 26pt; font-family: Times New Roman; margin: 10%">Bem-vindo, 
			<%out.println(adm.getEmail());%></label></a>
	</div>
</body>
</html>