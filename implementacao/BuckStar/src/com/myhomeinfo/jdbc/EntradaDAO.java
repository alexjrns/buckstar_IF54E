package com.myhomeinfo.jdbc;

import com.myhomeinfo.entidades.Entrada;
import com.myhomeinfo.entidades.ProdutoEstoque;

public class EntradaDAO {

	public EntradaDAO() {
		super();
	}

	public boolean cadastrar(Entrada entrada){
		String codigo = String.valueOf(entrada.getId());
		java.sql.Date dtaEntrada = new java.sql.Date(entrada.getDataEntrada().getTimeInMillis());
		String dataEntrada = String.valueOf(dtaEntrada);
		java.sql.Date hraEntrada = new java.sql.Date(entrada.getHoraEntrada().getTimeInMillis());
		String horaEntrada = String.valueOf(hraEntrada);
		String numeroNF = entrada.getNumeroNF();
		String valor = String.valueOf(entrada.getValor());
		String codFornecedor = String.valueOf(entrada.getFornecedor().getCodigo());
		String codUsuario = String.valueOf(entrada.getUsuario().getCodigo());

		String[][] vet = { {"id_produto", ""}, {"cod_produto", codigo}, {"dat_entrada", dataEntrada}, {"tim_entrada", horaEntrada},
				{"val_numeronfe", numeroNF}, {"val_precopago", valor}, {"fk_fornecedor", codFornecedor}, {"fk_usuario", codUsuario} }; 

		UsuarioComumDAO dao = new UsuarioComumDAO();
		dao.abreTransacao();
		if(assimilarProduto(entrada.getProdutos(), codigo)){
			dao.cadastrar("estoque", vet);
			return dao.commit();
		}else{
			return dao.rollback();
		}
	}

	private boolean assimilarProduto(ProdutoEstoque[] prods, String codEntrada){
		boolean result = false;
		ProdutoEstoque[] prod = prods;

		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		String [][] campos = {{}};
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

	
}
