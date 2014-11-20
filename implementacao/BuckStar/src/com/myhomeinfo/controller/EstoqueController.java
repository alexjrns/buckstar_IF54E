package com.myhomeinfo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhomeinfo.entidades.Produto;
import com.myhomeinfo.jdbc.ProdutoDAO;

@WebServlet("/estcontroller.do")
public class EstoqueController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EstoqueController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProdutoDAO proDAO = new ProdutoDAO();
			List<Produto> lstFalta = proDAO.buscarTodosFalta();
			request.setAttribute("listaFalta", lstFalta);
			List<Produto> lstAbaixo = proDAO.buscarTodosAbaixo();
			request.setAttribute("listaAbaixo", lstAbaixo);			
			List<Produto> lstNormais = proDAO.buscarTodosNormais();
			request.setAttribute("listaNormais", lstNormais);
			RequestDispatcher saida = request.getRequestDispatcher("pages/listaestoque.jsp");
			saida.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
