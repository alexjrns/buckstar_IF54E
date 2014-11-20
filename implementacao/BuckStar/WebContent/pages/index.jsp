<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>BuckStar</title>
	<link rel="stylesheet" type="text/css" href="css/alex.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<script src="javascript/jvsp.js"> </script>
	<script src="javascript/bootstrap.js"> </script>
</head>

<body onload="mostrar();">
	<c:import url="../includes/menu.jsp"></c:import>
	<c:import url="../includes/loading.jsp"></c:import>	
	<div class="container">
		<div id="crp" class="carousel slide" data-interval="3000" data-ride="carousel">
		  <ol class="carousel-indicators">
		    <li data-target="#crp" data-slide-to="0" class="active"></li>
			<li data-target="#crp" data-slide-to="1"></li>
		    <li data-target="#crp" data-slide-to="2"></li>
		  </ol>

		  <div class="carousel-inner">
		    <div class="item active">
				<img src="img/logotipo.png" alt="Tecno Contol" class="img_carrousel">
				<div class="carousel-caption">
					<h3>Sistema para controle de compras para minimercados</h3>
					<p>Seja bem vindo!</p>
				</div>
		    </div>
		    <div class="item">
		      <img src="img/estoque.png" alt="Estoque" class="img_carrousel">
		      <div class="carousel-caption">
					<h3>Gerencie seu <b>estoque</b></h3>
					<p>Gerencie seu estoque de forma simples e efetiva, comece agora mesmo clicando no menu acima "Movimentação".</p>
		      </div>
		    </div>
		    <div class="item">
		      <img src="img/valor.png" alt="Valores" class="img_carrousel">
		      <div class="carousel-caption">
					<h3>Tenha uma base histórica dos valores pagos em seus <b>Produtos</b></h3>
					<p>Com o BuckStar você consegue saber qual o histórico de valores pagos para cada determinado produto.</p>
		      </div>
		    </div>		    
		  </div>

		  <a class="left carousel-control" href="#crp" role="button" data-slide="prev">
		    <span class="glyphicon glyphicon-chevron-left"></span>
		  </a>
		  <a class="right carousel-control" href="#crp" role="button" data-slide="next">
		    <span class="glyphicon glyphicon-chevron-right"></span>
		  </a>
		</div>			
	 </div>
</body>
</html>