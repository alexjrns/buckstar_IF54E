<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="css/alex.css">
<link rel="stylesheet" type="text/css" href="css/menu-responsivo.css">
<script src="javascript/jvsp.js"> </script>
<script src="javascript/jquery.min.js"> </script>

<title>Usuários cadastrados</title>
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>

	<div class="container">	
		<div class="panel panel-default">
			<div class="panel-heading">
		    	<h3 class="panel-title">Lista de usuários</h3>
		  	</div>
		  	<div class="panel-body">
				<div class="table-responsive tabela">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr class="titulo">
								<th>Código</th><th>Nome</th><th>Login</th><th>Senha</th><th>Ação</th>
							</tr>
						</thead>
						<c:forEach items="${requestScope.lista}" var="u">
							<tr>
								<td>${u.codigo}</td>
								<td>${u.nome}</td>
								<td>${u.login}</td>
								<td>******</td>
								<%//<td>${u.senha}</td>%>
								<td>
									<script>var cod = ${u.codigo}</script>
									<a href="usucontroller.do?acao=edt&cdg=${u.codigo}"><button type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-file"></span></button></a>
									<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-remove"></span></button>
									<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									  <div class="modal-dialog">
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Fechar</span></button>
									        <h4 class="modal-title" id="myModalLabel">Confirmação</h4>
									      </div>
									      <div class="modal-body">
									        Tem certeza que deseja excuir o registro com o código ${u.codigo}?
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
									        <a href="usucontroller.do?acao=exc&cdg=${u.codigo}" ><button type="button" class="btn btn-primary">Sim</button></a>
									      </div>
									    </div>
									  </div>
									</div>
									<!--  <a href="javascript:confirmaExclusao(${u.codigo}, 'usucontroller.do?acao=exc&cdg=')" ><button type="button" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span></button></a>-->
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