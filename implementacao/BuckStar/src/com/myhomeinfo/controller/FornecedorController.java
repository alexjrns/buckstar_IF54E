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

import com.myhomeinfo.entidades.Fornecedor;
import com.myhomeinfo.jdbc.FornecedorDAO;
import com.myhomeinfo.jdbc.UsuarioComumDAO;

@WebServlet(name = "forncontroller.do", urlPatterns = { "/forncontroller.do" })
public class FornecedorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FornecedorController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		FornecedorDAO fornDAO = new FornecedorDAO();
		if((acao != null) && (acao.equals("exc"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Fornecedor forn = new Fornecedor(codigo);
			fornDAO.remover(forn);	
			response.sendRedirect("forncontroller.do?acao=lst");
		}else if((acao != null) && (acao.equals("edt"))){
			int codigo = Integer.parseInt(request.getParameter("cdg"));
			Fornecedor forn = fornDAO.buscar(codigo);
			request.setAttribute("fornecedor", forn);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmfornecedor.jsp");
			saida.forward(request, response);
		}else if((acao != null) && (acao.equals("cad"))){
			UsuarioComumDAO usu = new UsuarioComumDAO();
			Calendar cal = Calendar.getInstance();
			Fornecedor forn = new Fornecedor(usu.codAtual("fornecedor"), cal);
			request.setAttribute("fornecedor", forn);
			RequestDispatcher saida = request.getRequestDispatcher("pages/frmfornecedor.jsp");
			saida.forward(request, response);
		}else if((acao != null) && (acao.equals("lst"))){
			List<Fornecedor> lst = fornDAO.buscarTodos();
			request.setAttribute("lista", lst);
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

		Fornecedor rep = new Fornecedor(0, cod, razao, cnpj, fantasia, inscricao, logradouro, numero, complemento, cep, cidade, estado, telefone, email, site, dataCadastro, desativado);
		FornecedorDAO repDAO = new FornecedorDAO();
		repDAO.salvar(rep);
		response.sendRedirect("forncontroller.do?acao=lst");
	}

}
