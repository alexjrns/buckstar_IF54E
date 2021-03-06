<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/alex.css">
	<link rel="stylesheet" type="text/css" href="css/menu-responsivo.css">
	<script src="javascript/jvsp.js"> </script>	
	<title>Auditoria de Usu�rios</title>
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>
	
	<div class="container">	
		<div class="panel panel-default">
			<div class="panel-heading">
		    	<h3 class="panel-title">Usu�rios alterados <span class="glyphicon glyphicon-wrench"></span></h3>
		  	</div>
		  	<div class="panel-body">
				<div class="table-responsive tabela">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr class="titulo">
								<th>C�digo</th><th>Nome</th><th>Login</th><th>Tipo de altera��o</th><th>Data da altera��o</th><th>Hora da altera��o</th><th>Usu�rio da altera��o</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.auditorias}" var="u">
							<tr>
								<td>${u.getUsuario().codigo}</td>
								<td>${u.getUsuario().nome}</td>
								<td>${u.getUsuario().login}</td>
								<td> <c:out value="${u.getAuditoria().tipoAlteracao.equals('DEL') ? 'Exclus�o' : 'Edi��o'}" /> </td>
								<td>${u.getAuditoria().getDataFormatada()}</td>
								<td>${u.getAuditoria().getHoraFormatada()}</td>
								<td>${u.getAuditoria().usuarioAlteracao}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>