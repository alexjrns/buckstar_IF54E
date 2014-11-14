package com.myhomeinfo.entidades;

public class ProdutoEntrada {
	/* Atributos privates */
	private int codigo;
	private Entrada entrada;
	private Produto prod;
	private double quantidade;
	private double precoPago;
	
	public ProdutoEntrada() {
		super();
		this.codigo = 0;
		this.entrada = null;
		this.prod = null;
		this.quantidade = 0;
		this.precoPago = 0;
	}
	
	public ProdutoEntrada(int codigo, Entrada entrada, Produto prod, double quantidade, double precoPago) {
		super();
		this.codigo = codigo;
		this.entrada = entrada;
		this.prod = prod;
		this.quantidade = quantidade;
		this.precoPago = precoPago;
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

	public Entrada getEntrada() {
		return entrada;
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

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
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
