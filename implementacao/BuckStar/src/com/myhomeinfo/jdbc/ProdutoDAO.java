package com.myhomeinfo.jdbc;

import java.util.List;

import com.myhomeinfo.entidades.Produto;

public class ProdutoDAO {
	private UsuarioComumDAO usuDAO = new UsuarioComumDAO();
	
	public ProdutoDAO() {
		super();
	}
	
	public boolean cadastrar(Produto produto){
		String nome = produto.getNome();
		String marca = produto.getMarca();
		java.sql.Date dta = new java.sql.Date(produto.getDataUltimaCompra().getTimeInMillis());
		String dataUltimaCompra = String.valueOf(dta);
		String unidadeCompra = produto.getUnidadeCompra();
		String unidadeTransmissao = produto.getUnidadeTransmissao();
		String descricaoUso = produto.getDescricaoUso();
		String quantidadeAtual = String.valueOf(produto.getQuantidadeAtual());
		String quantidadeRecomendada = String.valueOf(produto.getQuantidadeRecomendada());
		String quantidadeMinima = String.valueOf(produto.getQuantidadeRecomendada());
		String codigoBarras = produto.getCodigoBarras();
		String valorMedioCompra = String.valueOf(produto.getValorMedioCompra());
		String desativado = (produto.isDesativado() ? "Sim": "Nao");

		String[][] vet = { {"id_produto", ""}, {"cod_produto", ""}, {"des_nome", nome}, {"des_marca", marca}, {"dat_ultimacompra", dataUltimaCompra}, {"des_unidadecompra", unidadeCompra},
				{"des_unidadetransmissao", unidadeTransmissao}, {"des_descricaouso", descricaoUso}, {"val_quantidadeatual", quantidadeAtual}, {"val_quantidaderecomendada", quantidadeRecomendada},
				{"val_quantidademinima", quantidadeMinima}, {"val_codigobarras", codigoBarras}, {"val_valormediocompra", valorMedioCompra}, {"opt_desativado", desativado} }; 
		UsuarioComumDAO dao = new UsuarioComumDAO();

		return dao.cadastrar("produto", vet);
	}
	
	public boolean alterar(Produto produto){
		String codigo = String.valueOf(produto.getCodigo()); 
		String nome = produto.getNome();
		String marca = produto.getMarca();
		java.sql.Date dta = new java.sql.Date(produto.getDataUltimaCompra().getTimeInMillis());
		String dataUltimaCompra = String.valueOf(dta);
		String unidadeCompra = produto.getUnidadeCompra();
		String unidadeTransmissao = produto.getUnidadeTransmissao();
		String descricaoUso = produto.getDescricaoUso();
		String quantidadeAtual = String.valueOf(produto.getQuantidadeAtual());
		String quantidadeRecomendada = String.valueOf(produto.getQuantidadeRecomendada());
		String quantidadeMinima = String.valueOf(produto.getQuantidadeRecomendada());
		String codigoBarras = produto.getCodigoBarras();
		String valorMedioCompra = String.valueOf(produto.getValorMedioCompra());
		String desativado = (produto.isDesativado() ? "Sim": "Nao");

		String[][] vet = { {"cod_produto", codigo}, {"des_nome", nome}, {"des_marca", marca}, {"dat_ultimacompra", dataUltimaCompra}, {"des_unidadecompra", unidadeCompra},
				{"des_unidadetransmissao", unidadeTransmissao}, {"des_descricaouso", descricaoUso}, {"val_quantidadeatual", quantidadeAtual}, {"val_quantidaderecomendada", quantidadeRecomendada},
				{"val_quantidademinima", quantidadeMinima}, {"val_codigobarras", codigoBarras}, {"val_valormediocompra", valorMedioCompra}, {"opt_desativado", desativado} }; 
		return usuDAO.alterar("produto", vet);
	}
	
	public boolean remover(Produto produto){
		return (usuDAO.remover("produto", "produto.cod_produto = " + produto.getCodigo()))? true: false;
	}
	
	public boolean salvar(Produto produto){
		int codg = usuDAO.codAtual("produto");		
		if((produto.getCodigo() != 0) && (produto.getCodigo() < codg))
			return alterar(produto);
		else
			return cadastrar(produto);
	}
	
	public List<Produto> buscarTodos(){
		return usuDAO.buscarTodosProdutos();
	}

	public List<Produto> buscarTodosFalta(){
		return usuDAO.buscarTodosProdutosFalta();
	}

	public List<Produto> buscarTodosAbaixo(){
		return usuDAO.buscarTodosProdutosAbaixo();
	}	

	public List<Produto> buscarTodosNormais(){
		return usuDAO.buscarTodosProdutosNormais();
	}	
		
	public Produto buscar(int id){
		return usuDAO.buscarProduto(id);
	}
}
