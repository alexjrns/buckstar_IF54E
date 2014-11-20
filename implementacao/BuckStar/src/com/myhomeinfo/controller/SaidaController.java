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

import com.myhomeinfo.entidades.Entrada;
import com.myhomeinfo.entidades.Excecoes;
import com.myhomeinfo.entidades.Produto;
import com.myhomeinfo.entidades.ProdutoSaida;
import com.myhomeinfo.entidades.Saida;
import com.myhomeinfo.entidades.Usuario;
import com.myhomeinfo.jdbc.EntradaDAO;
import com.myhomeinfo.jdbc.SaidaDAO;
import com.myhomeinfo.jdbc.UsuarioComumDAO;

@WebServlet("/saicontroller.do")
public class SaidaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SaidaController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		UsuarioComumDAO usu = new UsuarioComumDAO();
		SaidaDAO saiDAO = new SaidaDAO();
		if((acao != null) && (acao.equals("exc"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Saida sai = new Saida(codigo);
			saiDAO.remover(sai);	
			response.sendRedirect("entcontroller.do?acao=lst");
		}else if((acao != null) && (acao.equals("edt"))){
			List<Produto> prods = usu.buscarTodosProdutos();
			request.setAttribute("listaPro", prods);
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Saida sai = saiDAO.buscar(codigo);
			request.setAttribute("saida", sai);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmsaida.jsp");
			saida.forward(request, response);
		}else if((acao != null) && (acao.equals("cad"))){
			List<Produto> prods = usu.buscarTodosProdutos();
			request.setAttribute("listaPro", prods);
			EntradaDAO entDao = new EntradaDAO();
			List<Entrada> entr = entDao.buscarTodos();
			request.setAttribute("listaEnt", entr);
			Calendar cal = Calendar.getInstance();
			Saida sai = new Saida(usu.codAtual("saida"), cal, cal);
			request.setAttribute("saida", sai);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmsaida.jsp");
			saida.forward(request, response);
		}else if((acao != null) && (acao.equals("lst"))){
			List<Produto> prods = usu.buscarTodosProdutos();
			request.setAttribute("listaPro", prods);			
			List<Saida> lst = saiDAO.buscarTodos();
			request.setAttribute("lista", lst);
			RequestDispatcher saida = request.getRequestDispatcher("pages/listasaidas.jsp");
			saida.forward(request, response); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  codigo = request.getParameter("txtcodigo");
		String dtSaida = request.getParameter("txtdatasaida");
		String hrSaida= request.getParameter("txthorasaida");
		String vlSaida = request.getParameter("txtvalor");
		vlSaida = vlSaida.replace(",", ".");
		Double valorSaida = 0.0;
		try{
			valorSaida = Double.parseDouble(vlSaida);
		}catch(Exception e){
			Excecoes.erro(e);
		}		
				
		String codEntrada = request.getParameter("txtentrada");
		String cdgProd = request.getParameter("txtprodutos");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataSaida = Calendar.getInstance();
		try{
			dataSaida.setTime(sdf.parse(dtSaida));
		}catch(Exception e){
			Excecoes.erro(e);
		}

		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		Calendar horaSaida = Calendar.getInstance();
		try{
			horaSaida.setTime(shf.parse(hrSaida));
		}catch(Exception e){
			Excecoes.erro(e);
		}
		
		Usuario usr = new Usuario(1);

		int codEnt = 0;
		if((codEntrada != null) && (codEntrada != "0") && (codEntrada != ""))
			codEnt = Integer.parseInt(codEntrada);
		
		int codProduto = 0;
		if((cdgProd != null) && (cdgProd != "0") && (cdgProd != ""))
			codProduto = Integer.parseInt(cdgProd);		
		
		int cod = 0;
		if((codigo != null) && (codigo != "0") && (codigo != ""))
			cod = Integer.parseInt(codigo);		

		Saida saida = new Saida(cod);
		Produto prd = new Produto(codProduto);
		Double quantidade = Double.parseDouble(request.getParameter("quantidade"));
		//Double valor = Double.parseDouble(request.getParameter("valor"));
		
		ProdutoSaida prodSaida = new ProdutoSaida(codProduto, saida, prd, quantidade);
		prodSaida.setProduto(prd);
		prodSaida.setSaida(saida);
		ProdutoSaida[] prods = new ProdutoSaida[1];
		prods[0] = prodSaida;		
		
		EntradaDAO entDAO = new EntradaDAO();
		Entrada ent = entDAO.buscar(codEnt);

		Saida sai = new Saida(cod, dataSaida, horaSaida, valorSaida, ent, prods, usr);
		SaidaDAO saiDAO = new SaidaDAO();
		saiDAO.salvar(sai);
		response.sendRedirect("saicontroller.do?acao=lst");		
	}

}
