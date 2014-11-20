package com.myhomeinfo.entidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Saida {
	private int id;
	private Calendar data;
	private Calendar hora;
	private double valorSaida;
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

	public Saida(int codigo) {
		super();
		this.id = codigo;
		this.data = null;
		this.hora = null;
		this.valorSaida = 0;
		this.entrada = null;
		this.produtos = null;
		this.usuario = null;
	}	

	public Saida(int codigo, Calendar data, Calendar hora) {
		super();
		this.id = codigo;
		this.data = data;
		this.hora = hora;
		this.valorSaida = 0;
		this.entrada = null;
		this.produtos = null;
		this.usuario = null;
	}		
	
	public Saida(int id, Calendar data, Calendar hora, double valorSaida, Entrada entrada, ProdutoSaida[] produtos, Usuario usuario) {
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
	
	public double getValorSaida() {
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
	
	public String getDataSaidaFormatada(){
		String sr = "";
		if(data != null){
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");  
			sr = s.format(data.getTime());
		}
        return sr;
	}
	
	public String getHoraSaidaFormatada(){
		String str = "";
		if(hora != null){
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			str = sdf.format(hora.getTime());
		}
        return str;
	}

	/* SETs */
	public void setId(int id) {
		this.id = id;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public void setHora(Calendar hora) {
		this.hora = hora;
	}	
	
	public void setValorSaida(double valorSaida) {
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
