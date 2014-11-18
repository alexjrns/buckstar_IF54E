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
	<title>Entradas lançadas</title>
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>

	<div class="container">	
		<div class="panel panel-default">
			<div class="panel-heading">
		    	<h3 class="panel-title">Lista de entradas</h3>
		  	</div>
		  	<div class="panel-body">
				<div class="table-responsive tabela">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr class="titulo">
								<th>Código</th><th>Data da entrada</th><th>Hora da entrada</th><th>Numero NF</th><th>Valor total da NF</th><th>Ação</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.lista}" var="u">
							<tr>
								<td>${u.id}</td>
								<td>${u.getDataEntradaFormatada()}</td>
								<td>${u.getHoraEntradaFormatada()}</td>
								<td>${u.numeroNF}</td>
								<td>${u.valor}</td>
								<td>
									<script>var cod = ${u.id}</script>
									<a href="entcontroller.do?acao=edt&cdg=${u.id}"><button type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-file"></span></button></a>
									<a href="javascript:confirmaExclusao(${u.id}, 'entcontroller.do?acao=exc&cdg=')" ><button type="button" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span></button></a>
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