<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<link rel="icon" href="img/favicon/fav_16.png" sizes="16x16">
<link rel="icon" href="img/favicon/fav_32.png" sizes="32x32">
<link rel="icon" href="img/favicon/fav_48.png" sizes="48x48">
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
              	<li class="dropdown-header">Usuários</li>
				<li><a href="usucontroller.do?acao=cad">Cadastrar</a></li>
				<li><a href="usucontroller.do?acao=lst">Listar</a></li>

                <li class="divider"></li>

              	<li class="dropdown-header">Fornecedores</li>
				<li><a href="forncontroller.do?acao=cad">Cadastrar</a></li>
				<li><a href="forncontroller.do?acao=lst">Listar</a></li>

                <li class="divider"></li>

              	<li class="dropdown-header">Produtos</li>
				<li><a href="prodcontroller.do?acao=cad">Cadastrar</a></li>
				<li><a href="prodcontroller.do?acao=lst">Listar</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Movimentação <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">Entrada</li>
				<li><a href="entcontroller.do?acao=cad">Gerar entrada</a></li>
				<li><a href="entcontroller.do?acao=lst">Histórico de entradas</a></li>
				<li class="divider"></li>
				<li class="dropdown-header">Saída</li>
				<li><a href="saicontroller.do?acao=cad">Gerar saída</a></li>
				<li><a href="saicontroller.do?acao=lst">Histórico de saídas</a></li>
                <li class="divider"></li>
                <li><a href="estcontroller.do?acao=lst">Gerenciar estoque</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administração <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
              	<li class="dropdown-header">Cadastros</li>
				<li><a href="adtusuariocontroller.do">Usuários Alterados</a></li>
				<li><a href="#">Fornecedores Alterados</a></li>
				<li><a href="#">Produtos Alterados</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Movimentações</li>
                <li><a href="#">Entradas Alteradas</a></li>
                <li><a href="#">Saídas Alteradas</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Ajuda <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
				<li><a href="#">Tópicos de ajuda</a></li>
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
		        <!--<button type="submit" class="btn btn-danger-mn">Buscar</button> -->
		        	<button type="button" class="btn btn-danger-mn" onclick="alert('Desculpe, \n\nA função de busca ainda não foi implementada nessa versão :(');">Buscar</button>
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
	        	Desenvolvido para a matéria de Oficina de Integração 
	        </p>
	        <p>
	        	Alunos: Alex, Paulo, Rodrigo, Thiago
	        </p>
	        <p class="centro">
	        	<small>© Star Company - Setembro de 2014</small>
	        </p>
	      </div>
	      <div class="modal-footer">
	        <a href="http://www.site.com" target="_blank"><button type="button" class="btn btn-default" data-dismiss="modal">Visitar site</button></a>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>      