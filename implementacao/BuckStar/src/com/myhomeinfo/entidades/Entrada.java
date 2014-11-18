package com.myhomeinfo.entidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Entrada {
	/* Atributos */
	
	//Privates
	private int id;
	private Calendar dataEntrada;
	private Calendar horaEntrada;
	private String numeroNF;
	private Double valor;
	private ProdutoEntrada[] produtos;
	private Fornecedor fornecedor;
	private Usuario usuario;
	
	/* Construtores */
	
	//Vazio
	public Entrada() {
		super();
		this.id = 0;
		this.dataEntrada = null;
		this.horaEntrada = null;
		this.numeroNF = "";
		this.valor = 0.0;
		this.produtos = null;
		this.fornecedor = null;
		this.usuario = null;
	}

	//Completo
	public Entrada(int id, Calendar dataEntrada, Calendar horaEntrada, String numeroNF, Double valor, ProdutoEntrada[] produtos, Fornecedor fornecedor, Usuario usuario) {
		super();
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.horaEntrada = horaEntrada;
		this.numeroNF = numeroNF;
		this.valor = valor;
		this.produtos = produtos;
		this.fornecedor = fornecedor;
		this.usuario = usuario;
	}
	
	//Com código
	public Entrada(int id) {
		super();
		this.id = id;
		this.dataEntrada = null;
		this.horaEntrada = null;
		this.numeroNF = "";
		this.valor = 0.0;
		this.produtos = null;
		this.fornecedor = null;
		this.usuario = null;
	}

	//Com código e datas
	public Entrada(int id, Calendar dataEntrada, Calendar horaEntrada) {
		super();
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.horaEntrada = horaEntrada;
		this.numeroNF = "";
		this.valor = 0.0;
		this.produtos = null;
		this.fornecedor = null;
		this.usuario = null;
	}
	
	/* Métodos públicos */
	
	/* GETs */
	public int getId() {
		return id;
	}

	public Calendar getDataEntrada() {
		return dataEntrada;
	}

	public Calendar getHoraEntrada() {
		return horaEntrada;
	}

	public String getNumeroNF() {
		return numeroNF;
	}

	public Double getValor() {
		return valor;
	}

	public ProdutoEntrada[] getProdutos() {
		return produtos;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getDataEntradaFormatada(){
		String sr = "";
		if(dataEntrada != null){
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");  
			sr = s.format(dataEntrada.getTime());
		}
        return sr;
	}
	
	public String getHoraEntradaFormatada(){
		String str = "";
		if(horaEntrada != null){
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			str = sdf.format(horaEntrada.getTime());
		}
        return str;
	}
	
	/* SETs */	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDataEntrada(Calendar dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public void setHoraEntrada(Calendar horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public void setNumeroNF(String numeroNF) {
		this.numeroNF = numeroNF;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setProdutos(ProdutoEntrada[] produtos) {
		this.produtos = produtos;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
