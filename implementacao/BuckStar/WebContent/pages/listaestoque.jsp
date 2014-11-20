<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/alex.css">
	<link rel="stylesheet" type="text/css" href="css/menu-responsivo.css">
	<script src="javascript/jvsp.js"> </script>	
	<title>Gerenciamento de estoque</title>
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>

	<div class="container">	
		<div class="panel panel-danger">
			<div class="panel-heading">
		    	<h3 class="panel-title" data-toggle="tooltip" data-original="Esses produtos estão abaixo do valor mínimo estabelecido">Produtos em falta <span class="glyphicon glyphicon-exclamation-sign"></span></h3>
		  	</div>
		  	<div class="panel-body">
				<div class="table-responsive tabela">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr class="titulo">
								<th>Código</th><th>Nome</th><th>Quantidade atual</th><th>Quantidade recomendada</th><th>Quantidade mínima</th><th>Data da última compra</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.listaFalta}" var="u">
							<tr>
								<td>${u.codigo}</td>
								<td>${u.nome}</td>
								<td>${u.quantidadeAtual} | ${u.unidadeCompra}</td>
								<td>${u.quantidadeRecomendada} | ${u.unidadeCompra}</td>
								<td>${u.quantidadeMinima} | ${u.unidadeCompra}</td>
								<td>${u.getDataFormatada()}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

		<div class="panel panel-warning">
			<div class="panel-heading">
		    	<h3 class="panel-title">Produtos abaixo da média <span class="glyphicon glyphicon-arrow-down"></span></h3>
		  	</div>
		  	<div class="panel-body">
				<div class="table-responsive tabela">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr class="titulo">
								<th>Código</th><th>Nome</th><th>Quantidade atual</th><th>Quantidade recomendada</th><th>Quantidade mínima</th><th>Data da última compra</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.listaAbaixo}" var="u">
							<tr>
								<td>${u.codigo}</td>
								<td>${u.nome}</td>
								<td>${u.quantidadeAtual} | ${u.unidadeCompra}</td>
								<td>${u.quantidadeRecomendada} | ${u.unidadeCompra}</td>
								<td>${u.quantidadeMinima} | ${u.unidadeCompra}</td>
								<td>${u.getDataFormatada()}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

		<div class="panel panel-success">
			<div class="panel-heading">
		    	<h3 class="panel-title">Produtos normais <span class="glyphicon glyphicon-arrow-up"></span></h3>
		  	</div>
		  	<div class="panel-body">
				<div class="table-responsive tabela">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr class="titulo">
								<th>Código</th><th>Nome</th><th>Quantidade atual</th><th>Quantidade recomendada</th><th>Quantidade mínima</th><th>Data da última compra</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.listaNormais}" var="u">
							<tr>
								<td>${u.codigo}</td>
								<td>${u.nome}</td>
								<td>${u.quantidadeAtual} | ${u.unidadeCompra}</td>
								<td>${u.quantidadeRecomendada} | ${u.unidadeCompra}</td>
								<td>${u.quantidadeMinima} | ${u.unidadeCompra}</td>
								<td>${u.getDataFormatada()}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>
</html>