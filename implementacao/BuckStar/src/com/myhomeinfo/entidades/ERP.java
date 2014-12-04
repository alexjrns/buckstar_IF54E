package com.myhomeinfo.entidades;

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
