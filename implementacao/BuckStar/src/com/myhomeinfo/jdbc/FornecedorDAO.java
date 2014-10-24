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
	private Connection con = Conexao.getConnection();

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
			UsuarioComumDAO dao = new UsuarioComumDAO();
			return dao.cadastrar("fornecedor", vet);
	}
	
	/**
	 * Cadastra ou altera no banco de dados um representante, caso o representante esteja com um código não existente no banco ele alterará.
	 * @param representante Objeto com todos os dados a serem inseridos no banco.
	 * @return Verdadeiro ser for inserido/cadastrado com sucesso e False caso não consiga realizar a inserção/atualização.
	 */	
	public boolean salvar(Fornecedor fornecedor){
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
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
			UsuarioComumDAO usuDAO = new UsuarioComumDAO();
			return usuDAO.alterar("fornecedor", vet);
	}
	
	/**
	 * Remove do banco de dados um usuário.
	 * @param usuario Objeto com todos os dados, ou apenas o código a ser removido do banco.
	 * @return Verdadeiro ser for removido com sucesso e False caso não consiga remover.
	 */
	public boolean remover(Fornecedor fornecedor){
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		return (usuDAO.remover("fornecedor", "fornecedor.cod_fornecedor = " + fornecedor.getCodigo()))? true: false;
	}
	
	public List<Fornecedor> buscarTodos(){
		String sql = "SELECT fornecedor.id_fornecedor, fornecedor.cod_fornecedor, fornecedor.des_razaosocial, fornecedor.des_nomefantasia, fornecedor.val_cnpj, fornecedor.val_ie, fornecedor.end_logradouro, fornecedor.end_numero, fornecedor.end_complemento, fornecedor.end_cep, fornecedor.end_cidade, fornecedor.end_estado, fornecedor.val_telefone, fornecedor.val_email, fornecedor.val_site, fornecedor.dat_cadastro, fornecedor.opt_desativado FROM public.fornecedor;";
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Fornecedor forn = new Fornecedor();
				forn.setCodigo(resultado.getInt("cod_fornecedor"));
				forn.setRazaoSocial(resultado.getString("des_razaosocial"));
				forn.setCNPJ(resultado.getString("val_cnpj"));
				forn.setNomeFantasia(resultado.getString("des_nomefantasia"));
				forn.setInscricaoEstadual(resultado.getString("val_ie"));
				forn.setLogradouro(resultado.getString("end_logradouro"));
				forn.setNumero(resultado.getString("end_numero"));
				forn.setComplemento(resultado.getString("end_complemento"));
				forn.setCEP(resultado.getString("end_cep"));
				forn.setCidade(resultado.getString("end_cidade"));
				forn.setEstado(resultado.getString("end_estado"));
				forn.setTelefone(resultado.getString("val_telefone"));
				forn.seteMail(resultado.getString("val_email"));
				forn.setSite(resultado.getString("val_site"));
				java.sql.Date data = new java.sql.Date(resultado.getDate("dat_cadastro").getTime());
				
				java.util.Date dtFinal = new java.util.Date(data.getTime()); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtFinal);
				forn.setDataDeCadastro(cal);

				if(resultado.getString("opt_desativado").equals("Sim"))
					forn.setDesativado(true);	
				else if(resultado.getString("opt_desativado").equals("Nao"))
					forn.setDesativado(false);

				lista.add(forn);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;
	}
	
	public Fornecedor buscar(int id){
		String sql = "SELECT fornecedor.id_fornecedor, fornecedor.cod_fornecedor, fornecedor.des_razaosocial, fornecedor.des_nomefantasia, fornecedor.val_cnpj, fornecedor.val_ie, fornecedor.end_logradouro, fornecedor.end_numero, fornecedor.end_complemento, fornecedor.end_cep, fornecedor.end_cidade, fornecedor.end_estado, fornecedor.val_telefone, fornecedor.val_email, fornecedor.val_site, fornecedor.dat_cadastro, fornecedor.opt_desativado FROM public.fornecedor WHERE (cod_fornecedor = ?);";
		Fornecedor forn = new Fornecedor();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();
			resultado.next();

			forn.setCodigo(resultado.getInt("cod_fornecedor"));
			forn.setRazaoSocial(resultado.getString("des_razaosocial"));
			forn.setCNPJ(resultado.getString("val_cnpj"));
			forn.setNomeFantasia(resultado.getString("des_nomefantasia"));
			forn.setInscricaoEstadual(resultado.getString("val_ie"));
			forn.setLogradouro(resultado.getString("end_logradouro"));
			forn.setNumero(resultado.getString("end_numero"));
			forn.setComplemento(resultado.getString("end_complemento"));
			forn.setCEP(resultado.getString("end_cep"));
			forn.setCidade(resultado.getString("end_cidade"));
			forn.setEstado(resultado.getString("end_estado"));
			forn.setTelefone(resultado.getString("val_telefone"));
			forn.seteMail(resultado.getString("val_email"));
			forn.setSite(resultado.getString("val_site"));			
			java.sql.Date data = new java.sql.Date(resultado.getDate("dat_cadastro").getTime());
			
			java.util.Date dtFinal = new java.util.Date(data.getTime()); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(dtFinal);
			forn.setDataDeCadastro(cal);
			
			if(resultado.getString("opt_desativado").equals("Sim"))
				forn.setDesativado(true);	
			else if(resultado.getString("opt_desativado").equals("Nao"))
				forn.setDesativado(false);

			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return forn;
	}

	public List<Fornecedor> buscar(String nome){
		String sql = "SELECT fornecedor.id_fornecedor, fornecedor.cod_fornecedor, fornecedor.des_razaosocial, fornecedor.des_nomefantasia, fornecedor.val_cnpj, fornecedor.val_ie, fornecedor.end_logradouro, fornecedor.end_numero, fornecedor.end_complemento, fornecedor.end_cep, fornecedor.end_cidade, fornecedor.end_estado, fornecedor.val_telefone, fornecedor.val_email, fornecedor.val_site, fornecedor.dat_cadastro, fornecedor.opt_desativado FROM public.fornecedor WHERE (des_razaosocial LIKE ?);";
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%" + nome + "%");
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Fornecedor forn = new Fornecedor();
				forn.setCodigo(resultado.getInt("cod_fornecedor"));
				forn.setRazaoSocial(resultado.getString("des_razaosocial"));
				forn.setCNPJ(resultado.getString("val_cnpj"));
				forn.setNomeFantasia(resultado.getString("des_nomefantasia"));
				forn.setInscricaoEstadual(resultado.getString("val_ie"));
				forn.setLogradouro(resultado.getString("end_logradouro"));
				forn.setNumero(resultado.getString("end_numero"));
				forn.setComplemento(resultado.getString("end_complemento"));
				forn.setCEP(resultado.getString("end_cep"));
				forn.setCidade(resultado.getString("end_cidade"));
				forn.setEstado(resultado.getString("end_estado"));
				forn.setTelefone(resultado.getString("val_telefone"));
				forn.seteMail(resultado.getString("val_email"));
				forn.setSite(resultado.getString("val_site"));	
				java.sql.Date data = new java.sql.Date(resultado.getDate("dat_cadastro").getTime());
				
				java.util.Date dtFinal = new java.util.Date(data.getTime()); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(dtFinal);
				forn.setDataDeCadastro(cal);

				if(resultado.getString("opt_desativado").equals("Sim"))
					forn.setDesativado(true);	
				else if(resultado.getString("opt_desativado").equals("Nao"))
					forn.setDesativado(false);
				lista.add(forn);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;
	}
}
