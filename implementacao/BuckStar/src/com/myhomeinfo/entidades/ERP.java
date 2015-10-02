package com.myhomeinfo.entidades;

import javax.persistence.Entity;

@Entity
public class ERP {
	private int id;
	private String nome;
	private Saida[] saida;
	 
	public ERP() {
		super();
	}

	public void solicitarCompra() {		 
	}

	public boolean recebeEntrada(Saida saida) {
		return false;
	}
}
