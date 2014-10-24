<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Fornecedores</title>
<link rel="stylesheet" type="text/css" href="css/alex.css">

<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="http://code.jquery.com/jvsp.js"></script>

</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
		    	<h3 class="panel-title">Cadastro de fornecedores</h3>
		  	</div>
		  	<div class="panel-body">	
				<form action="forncontroller.do" method="post" class="padrao">
					<fieldset>
						<label for="txtcodigo">Código</label>
						<input type="text" name="txtcodigo" id="txtcodigo" readonly="readonly" value="${requestScope.fornecedor.codigo}" required="required" />
						<label for="txtrazao">Razão social*</label>
						<input type="text" name="txtrazao" id="txtrazao" value="${requestScope.fornecedor.razaoSocial}" required="required" />
						<label for="txtcnpj">CNPJ*</label>
						<input type="text" name="txtcnpj" id="txtcnpj" value="${requestScope.fornecedor.CNPJ}" required="required" />
						<label for="txtfantasia">Nome fantasia</label>
						<input type="text" name="txtfantasia" id="txtfantasia" value="${requestScope.fornecedor.nomeFantasia}" />
						<label for="txtinscricao">Inscrição estadual</label>
						<input type="text" name="txtinscricao" id="txtinscricao" value="${requestScope.fornecedor.inscricaoEstadual}" />
						<label for="txtlogradouro">Logradouro</label>
						<input type="text" name="txtlogradouro" id="txtlogradouro" value="${requestScope.fornecedor.logradouro}" />
						<label for="txtnumero">Número</label>
						<input type="text" name="txtnumero" id="txtnumero" value="${requestScope.fornecedor.numero}" />
						<label for="txtcomplemento">Complemento</label>
						<input type="text" name="txtcomplemento" id="txtcomplemento" value="${requestScope.fornecedor.complemento}" />			
						<label for="txtcep">CEP</label>
						<input type="text" name="txtcep" id="txtcep" value="${requestScope.fornecedor.CEP}" data-mask="#####-###" pattern="[0-9]{5}-[0-9]{3}" />
						<label for="txtcidade">Cidade</label>
						<input type="text" name="txtcidade" id="txtcidade" value="${requestScope.fornecedor.cidade}" />
						<label for="txtestado">Estado</label>
						<input type="text" name="txtestado" id="txtestado" value="${requestScope.fornecedor.estado}" />
						<label for="txttelefone">Telefone</label>
						<input type="tel" name="txttelefone" id="txttelefone" value="${requestScope.fornecedor.telefone}" data-mask="(##) ####-####" pattern="\([0-9]{2}\)[\s][0-9]{4}-[0-9]{4}" />					
						<label for="txtemail">E-mail</label>
						<input type="email" name="txtemail" id="txtemail" value="${requestScope.fornecedor.eMail}" />
						<label for="txtsite">Site</label>
						<input type="url" name="txtsite" id="txtsite" value="${requestScope.fornecedor.site}" />
						<label for="txtdatacadastro">Data de cadastro</label>
						<input type="datetime" name="txtdatacadastro" id="txtdatacadastro" value="${requestScope.fornecedor.getDataFormatada()}"  data-mask="##/##/####" />
						<input type="checkbox" id="desativado" name="cbbdesativado" ${requestScope.fornecedor.isDesativado()?'checked':''} class="css-checkbox" value="Sim" />
						<label for="desativado" class="css-label combo l">Desativado</label>
						<script src="javascript/jquery.mask.js"> </script>
					</fieldset>		
					<label class="tt">* Campos obrigatórios</label>					
					<input type="submit" id="submit" value="Salvar" class="btn btn-primary salvar" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>