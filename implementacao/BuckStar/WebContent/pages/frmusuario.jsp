<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuários</title>
<link rel="stylesheet" type="text/css" href="css/alex.css">
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>

	<div class="container">
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Cadastro de usuários</h3>
		  </div>
		  <div class="panel-body">
			<div class="form-group">
				<form action="usucontroller.do" method="post" class="padrao">
						<fieldset>
							<label for="txtcodigo">Código</label>							
							<input type="text" name="txtcodigo" id="txtcodigo" readonly="readonly" value="${requestScope.usuario.codigo}" required="required" />
							<label for="txtnome">Nome*</label>
							<input type="text" name="txtnome" id="txtnome" value="${requestScope.usuario.nome}" placeholder="Nome do usuário" required="required" />
							<label for="txtlogin">Login*</label>
							<input type="text" name="txtlogin" id="txtlogin" value="${requestScope.usuario.login}" placeholder="Login para conexão" required="required" />
							<label for= "txtsenha">Senha*</label>
							<input type="password" name="txtsenha" id="txtsenha" value="" maxlength="12" size="20" placeholder="Senha de 6 a 8 caracteres" required="required" />
						</fieldset>						
					<label class="tt">* Campos obrigatórios</label>					
					<div class="salvar">					
						<input type="submit" id="submit" value="Salvar" class="btn btn-primary salvar" />	
					</div>
				</form>
			</div>
		  </div>
		</div>
	</div>	
</body>
</html>