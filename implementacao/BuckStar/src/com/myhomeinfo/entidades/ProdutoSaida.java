package com.myhomeinfo.entidades;

import javax.persistence.Entity;

@Entity
public class ProdutoSaida {
	private int codigo;
	private Saida saida;
	private Produto produto;
	private double quantidade;


	public ProdutoSaida() {
		super();
		this.codigo = 0;
		this.saida = null;
		this.produto = null;
		this.quantidade = 0;		
	}


	public ProdutoSaida(int codigo, Saida saida, Produto produto, double quantidade) {
		super();
		this.codigo = codigo;
		this.saida = saida;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	/* GETs */
	public int getCodigo() {
		return codigo;
	}

	public Saida getSaida() {
		return saida;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public int getCodProduto(){
		return produto.getCodigo();
	}
	
	public int getCodSaida(){
		return saida.getId();
	}

	public double getQuantidade() {
		return quantidade;
	}

	/* SETs */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
}
