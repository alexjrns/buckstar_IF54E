package com.myhomeinfo.jdbc;

import com.myhomeinfo.entidades.Entrada;
import com.myhomeinfo.entidades.Excecoes;
import com.myhomeinfo.entidades.Saida;

public class SaidaDAO {
	private UsuarioComumDAO usuDAO = new UsuarioComumDAO();
	
	public SaidaDAO() {
		super();
	}
	
	public boolean salvar(Saida saida){
		int codg = usuDAO.codAtual("saida");		
		if((saida.getId() != 0) && (saida.getId() < codg))
			//return alterar(saida);
			return false;
		else
			return cadastrar(saida);
	}

	public boolean cadastrar(Saida saida){
		String codigo = "";
		try{
			codigo = String.valueOf(saida.getId());
		}catch(Exception e){
			Excecoes.erro(e);
		}
		java.sql.Date dtaSaida = new java.sql.Date(saida.getData().getTimeInMillis());
		String dataSaida = "";
		try{
			dataSaida = String.valueOf(dtaSaida);
		}catch(Exception e){
			Excecoes.erro(e);
		}
		java.sql.Time hraSaida = new java.sql.Time(saida.getHora().getTimeInMillis());
		String horaSaida = "";
		try{
			horaSaida = String.valueOf(hraSaida);
		}catch(Exception e){
			Excecoes.erro(e);
		}

		String codEntrada = "";
		try{
			codEntrada = String.valueOf(saida.getEntrada().getId());
		}catch(Exception e){
			Excecoes.erro(e);
		}
		String codUsuario = "";
		try{
			codUsuario = String.valueOf(saida.getUsuario().getCodigo());
		}catch(Exception e){
			Excecoes.erro(e);
		}

		String[][] vet = { {"id_entrada", ""}, {"cod_entrada", codigo}, {"dat_entrada", dataEntrada}, {"tim_entrada", horaEntrada},
				{"val_numeronfe", numeroNF}, {"val_precopago", valor}, {"fk_fornecedor", codFornecedor}, {"fk_usuario", codUsuario} }; 

		usuDAO.abreTransacao();
		if(usuDAO.cadastrar("saida", vet)){
			try{
				assimilarProduto(saida.getProdutos(), codigo);
			} catch (Exception e){
				Excecoes.erro(e);
				return usuDAO.rollback();
			}
			return usuDAO.commit();
		}else{
			return usuDAO.rollback();
		}
	}
}
