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
<title>Fornecedores cadastrados</title>
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
		    	<h3 class="panel-title">Lista de fornecedores cadastrados</h3>
		  	</div>
		  	<div class="panel-body">
				<div class="table-responsive tabela">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr class="titulo">
								<th>Código</th>	<th>Razão Social</th>	<th>CNPJ</th>	<th>Nome Fantasia</th> <th>Inscrição Estadual</th> <th>Logradouro</th>
								<th>Numero</th> <th>Complemento</th> <th>CEP</th> <th>Cidade</th> <th>Estado</th> <th>Telefone</th> <th>E-mail</th> <th>Site</th> <th>Data de Cadastro</th>
								<th>Desativado?</th> <th>Ação</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.lista}" var="u"> 
							<tr>
								<td>${u.codigo}</td>
								<td>${u.razaoSocial}</td>
								<td>${u.CNPJ}</td>
								<td>${u.nomeFantasia}</td>
								<td>${u.inscricaoEstadual}</td>
								<td>${u.logradouro}</td>
								<td>${u.numero}</td>
								<td>${u.complemento}</td>
								<td>${u.CEP}</td>
								<td>${u.cidade}</td>
								<td>${u.estado}</td>
								<td>${u.telefone}</td>
								<td>${u.eMail}</td>
								<td>${u.site}</td>
								<td>${u.getDataFormatada()}</td>
								<td> <c:out value="${u.desativado ? 'Sim' : 'Não'}" /> </td>
								<td>
									<a href="forncontroller.do?acao=edt&cdg=${u.codigo}"><button type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-file"></span></button></a>
									<a href="javascript:confirmaExclusao(${u.codigo}, 'forncontroller.do?acao=exc&cdg=')" ><button type="button" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span></button></a>
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