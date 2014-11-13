package com.myhomeinfo.entidades;

public class ProdutoEstoque {
	/* Atributos privates */
	private int codigo;
	private Entrada estoque;
	private Produto prod;
	private double quantidade;
	private double precoPago;
	
	public ProdutoEstoque() {
		super();
		this.codigo = 0;
		this.estoque = null;
		this.prod = null;
		this.quantidade = 0;
		this.precoPago = 0;
	}
	
	public boolean aumentarQuantidade(){
		if((quantidade + 1) <= prod.getQuantidadeAtual()){
			this.quantidade++;
			prod.setQuantidadeAtual(prod.getQuantidadeAtual() - 1);
			return true;
		} else{
			return false;
		}
	}

	public boolean aumentarQuantidade(double qtd){
		if((quantidade + qtd) <= prod.getQuantidadeAtual()){
			this.quantidade += qtd;
			prod.setQuantidadeAtual(prod.getQuantidadeAtual() - qtd);
			return true;
		} else{
			return false;
		}
	}

	public int getCodProduto(){
		return prod.getCodigo();
	}
	public int getCodigo(){
		return this.codigo;
	}

	public Entrada getEstoque() {
		return estoque;
	}

	public Produto getProd() {
		return prod;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public double getPrecoPago() {
		return precoPago;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setEstoque(Entrada estoque) {
		this.estoque = estoque;
	}

	public void setProd(Produto prod) {
		this.prod = prod;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public void setPrecoPago(double precoPago) {
		this.precoPago = precoPago;
	}
}
