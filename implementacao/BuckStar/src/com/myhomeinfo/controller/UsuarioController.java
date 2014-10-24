package com.myhomeinfo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhomeinfo.entidades.Usuario;
import com.myhomeinfo.jdbc.UsuarioComumDAO;
import com.myhomeinfo.jdbc.UsuarioDAO;

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UsuarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		if((acao != null) && (acao.equals("exc"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Usuario usu = new Usuario(codigo);
			usuDAO.remover(usu);
			//Redirecionando ao cliente(browser)
			response.sendRedirect("usucontroller.do?acao=lst");
			//acao="lst";
		}else if((acao != null) && (acao.equals("edt"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Usuario usu = usuDAO.buscar(codigo);
			request.setAttribute("usuario", usu);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmusuario.jsp");
			saida.forward(request, response);
			//acao="lst";
		}else if((acao != null) && (acao.equals("cad"))){
			UsuarioComumDAO usuPer = new UsuarioComumDAO();
			Usuario usu = new Usuario(usuPer.codAtual("usuario"), "", "", "");
			request.setAttribute("usuario", usu);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmusuario.jsp");
			saida.forward(request, response);
			//acao="lst";
		}else if((acao != null) && (acao.equals("lst"))){
			List<Usuario> lst = usuDAO.buscarTodos();
			request.setAttribute("lista", lst); //Container dentro do request
			
			/*Encaminhamento para o JSP*/
			RequestDispatcher saida = request.getRequestDispatcher("pages/listausuarios.jsp"); //Objeto de encaminhamento
			saida.forward(request, response); //encaminha o resquest e o response para o JSP
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//super.doPost(request, response);
		/*Recebe dados da Tela*/
		String codigo = request.getParameter("txtcodigo");
		String nome = request.getParameter("txtnome");
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");

		int cod = 0;
		if((codigo != null) && (codigo != "0") && (codigo != ""))
			cod = Integer.parseInt(codigo);

		/*Cria um usuário com os valores da Tela*/
		Usuario usu = new Usuario(cod, nome, login, senha);
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		response.sendRedirect("usucontroller.do?acao=lst");
	}
}
