package com.myhomeinfo.jdbc;

import java.util.List;

import com.myhomeinfo.entidades.Entrada;
import com.myhomeinfo.entidades.Excecoes;
import com.myhomeinfo.entidades.ProdutoEntrada;

public class EntradaDAO {

	public EntradaDAO() {
		super();
	}

	public boolean salvar(Entrada entrada){
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		int codg = usuDAO.codAtual("entrada");		
		if((entrada.getId() != 0) && (entrada.getId() < codg))
			//return alterar(entrada);
			return false;
		else
			return cadastrar(entrada);
	}

	public boolean cadastrar(Entrada entrada){
		String codigo = "";
		try{
			codigo = String.valueOf(entrada.getId());
		}catch(Exception e){
			Excecoes.erro(e);
		}
		java.sql.Date dtaEntrada = new java.sql.Date(entrada.getDataEntrada().getTimeInMillis());
		String dataEntrada = "";
		try{
			dataEntrada = String.valueOf(dtaEntrada);
		}catch(Exception e){
			Excecoes.erro(e);
		}
		java.sql.Time hraEntrada = new java.sql.Time(entrada.getHoraEntrada().getTimeInMillis());
		String horaEntrada = "";
		try{
			horaEntrada = String.valueOf(hraEntrada);
		}catch(Exception e){
			Excecoes.erro(e);
		}
		String numeroNF = entrada.getNumeroNF();
		String valor = ""; 
		try{
			valor = String.valueOf(entrada.getValor());
		}catch(Exception e){
			Excecoes.erro(e);
		}
		String codFornecedor = "";
		try{
			codFornecedor = String.valueOf(entrada.getFornecedor().getCodigo());
		}catch(Exception e){
			Excecoes.erro(e);
		}
		String codUsuario = "";
		try{
			codUsuario = String.valueOf(entrada.getUsuario().getCodigo());
		}catch(Exception e){
			Excecoes.erro(e);
		}

		String[][] vet = { {"id_entrada", ""}, {"cod_entrada", codigo}, {"dat_entrada", dataEntrada}, {"tim_entrada", horaEntrada},
				{"val_numeronfe", numeroNF}, {"val_precopago", valor}, {"fk_fornecedor", codFornecedor}, {"fk_usuario", codUsuario} }; 

		UsuarioComumDAO dao = new UsuarioComumDAO();
		dao.abreTransacao();
		if(dao.cadastrar("entrada", vet)){
			try{
				assimilarProduto(entrada.getProdutos(), codigo);
			} catch (Exception e){
				Excecoes.erro(e);
				return dao.rollback();
			}
			return dao.commit();
		}else{
			return dao.rollback();
		}
	}

	private boolean assimilarProduto(ProdutoEntrada[] prods, String codEntrada) throws Exception{
		boolean result = false;
		ProdutoEntrada[] prod = prods;

		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		String [][] campos = new String[6][2];
		for(int i = 0; i < prod.length; i++){
			campos[0][0] = "id_produtosentrada";
			campos[0][1] = "";
			campos[1][0] = "cod_produtosentrada";
			campos[1][1] = String.valueOf(prods[i].getCodigo());
			campos[2][0] = "fk_produto";
			campos[2][1] = String.valueOf(prods[i].getCodProduto());
			campos[3][0] = "fk_entrada";
			campos[3][1] = codEntrada;
			campos[4][0] = "val_quantidade";
			campos[4][1] = String.valueOf(prods[i].getQuantidade());
			campos[5][0] = "val_precopago";
			campos[5][1] = String.valueOf(prods[i].getPrecoPago());
			result = usuDAO.cadastrar("produtosentrada", campos);
		}
		return result;
	}
	
	public List<Entrada> buscarTodos(){
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		return usuDAO.buscarTodosEntrada();
	}

}
