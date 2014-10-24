package com.myhomeinfo.entidades;

public class Vendedor {
	/* Atributos */
	/* Privados */
	private int codigo;
	private int cdgRepresentante;
	private String nome;
	private String cidade;
	private String estado;
	private String fone;
	private String email;
	
	/* Construtores */
	public Vendedor() {
		super();
		this.codigo = 0;
		cdgRepresentante = 0;
		this.nome = "";
		this.cidade = "";
		this.estado = "";
		this.fone = "";
		this.email = "";
	}

	public Vendedor(int id) {
		super();
		this.codigo = id;
		cdgRepresentante = 0;
		this.nome = "";
		this.cidade = "";
		this.estado = "";
		this.fone = "";
		this.email = "";
	}	
	
	public Vendedor(int codigo, int cdgRepresentante, String nome, String cidade, String estado, String fone, String email) {
		super();
		this.codigo = codigo;
		this.cdgRepresentante = cdgRepresentante;
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado;
		this.fone = fone;
		this.email = email;
	}

	/* Gets */
	public int getCodigo() {
		return codigo;
	}
	
	public int getCdgRepresentante() {
		return this.cdgRepresentante;
	}

	
	public String getNome() {
		return nome;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}	
	
	public String getFone() {
		return fone;
	}

	/* Sets */	
	public String getEmail() {
		return email;
	}	
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setCodigoRepresentante(int codigoRepresentante) {
		this.cdgRepresentante = codigoRepresentante;
	}	
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public void setFone(String fone) {
		this.fone = fone;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
