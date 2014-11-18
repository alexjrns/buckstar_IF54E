package com.myhomeinfo.entidades;

import java.util.Calendar;

public class Saida {
	private int id;
	private Calendar data;
	private Calendar hora;
	private float valorSaida;
	private Entrada entrada;
	private ProdutoSaida produtos[];
	private Usuario usuario;
	
	public Saida() {
		super();
		this.id = 0;
		this.data = null;
		this.hora = null;
		this.valorSaida = 0;
		this.entrada = null;
		this.produtos = null;
		this.usuario = null;
	}

	public Saida(int id, Calendar data, Calendar hora, float valorSaida, Entrada entrada, ProdutoSaida[] produtos, Usuario usuario) {
		super();
		this.id = id;
		this.data = data;
		this.hora = hora;
		this.valorSaida = valorSaida;
		this.entrada = entrada;
		this.produtos = produtos;
		this.usuario = usuario;
	}

	/* GETs */
	public int getId() {
		return id;
	}

	public Calendar getData() {
		return data;
	}

	public Calendar getHora() {
		return hora;
	}	
	
	public float getValorSaida() {
		return valorSaida;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public ProdutoSaida[] getProdutos() {
		return produtos;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	/* SETs */
	public void setId(int id) {
		this.id = id;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public void setHora(Calendar hora) {
		this.data = hora;
	}	
	
	public void setValorSaida(float valorSaida) {
		this.valorSaida = valorSaida;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public void setProdutos(ProdutoSaida[] produtos) {
		this.produtos = produtos;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
