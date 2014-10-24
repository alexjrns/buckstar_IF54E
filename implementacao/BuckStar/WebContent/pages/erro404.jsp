<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Endereço não encontrado!</title>
<link rel="stylesheet" href="css/erro.css" type="text/css"/>
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>
	<div class="box">
        <div id="fundo" class="fundo_principal">
			<img src="img/logo/logo.png" alt="Buck Star">
		</div>	
		<h1>Desculpe, mas esta página não foi encontrada!</h1> 
		<h2>Ela pode ter sido removida ou modificada.</h2>
		<h3>
			Tente acessar a página principal do site <a href="http://buckstar.com.br/"><b>aqui</b></a>
        </h3>
		<p class="inner-box">Se o problema persistir, contate o desenvolvedor do site.</p>
        <p class="error">
			Hospedado por <a href="http://www.buckstar.com.br/" target="_blank"><strong>© Star Company</strong></a><br>
		</p>
	</div>
</body>
</html>