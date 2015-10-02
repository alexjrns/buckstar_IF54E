package com.myhomeinfo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.myhomeinfo.entidades.Fornecedor;

public class FornecedorDAO {
	private UsuarioComumDAO usuDAO = new UsuarioComumDAO();
	
	public FornecedorDAO(){
	}

	@Override
	public String toString() {
		return "FornecedorDAO [con=" + con + "]";
	}

	/**
	 * Cadastra no banco de dados um representante.
	 * @param representante Objeto com todos os dados a serem inseridos no banco.
	 * @return Verdadeiro ser for inserido com sucesso e False caso não consiga realizar a inserção.
	 */
	public boolean cadastrar(Fornecedor fornecedor){
			String razao = fornecedor.getRazaoSocial();
			String cnpj = fornecedor.getCNPJ();
			String fantasia = fornecedor.getNomeFantasia();
			String inscricao = fornecedor.getInscricaoEstadual();
			String logradouro = fornecedor.getLogradouro();
			String numero = fornecedor.getNumero();
			String complemento = fornecedor.getComplemento();
			String cep = fornecedor.getCEP();
			String cidade = fornecedor.getCidade();
			String estado = fornecedor.getEstado();
			String fone = fornecedor.getTelefone();
			String email = fornecedor.geteMail();
			String site = fornecedor.getSite();
			java.sql.Date dta = new java.sql.Date(fornecedor.getDataDeCadastro().getTimeInMillis());
			String data = String.valueOf(dta);
			String desativado = (fornecedor.isDesativado() ? "Sim": "Nao");
			
			String[][] vet = {{"id_fornecedor", ""}, {"cod_fornecedor", ""}, {"des_razaosocial", razao}, {"val_cnpj", cnpj} , {"des_nomefantasia", fantasia}, {"val_ie", inscricao}, {"end_logradouro", logradouro}, {"end_numero", numero}, {"end_complemento", complemento}, {"end_cep", cep}, {"end_cidade", cidade}, {"end_estado", estado}, {"val_telefone", fone}, {"val_email", email}, {"val_site", site}, {"dat_cadastro", data}, {"opt_desativado", desativado} }; 
			return usuDAO.cadastrar("fornecedor", vet);
	}
	
	/**
	 * Cadastra ou altera no banco de dados um representante, caso o representante esteja com um código não existente no banco ele alterará.
	 * @param representante Objeto com todos os dados a serem inseridos no banco.
	 * @return Verdadeiro ser for inserido/cadastrado com sucesso e False caso não consiga realizar a inserção/atualização.
	 */	
	public boolean salvar(Fornecedor fornecedor){
		int codg = usuDAO.codAtual("fornecedor");		
		if((fornecedor.getCodigo() != 0) && (fornecedor.getCodigo() < codg))
			return alterar(fornecedor);
		else
			return cadastrar(fornecedor);
	}

	public boolean alterar(Fornecedor fornecedor){
			String codigo = String.valueOf(fornecedor.getCodigo());
			String razao = fornecedor.getRazaoSocial();
			String cnpj = fornecedor.getCNPJ();
			String fantasia =  fornecedor.getNomeFantasia();
			String inscricao =  fornecedor.getInscricaoEstadual();
			String logradouro =  fornecedor.getLogradouro();
			String numero =  fornecedor.getNumero();
			String complemento =  fornecedor.getComplemento();
			String cep =  fornecedor.getCEP();
			String cidade =  fornecedor.getCidade();
			String estado =  fornecedor.getEstado();
			String fone =  fornecedor.getTelefone();
			String email =  fornecedor.geteMail();
			String site = fornecedor.getSite();			
			java.sql.Date dta = new java.sql.Date(fornecedor.getDataDeCadastro().getTimeInMillis());
			String data = String.valueOf(dta);
			String desativado = (fornecedor.isDesativado() ? "Sim": "Nao");
			
			String[][] vet = { {"cod_fornecedor", codigo}, {"des_razaosocial", razao}, {"val_cnpj", cnpj} , {"des_nomefantasia", fantasia}, {"val_ie", inscricao}, {"end_logradouro", logradouro}, {"end_numero", numero}, {"end_complemento", complemento}, {"end_cep", cep}, {"end_cidade", cidade}, {"end_estado", estado}, {"val_telefone", fone}, {"val_email", email}, {"val_site", site}, {"dat_cadastro", data}, {"opt_desativado", desativado} }; 
			return usuDAO.alterar("fornecedor", vet);
	}
	
	/**
	 * Remove do banco de dados um usuário.
	 * @param usuario Objeto com todos os dados, ou apenas o código a ser removido do banco.
	 * @return Verdadeiro ser for removido com sucesso e False caso não consiga remover.
	 */
	public boolean remover(Fornecedor fornecedor){
		return (usuDAO.remover("fornecedor", "fornecedor.cod_fornecedor = " + fornecedor.getCodigo()))? true: false;
	}
	
	public Fornecedor buscarCh(int chave){
		return usuDAO.buscarFornecedorCh(chave);
	}	
	
	public List<Fornecedor> buscarTodos(){
		return usuDAO.buscarTodosFornecedor();
	}
	
	public Fornecedor buscar(int id){
		return usuDAO.buscarFornecedor(id);
	}

	public List<Fornecedor> buscar(String nome){
		return usuDAO.buscarTodosFornecedor(nome);
	}
}
