<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/alex.css">
	<link rel="stylesheet" type="text/css" href="css/menu-responsivo.css">
	<script src="javascript/jvsp.js"> </script>
	<title>Produtos cadastrados</title>
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
		    	<h3 class="panel-title">Lista de produtos cadastrados</h3>
		  	</div>
		  	<div class="panel-body">
				<div class="table-responsive tabela">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr class="titulo">
								<th>Código</th>	<th>Nome</th>	<th>Marca</th>	<th>Data da última compra</th> <th>Unidade de compra</th> <th>Unidade de transmissão</th>
								<th>Descrição de uso</th> <th>Quantidade atual</th> <th>Quantidade recomendada</th> <th>Quantidade mínima</th> <th>Código de barras</th> <th>Valor médio de compra</th> 
								<th>Desativado</th> <th>Código do último fornecedor</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.lista}" var="u"> 
							<tr>
								<td>${u.codigo}</td>
								<td>${u.nome}</td>
								<td>${u.marca}</td>
								<td>${u.getDataFormatada()}</td>								
								<td>${u.unidadeCompra}</td>
								<td>${u.unidadeTransmissao}</td>
								<td>${u.descricaoUso}</td>
								<td>${u.quantidadeAtual}</td>
								<td>${u.quantidadeRecomendada}</td>
								<td>${u.quantidadeMinima}</td>
								<td>${u.codigoBarras}</td>
								<td>${u.valorMedioCompra}</td>
								<td> <c:out value="${u.desativado ? 'Sim' : 'Não'}" /> </td>
								<td>${u.ultimoFornecedor}</td>
								<td>
									<a href="prodcontroller.do?acao=edt&cdg=${u.codigo}"><button type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-file"></span></button></a>
									<a href="javascript:confirmaExclusao(${u.codigo}, 'prodcontroller.do?acao=exc&cdg=')" ><button type="button" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span></button></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>