<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>BuckStar - Login</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<script src="javascript/jquery.min.js"> </script>
	<script src="javascript/bootstrap.js"></script>
	<link rel="icon" href="img/favicon/fav_16.png" sizes="16x16">
	<link rel="icon" href="img/favicon/fav_32.png" sizes="32x32">
	<link rel="icon" href="img/favicon/fav_48.png" sizes="48x48">	
</head>
<body class="principal">
	
	<div>
		<img src="img/logo/logo.png" class="img_login" alt="Logo - BuckStar">
	</div>
	<div class="container">
		<form action="logincontroller.do" method="post" class="form-login">
			<input type="text" name="txtlogin" value="" size="5" placeholder="digite o login" required="required" class="form-control" autofocus />
			<input type="password" name="txtsenha" value="" maxlength="16" size="5" placeholder="digite a senha" required="required" autocomplete="off" class="form-control" />
			<div>
				<input type="checkbox" id="lembrar" name="cbblembrar" class="css-checkbox" id="cbblembrar" />
				<label for="lembrar" class="css-label">Lembrar usuário</label>
			</div>
			<div>
				<input type="submit" id="submit" value="Entrar" class="btn btn-primary salvar btn-block" />
			</div>
		</form>
			${requestScope.inf}	 
	</div>
</body>
</html>