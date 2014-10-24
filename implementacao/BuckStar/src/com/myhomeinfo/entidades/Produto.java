package com.myhomeinfo.entidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Produto {
	/* Atributos */
	//Private	
	private int id;
	private String nome;
	private String marca;
	private Calendar dataUltimaCompra;
	private String unidadeCompra;
	private String unidadeTransmissao;
	private String descricaoUso;
	private double quantidadeAtual;
	private double quantidadeRecomendada;
	private double quantidadeMinima;
	private String codigoBarras;
	private double valorMedioCompra;
	private boolean desativado;
	private int ultimoFornecedor;

	/* Construtores */	
	public Produto() {
		super();
	}

	public Produto(int id, String nome, String marca,
			Calendar dataUltimaCompra, String unidadeCompra,
			String unidadeTransmissao, String descricaoUso,
			double quantidadeAtual, double quantidadeRecomendada,
			double quantidadeMinima, String codigoBarras,
			double valorMedioCompra, boolean desativado, int ultimoFornecedor) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.dataUltimaCompra = dataUltimaCompra;
		this.unidadeCompra = unidadeCompra;
		this.unidadeTransmissao = unidadeTransmissao;
		this.descricaoUso = descricaoUso;
		this.quantidadeAtual = quantidadeAtual;
		this.quantidadeRecomendada = quantidadeRecomendada;
		this.quantidadeMinima = quantidadeMinima;
		this.codigoBarras = codigoBarras;
		this.valorMedioCompra = valorMedioCompra;
		this.ultimoFornecedor = ultimoFornecedor;
		this.desativado = desativado;
	}

	public boolean isDesativado() {
		return desativado;
	}

	public void setDesativado(boolean desativado) {
		this.desativado = desativado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Calendar getDataUltimaCompra() {
		return dataUltimaCompra;
	}

	public void setDataUltimaCompra(Calendar dataUltimaCompra) {
		this.dataUltimaCompra = dataUltimaCompra;
	}

	public String getUnidadeCompra() {
		return unidadeCompra;
	}

	public void setUnidadeCompra(String unidadeCompra) {
		this.unidadeCompra = unidadeCompra;
	}

	public String getUnidadeTransmissao() {
		return unidadeTransmissao;
	}

	public void setUnidadeTransmissao(String unidadeTransmissao) {
		this.unidadeTransmissao = unidadeTransmissao;
	}

	public String getDescricaoUso() {
		return descricaoUso;
	}

	public void setDescricaoUso(String descricaoUso) {
		this.descricaoUso = descricaoUso;
	}

	public double getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(double quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	public double getQuantidadeRecomendada() {
		return quantidadeRecomendada;
	}

	public void setQuantidadeRecomendada(double quantidadeRecomendada) {
		this.quantidadeRecomendada = quantidadeRecomendada;
	}

	public double getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(double quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public double getValorMedioCompra() {
		return valorMedioCompra;
	}

	public void setValorMedioCompra(double valorMedioCompra) {
		this.valorMedioCompra = valorMedioCompra;
	}

	public int getUltimoFornecedor() {
		return ultimoFornecedor;
	}

	public void setUltimoFornecedor(int ultimoFornecedor) {
		this.ultimoFornecedor = ultimoFornecedor;
	}

	public String getDataFormatada(){
		String sr = "";
		if(this.dataUltimaCompra != null){
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");  
			sr = s.format(this.dataUltimaCompra.getTime());
		}
        return sr;
	}
	
}
