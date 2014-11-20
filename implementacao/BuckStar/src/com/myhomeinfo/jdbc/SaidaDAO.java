package com.myhomeinfo.jdbc;

import java.util.List;

import com.myhomeinfo.entidades.Excecoes;
import com.myhomeinfo.entidades.ProdutoSaida;
import com.myhomeinfo.entidades.Saida;

public class SaidaDAO {
	private UsuarioComumDAO usuDAO = new UsuarioComumDAO();
	
	public SaidaDAO() {
		super();
	}
	
	public boolean salvar(Saida saida){
		int codg = usuDAO.codAtual("saida");		
		if((saida.getId() != 0) && (saida.getId() < codg))
			return alterar(saida);
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

		String valSaida = "";
		try{
			valSaida = String.valueOf(saida.getValorSaida());
		}catch(Exception e){
			Excecoes.erro(e);
		}
		
		String codEntrada = "";
		try{
			codEntrada = String.valueOf(saida.getEntrada().getChave());
		}catch(Exception e){
			Excecoes.erro(e);
		}
		String codUsuario = "";
		try{
			codUsuario = String.valueOf(saida.getUsuario().getCodigo());
		}catch(Exception e){
			Excecoes.erro(e);
		}

		String[][] vet = { {"id_saida", ""}, {"cod_saida", codigo}, {"dat_saida", dataSaida}, {"tim_saida", horaSaida},
				{"val_saida", valSaida}, {"fk_entrada", codEntrada}, {"fk_usuario", codUsuario} }; 

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
	
	private boolean assimilarProduto(ProdutoSaida[] prods, String codSaida) throws Exception{
		boolean result = false;
		ProdutoSaida[] prod = prods;

		String [][] campos = new String[5][2];
		for(int i = 0; i < prod.length; i++){
			campos[0][0] = "id_produtossaida";
			campos[0][1] = "";
			campos[1][0] = "cod_produtossaida";
			campos[1][1] = String.valueOf(prods[i].getCodigo());
			campos[2][0] = "fk_produto";
			campos[2][1] = String.valueOf(prods[i].getCodProduto());
			campos[3][0] = "fk_saida";
			campos[3][1] = codSaida;
			campos[4][0] = "val_quantidade";
			campos[4][1] = String.valueOf(prods[i].getQuantidade());
			result = usuDAO.cadastrar("produtossaida", campos);
		}
		return result;
	}

	public List<Saida> buscarTodos(){
		return usuDAO.buscarTodosSaida();
	}

	public Saida buscar(int codigo){
		return usuDAO.buscarSaida(codigo);
	}

	public boolean alterar(Saida saida){
		return false;
	}

	public boolean remover(Saida saida){
		return false;
	}
}
