package com.myhomeinfo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhomeinfo.entidades.Produto;
import com.myhomeinfo.jdbc.ProdutoDAO;
import com.myhomeinfo.jdbc.UsuarioComumDAO;

@WebServlet("/prodcontroller.do")
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProdutoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		ProdutoDAO proDAO = new ProdutoDAO();
		if((acao != null) && (acao.equals("exc"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Produto pro = new Produto(codigo);
			proDAO.remover(pro);
			response.sendRedirect("prodcontroller.do?acao=lst");
		}else if((acao != null) && (acao.equals("edt"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Produto pro = proDAO.buscar(codigo);
			request.setAttribute("produto", pro);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmproduto.jsp");
			saida.forward(request, response);
		}else if((acao != null) && (acao.equals("cad"))){
			UsuarioComumDAO usu = new UsuarioComumDAO();
			Calendar cal = Calendar.getInstance();
			Produto prod = new Produto(usu.codAtual("produto"), cal);
			request.setAttribute("produto", prod);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmproduto.jsp");
			saida.forward(request, response);
		}else if((acao != null) && (acao.equals("lst"))){
			List<Produto> lst = proDAO.buscarTodos();
			request.setAttribute("lista", lst);
			RequestDispatcher saida = request.getRequestDispatcher("pages/listaprodutos.jsp");
			saida.forward(request, response); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("txtcodigo");
		String nome = request.getParameter("txtnome");
		String marca = request.getParameter("txtmarca");
		String dtUltimaCompra = request.getParameter("txtdataultimacompra");
		String unidadeCompra = request.getParameter("txtunidadecompra");
		String unidadeTransmissao = request.getParameter("txtunidadetransmissao");
		String descricaoUso = request.getParameter("txtdescricaouso");
		
		Double quantidadeAtual = 0.0;
		try{
			quantidadeAtual = (Double.parseDouble(request.getParameter("txtquantidadeatual")));
		}catch(Exception e){
			e.printStackTrace();
		}
		Double quantidadeRecomendada = 0.0;
		try{
			quantidadeRecomendada = (Double.parseDouble(request.getParameter("txtquantidaderecomendada")));
		}catch(Exception e){
			e.printStackTrace();
		}
		Double quantidadeMinima = 0.0;
		try{
			quantidadeMinima = (Double.parseDouble(request.getParameter("txtquantidademinima")));
		}catch(Exception e){
			e.printStackTrace();
		}
		String codigoBarras = request.getParameter("txtcodigobarras");
		Double valorMedioCompra = 0.0;
		try{
			valorMedioCompra = (Double.parseDouble(request.getParameter("txtvalormedio")));
		}catch(Exception e){
			e.printStackTrace();
		}		
		String dst = request.getParameter("cbbdesativado");

		boolean desativado = false;
		if((dst != null) && (dst.equals("Sim")))
			desativado = true;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataUltimaCompra = Calendar.getInstance();
		try{
			dataUltimaCompra.setTime(sdf.parse(dtUltimaCompra));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		int cod = 0;
		if((codigo != null) && (codigo != "0") && (codigo != ""))
			cod = Integer.parseInt(codigo);

		Produto rep = new Produto(0, cod, nome, marca, dataUltimaCompra, unidadeCompra, unidadeTransmissao, descricaoUso, quantidadeAtual, quantidadeRecomendada, quantidadeMinima, codigoBarras, valorMedioCompra, desativado);
		ProdutoDAO repDAO = new ProdutoDAO();
		repDAO.salvar(rep);
		response.sendRedirect("prodcontroller.do?acao=lst");
	}

}
