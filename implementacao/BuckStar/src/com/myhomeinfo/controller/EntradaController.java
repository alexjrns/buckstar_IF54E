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
import javax.servlet.http.HttpSession;

import com.myhomeinfo.entidades.Entrada;
import com.myhomeinfo.entidades.Fornecedor;
import com.myhomeinfo.entidades.Produto;
import com.myhomeinfo.entidades.ProdutoEntrada;
import com.myhomeinfo.entidades.Usuario;
import com.myhomeinfo.jdbc.EntradaDAO;
import com.myhomeinfo.jdbc.FornecedorDAO;
import com.myhomeinfo.jdbc.UsuarioComumDAO;

@WebServlet("/entcontroller.do")
public class EntradaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EntradaController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		UsuarioComumDAO usu = new UsuarioComumDAO();
		EntradaDAO entDAO = new EntradaDAO();
		if((acao != null) && (acao.equals("exc"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Entrada ent = new Entrada(codigo);
			entDAO.remover(ent);	
			response.sendRedirect("entcontroller.do?acao=lst");
		}else if((acao != null) && (acao.equals("edt"))){
			List<Produto> prods = usu.buscarTodosProdutos();
			request.setAttribute("listaPro", prods);			
			FornecedorDAO forDao = new FornecedorDAO();
			List<Fornecedor> forn = forDao.buscarTodos();
			request.setAttribute("listaFor", forn);			
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Entrada ent = entDAO.buscar(codigo);
			request.setAttribute("entrada", ent);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmentrada.jsp");
			saida.forward(request, response);
		}else if((acao != null) && (acao.equals("cad"))){
			List<Produto> prods = usu.buscarTodosProdutos();
			request.setAttribute("listaPro", prods);			
			FornecedorDAO forDao = new FornecedorDAO();
			List<Fornecedor> forn = forDao.buscarTodos();
			request.setAttribute("listaFor", forn);
			Calendar cal = Calendar.getInstance();
			Entrada ent = new Entrada(usu.codAtual("entrada"), cal, cal);
			request.setAttribute("entrada", ent);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmentrada.jsp");
			saida.forward(request, response);
		}else if((acao != null) && (acao.equals("lst"))){
			List<Entrada> lst = entDAO.buscarTodos();
			request.setAttribute("lista", lst);
			RequestDispatcher saida = request.getRequestDispatcher("pages/listaentradas.jsp");
			saida.forward(request, response); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  codigo = request.getParameter("txtcodigo");
		String dtEntrada = request.getParameter("txtdataentrada");
		String hrEntrada = request.getParameter("txthoraentrada");
		String numeroNF = request.getParameter("txtnota");
		String vlNF = request.getParameter("txtvalor");
		vlNF = vlNF.replace(",", ".");
		Double valorNF = Double.parseDouble(vlNF);
		String codFornecedor = request.getParameter("txtfornecedor");
		String cdgProd = request.getParameter("txtprodutos");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataEntrada = Calendar.getInstance();
		try{
			dataEntrada.setTime(sdf.parse(dtEntrada));
		}catch(Exception e){
			e.printStackTrace();
		}

		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		Calendar horaEntrada = Calendar.getInstance();
		try{
			horaEntrada.setTime(shf.parse(hrEntrada));
		}catch(Exception e){
			e.printStackTrace();
		}

		int cod = 0;
		if((codigo != null) && (codigo != "0") && (codigo != ""))
			cod = Integer.parseInt(codigo);

		int codProduto = 0;
		if((cdgProd != null) && (cdgProd != "0") && (cdgProd != ""))
			codProduto = Integer.parseInt(cdgProd);

		Entrada entrada = new Entrada(Integer.parseInt(codigo));
		Produto prd = new Produto(codProduto);
		Double quantidade = Double.parseDouble(request.getParameter("quantidade"));
		Double valor = Double.parseDouble(request.getParameter("valor"));

		ProdutoEntrada prodEntrada = new ProdutoEntrada(codProduto, entrada, prd, quantidade, valor);
		prodEntrada.setProd(prd);
		prodEntrada.setEntrada(entrada);
		ProdutoEntrada[] prods = new ProdutoEntrada[1];
		prods[0] = prodEntrada;

		HttpSession sessao = request.getSession();
		Usuario usr = (Usuario) sessao.getAttribute("usuarioLogado");
		
		int codForn = 0;
		if((codFornecedor != null) && (codFornecedor != "0") && (codFornecedor != ""))
			codForn = Integer.parseInt(codFornecedor);

		FornecedorDAO frnDAO = new FornecedorDAO();
		Fornecedor frnd = frnDAO.buscar(codForn);
		Entrada ent = new Entrada(0, cod, dataEntrada, horaEntrada, numeroNF, valorNF, prods, frnd, usr);
		EntradaDAO entDAO = new EntradaDAO();
		entDAO.salvar(ent);
		response.sendRedirect("entcontroller.do?acao=lst");
	}
}
