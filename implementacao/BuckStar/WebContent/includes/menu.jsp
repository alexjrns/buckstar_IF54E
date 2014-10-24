<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
<link rel="stylesheet" href="css/menu.css" type="text/css"/>

<script type="text/javascript" src="javascript/jquery.min.js"></script>
<script type="text/javascript" src="javascript/bootstrap.js"></script>

    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <div class="img-menu">
          		<div class="img-int">
          			<a href="pages/index.jsp"></a>
          		</div>
          </div>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastros <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
              	<li class="dropdown-header">Usu�rios</li>
				<li><a href="usucontroller.do?acao=cad">Cadastrar</a></li>
				<li><a href="usucontroller.do?acao=lst">Listar</a></li>

                <li class="divider"></li>

              	<li class="dropdown-header">Fornecedores</li>
				<li><a href="forncontroller.do?acao=cad">Cadastrar</a></li>
				<li><a href="forncontroller.do?acao=lst">Listar</a></li>

                <li class="divider"></li>
              	
              	<li class="dropdown-header">Produtos</li>
				<li><a href="vencontroller.do?acao=cad">Cadastrar</a></li>
				<li><a href="vencontroller.do?acao=lst">Listar</a></li>

                <li class="divider"></li>
                
                <li class="dropdown-header">Clientes</li>
				<li><a href="pages/index.jsp">Cadastrar</a></li>
				<li><a href="clicontroller.do?acao=lst">Listar</a></li>

              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Movimenta��o <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
				<li><a href="#">Gerar entrada</a></li>
				<li><a href="#">Gerar sa�da</a></li>
                <li class="divider"></li>
                <li><a href="#">Ajustar estoque</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Relat�rios <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
				<li><a href="#">Usu�rios</a></li>
				<li><a href="#">Representantes</a></li>
				<li><a href="#">Clientes</a></li>
                <li class="divider"></li>
                <li><a href="#">Propostas Comerciais</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Ajuda <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
				<li><a href="#">T�picos de ajuda</a></li>
                <li class="divider"></li>
                <li><a href="#" data-toggle="modal" data-target="#myModal">Sobre</a></li>

              </ul>
            </li>

          </ul>

          <ul class="nav navbar-nav navbar-right">
	        <li>
	        	      <form class="navbar-form navbar-right target pesquisar" role="search" action="#">
				        <div class="form-group">
				          <input type="search" class="form-control" placeholder="O que deseja buscar?">
				        </div>
				        <button type="submit" class="btn btn-danger-mn">Buscar</button>
				      </form>
	        </li>	
			<li class="active"><a href="logincontroller.do">Sair</a></li>	
          </ul>
        </div>
      </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Fechar</span></button>
	        <h4 class="modal-title" id="myModalLabel">BuckStar</h4>
	      </div>
	      <div class="modal-body">
	        <h4>
	        	Sistema para controle de compras e estoque para minimercados
	        </h4>
	        <p>
	        	Desenvolvido para a mat�ria de Oficina de Integra��o 
	        </p>
	        <p>
	        	Alunos: Alex, Paulo, Rodrigo, Thiago
	        </p>
	        <p class="centro">
	        	<small>� Star Company - Setembro de 2014</small>
	        </p>
	      </div>
	      <div class="modal-footer">
	        <a href="http://www.google.com" target="_blank"><button type="button" class="btn btn-default" data-dismiss="modal">Visitar site</button></a>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button></a>
	      </div>
	    </div>
	  </div>
	</div>      