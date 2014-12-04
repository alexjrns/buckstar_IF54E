package com.homeinfo.auditoria;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adtusuariocontroller.do")
public class AuditoriaUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuditoriaUsuarioDAO adtDAO = new AuditoriaUsuarioDAO();
	
    public AuditoriaUsuarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AuditoriaUsuario> auditorias = adtDAO.buscarTodos();
		request.setAttribute("auditorias", auditorias);			
		RequestDispatcher saida = request.getRequestDispatcher("pages/listausuariosaudit.jsp");
		saida.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
