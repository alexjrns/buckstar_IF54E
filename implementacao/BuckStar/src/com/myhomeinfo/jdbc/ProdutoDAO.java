package com.myhomeinfo.jdbc;

import java.util.List;

import com.myhomeinfo.entidades.Produto;

public class ProdutoDAO {

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
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		return usuDAO.alterar("produto", vet);
	}
	
	public boolean remover(Produto produto){
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		return (usuDAO.remover("produto", "produto.cod_produto = " + produto.getCodigo()))? true: false;
	}
	
	public boolean salvar(Produto produto){
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		int codg = usuDAO.codAtual("produto");		
		if((produto.getCodigo() != 0) && (produto.getCodigo() < codg))
			return alterar(produto);
		else
			return cadastrar(produto);
	}
	
	public List<Produto> buscarTodos(){
		UsuarioComumDAO usu = new UsuarioComumDAO();
		return usu.buscarTodosProdutos();
	}

	public List<Produto> buscarTodosFalta(){
		UsuarioComumDAO usu = new UsuarioComumDAO();
		return usu.buscarTodosProdutosFalta();
	}

	public List<Produto> buscarTodosAbaixo(){
		UsuarioComumDAO usu = new UsuarioComumDAO();
		return usu.buscarTodosProdutosAbaixo();
	}	

	public List<Produto> buscarTodosNormais(){
		UsuarioComumDAO usu = new UsuarioComumDAO();
		return usu.buscarTodosProdutosNormais();
	}	
		
	public Produto buscar(int id){
		UsuarioComumDAO usu = new UsuarioComumDAO();
		return usu.buscarProduto(id);
	}

}
