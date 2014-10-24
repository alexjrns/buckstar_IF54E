package com.myhomeinfo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myhomeinfo.entidades.Usuario;
import com.myhomeinfo.jdbc.UsuarioDAO;

@WebServlet("/logincontroller.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		if(sessao != null)
			sessao.invalidate();
		response.sendRedirect("login.jsp");
		//request.getRequestDispatcher("login.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");
		//String lembrarUsuario = request.getParameter("cbblembrar");

		Usuario usr = new Usuario(login, senha);
		UsuarioDAO usrDAO = new UsuarioDAO();
		if (usrDAO.autenticar(usr)){
			HttpSession sessao = request.getSession();
			sessao.setMaxInactiveInterval(3000);
			sessao.setAttribute("usuarioLogado", usr);

			String pagDestino = request.getParameter("pagina");
			if(pagDestino != null)
				request.getRequestDispatcher(pagDestino).forward(request, response);
			else
				request.getRequestDispatcher("pages/index.jsp").forward(request, response);
		}else{

			String info = ("<p><div class=\"msg_erro alert alert-danger fade in\" role=\"alert\">" +
					"<button class=\"close\" data-dismiss=\"alert\" type=\"button\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Fechar</span></button>" +
					"<h4>Usuário e/ou senha incorretos!</h4>" +
					"<p>Verifique o usuário, a senha e digite os dados novamente.</p>" +
					"</div></p>");
			request.setAttribute("inf", info);

			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
