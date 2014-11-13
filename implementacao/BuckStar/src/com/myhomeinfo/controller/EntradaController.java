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
import com.myhomeinfo.entidades.Fornecedor;
import com.myhomeinfo.entidades.Produto;
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
		
		EntradaDAO entDAO = new EntradaDAO();
		if((acao != null) && (acao.equals("exc"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Entrada ent = new Entrada(codigo);
			//entDAO.remover(ent);	
			response.sendRedirect("entcontroller.do?acao=lst");
		}else if((acao != null) && (acao.equals("edt"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			//Entrada ent = entDAO.buscar(codigo);
			//request.setAttribute("fornecedor", ent);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmentrada.jsp");
			saida.forward(request, response);
		}else if((acao != null) && (acao.equals("cad"))){
			UsuarioComumDAO usu = new UsuarioComumDAO();
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
			//List<Fornecedor> lst = entDAO.buscarTodos();
			//request.setAttribute("lista", lst);
			RequestDispatcher saida = request.getRequestDispatcher("pages/listafornecedores.jsp");
			saida.forward(request, response); 
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("txtcodigo");
		String razao = request.getParameter("txtrazao");
		String cnpj = request.getParameter("txtcnpj");
		String fantasia = request.getParameter("txtfantasia");
		String inscricao = request.getParameter("txtinscricao");
		String logradouro = request.getParameter("txtlogradouro");
		String numero = request.getParameter("txtnumero");
		String complemento = request.getParameter("txtcomplemento");			
		String cep = request.getParameter("txtcep");
		cep = cep.replace("-", "");
		String cidade = request.getParameter("txtcidade");			
		String estado = request.getParameter("txtestado");
		String telefone = request.getParameter("txttelefone");
		String email = request.getParameter("txtemail");
		String site = request.getParameter("txtsite");
		String dtCadastro = request.getParameter("txtdatacadastro");
		String dst = request.getParameter("cbbdesativado");
		
		boolean desativado = false;
		if((dst != null) && (dst.equals("Sim")))
			desativado = true;	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCadastro = Calendar.getInstance();
		try{
			dataCadastro.setTime(sdf.parse(dtCadastro));
		}catch(Exception e){
			e.printStackTrace();
		}
		int cod = 0;
		if((codigo != null) && (codigo != "0") && (codigo != ""))
			cod = Integer.parseInt(codigo);

		//Entrada ent = new Entrada(codigo, razao, cnpj, fantasia, inscricao, logradouro, numero, complemento, cep, cidade, estado, telefone, email, site, dataCadastro, desativado);
		EntradaDAO entDAO = new EntradaDAO();
		//entDAO.salvar(ent);
		response.sendRedirect("entcontroller.do?acao=lst");		
		
	}

}
