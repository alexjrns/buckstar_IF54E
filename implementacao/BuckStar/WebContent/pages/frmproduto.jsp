<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cadastro de Produtos</title>
	<link rel="stylesheet" type="text/css" href="css/alex.css">
	
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="http://code.jquery.com/jvsp.js"></script>
	<script>
		$(function(){
			$('#txtultimofornecedor').find('[value="' + '${requestScope.produto.ultimoFornecedor}' + '"]').attr('selected', true);
		});
	</script>
</head>
<body>
	<c:import url="../includes/menu.jsp"></c:import>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
		    	<h3 class="panel-title">Cadastro de produtos</h3>
		  	</div>
		  	<div class="panel-body">	
				<form action="prodcontroller.do" method="post" class="padrao">
					<fieldset>
						<label for="txtcodigo">Código</label>
						<input type="text" name="txtcodigo" id="txtcodigo" readonly="readonly" value="${requestScope.produto.codigo}" required="required" />
						<label for="txtnome">Nome*</label>
						<input type="text" name="txtnome" id="txtnome" value="${requestScope.produto.nome}" required="required" />
						<label for="txtmarca">Marca</label>
						<input type="text" name="txtmarca" id="txtmarca" value="${requestScope.produto.marca}" />						
						<label for="txtdataultimacompra">Data da última compra</label>
						<input type="datetime" name="txtdataultimacompra" id="txtdataultimacompra" value="${requestScope.produto.getDataFormatada()}"  data-mask="##/##/####" />						
						<label for="txtunidadecompra">Unidade de compra</label>
						<input type="text" name="txtunidadecompra" id="txtunidadecompra" value="${requestScope.produto.unidadeCompra}" />	
						<label for="txtunidadetransmissao">Unidade de transmissão</label>
						<input type="text" name="txtunidadetransmissao" id="txtunidadetransmissao" value="${requestScope.produto.unidadeTransmissao}" />	
						<label for="txtdescricaouso">Descrição de uso</label>
						<textarea name="txtdescricaouso" id="txtdescricaouso" rows="3">${requestScope.produto.descricaoUso}</textarea>				
						<label for="txtquantidadeatual">Quantidade atual</label>
						<input type="text" name="txtquantidadeatual" id="txtquantidadeatual" value="${requestScope.produto.quantidadeAtual}" />	
						<label for="txtquantidaderecomendada">Quantidade recomendada</label>
						<input type="text" name="txtquantidaderecomendada" id="txtquantidaderecomendada" value="${requestScope.produto.quantidadeRecomendada}" />	
						<label for="txtquantidademinima">Quantidade mínima</label>
						<input type="text" name="txtquantidademinima" id="txtquantidademinima" value="${requestScope.produto.quantidadeMinima}" />	
						<label for="txtcodigobarras">Código de barras</label>
						<input type="text" name="txtcodigobarras" id="txtcodigobarras" value="${requestScope.produto.codigoBarras}" />	
						<label for="txtvalormedio">Valor médio de compra</label>
						<div class="input-group">
  							<span class="input-group-addon">R$</span>
  							<input type="text" name="txtvalormedio" id="txtvalormedio" class="monetario" value="${requestScope.produto.valorMedioCompra}" />
						</div>
						<label for="txtultimofornecedor">Último fornecedor</label>
						<!--<input type="text" name="txtultimofornecedor" id="txtultimofornecedor" value="${requestScope.produto.ultimoFornecedor}" /> -->
						<select id="txtultimofornecedor" name="txtultimofornecedor">
							<c:forEach items="${requestScope.listaFor}" var="u">
								<option value="${u.chave}">${u.razaoSocial}</option>
							</c:forEach>
						</select>
						<input type="checkbox" id="desativado" name="cbbdesativado" ${requestScope.produto.isDesativado()?'checked':''} class="css-checkbox" value="Sim" />
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