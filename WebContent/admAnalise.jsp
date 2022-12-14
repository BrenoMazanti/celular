<%@ include file="Componentes/EstruturaMate.jsp"%>
</head>
<body>
	<%@ include file="Componentes/admCabecalho.jsp"%>
	<div class="row" style="padding: 10px; margin-left: 5%">
		<div class="col-4">
			<div class="form-group">
			<label for="txtProduto1">Produto 1</label> <input type="text"
				class="form-control" id="txtProduto1" name="txtProduto1"
				placeholder="Digite um produto" value="Samsung Galaxy S10">
			</div>
		</div>
		<div class="col-4">
			<div class="form-group">
			<label for="txtProduto1">Produto 2</label> <input type="text"
				class="form-control" id="txtProduto1" name="txtProduto1"
				placeholder="Digite um produto" value="Iphone X">
			</div>
		</div>
		<div class="col-4">
			<div class="form-group">
			<label for="txtProduto1">Produto 3</label> <input type="text"
				class="form-control" id="txtProduto1" name="txtProduto1"
				placeholder="Digite um produto" value="Iphone 11 Pro Max">
			</div>
		</div>
		<div class="col-5">
			<div class="form-group">
			<label for="txtDataAnalise1">Período - Mês/Ano</label> <input
				type="text" class="form-control" id="txtDataAnalise1"
				name="txtDataAnalise1" value="09/2019">
			</div>
		</div>
		<div class="col-5">
			<div class="form-group">
			<label for="txtDataAnalise2"></label> <input
				type="text" class="form-control" id="txtDataAnalise2"
				name="txtDataAnalise2" value="02/2020">
			</div>
		</div>
		
		<div class="col-2" style="margin-top: 3%">
		<input type='submit' id='operacao' name='operacao' value='Visualizar'
				class="btn btn-primary" />
		</div>
		
		<img src="./Imagens/grafico.png">
	
	<script type="text/javascript">

		$(document).ready(function() {
			
			$("#txtDataAnalise1").mask("00-0000");
			$("#txtDataAnalise2").mask("00-0000");

		});
		
		var options = {
		          series: [
		          {
		            name: "High - 2013",
		            data: [28, 29, 33, 36, 32, 32, 33]
		          },
		          {
		            name: "Low - 2013",
		            data: [12, 11, 14, 18, 17, 13, 13]
		          }
		        ],
		          chart: {
		          height: 350,
		          type: 'line',
		          dropShadow: {
		            enabled: true,
		            color: '#000',
		            top: 18,
		            left: 7,
		            blur: 10,
		            opacity: 0.2
		          },
		          toolbar: {
		            show: false
		          }
		        },
		        colors: ['#77B6EA', '#545454'],
		        dataLabels: {
		          enabled: true,
		        },
		        stroke: {
		          curve: 'smooth'
		        },
		        title: {
		          text: 'Average High & Low Temperature',
		          align: 'left'
		        },
		        grid: {
		          borderColor: '#e7e7e7',
		          row: {
		            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
		            opacity: 0.5
		          },
		        },
		        markers: {
		          size: 1
		        },
		        xaxis: {
		          categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
		          title: {
		            text: 'Month'
		          }
		        },
		        yaxis: {
		          title: {
		            text: 'Temperature'
		          },
		          min: 5,
		          max: 40
		        },
		        legend: {
		          position: 'top',
		          horizontalAlign: 'right',
		          floating: true,
		          offsetY: -25,
		          offsetX: -5
		        }
		        };

		        var chart = new ApexCharts(document.querySelector("#chart"), options);
		        chart.render();
		      
	</script>
</body>
</html>