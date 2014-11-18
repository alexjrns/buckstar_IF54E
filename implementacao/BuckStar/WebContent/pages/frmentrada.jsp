<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Entrada de produtos</title>
	<link rel="stylesheet" type="text/css" href="css/alex.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>	
	<script type="text/javascript" src="javascript/jvsp.js"></script>
	<script>
		$(function(){
			$('#txtfornecedor').find('[value="' + '${requestScope.entrada.getFornecedor().chave}' + '"]').attr('selected', true);
			console.log('${requestScope.entrada.fornecedor.getCodigo()}');
			console.log('${requestScope.entrada.fornecedor.chave}');
			console.log('${requestScope.entrada.fornecedor.razaoSocial}');
		});
	</script>	
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>

	<div class="container">
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Entrada de produtos</h3>
		  </div>
		  <div class="panel-body">
			<div class="form-group">
				<form action="entcontroller.do" method="post" class="padrao">
					<fieldset>
						<label for="txtcodigo">Código</label>							
						<input type="text" name="txtcodigo" id="txtcodigo" readonly="readonly" value="${requestScope.entrada.id}" required="required" />
						<label for="txtdataentrada">Data da entrada*</label>
						<input type="datetime" name="txtdataentrada" id="txtdataentrada" value="${requestScope.entrada.getDataEntradaFormatada()}"  data-mask="##/##/####" />
						<label for="txthoraentrada">Hora da entrada*</label>
						<input type="text" name="txthoraentrada" id="txthoraentrada" value="${requestScope.entrada.getHoraEntradaFormatada()}"  data-mask="##:##" />
						<label for="txtnota">Número da nota fiscal*</label>
						<input type="text" name="txtnota" id="txtnota" value="${requestScope.entrada.numeroNF}" required="required" />
						<label for="txtvalor">Valor da nota fiscal</label>
						<div class="input-group">
  							<span class="input-group-addon">R$</span>
  							<input type="text" name="txtvalor" id="txtvalor" class="monetario" value="${requestScope.entrada.valor}" />
						</div>
						<label for="txtfornecedor">Fornecedor</label>
						<select id="txtfornecedor" name="txtfornecedor">
								<c:forEach items="${requestScope.listaFor}" var="u">
								<option value="${u.chave}">${u.razaoSocial}</option>
							</c:forEach>
						</select>
						<div id="produtos" class="produtos">
							<div id="item1" class="itemprodutos">
								<label for="txtprodutos" class="linha">Produto</label>
								<select id="txtprodutos" name="txtprodutos">
									<c:forEach items="${requestScope.listaPro}" var="p">
										<option value="${p.chave}">${p.nome}</option>
									</c:forEach>
								</select>
								<label for="quantidade" class="linha">Quantidade</label>
								<input type="number" name="quantidade" id="quantidade" class="quantidade" min="1" max="9999" />
								<label for="valor" class="linha">Valor</label>
								<input type="text" name="valor" id="valor" class="quantidade" value="" />
								<button type="button" class="btn btn-primary" onclick="adicionaProduto(this);">+</button>							
							</div>
						</div>
						
						<script src="javascript/jquery.mask.js"> </script>
					</fieldset>						
					<label class="tt">* Campos obrigatórios</label>					
					<div class="salvar">					
						<input type="submit" id="submit" value="Gerar lançamento de entrada" class="btn btn-primary salvar" />	
					</div>
				</form>
			</div>
		  </div>
		</div>
	</div>
</body>
</html>