package com.myhomeinfo.entidades;

public class Usuario {
	private int codigo;
	private String nome;
	private String login;
	private String senha;
	
	public Usuario(){	
	}

	public Usuario(int codigo, String nome, String login, String senha){
		this.codigo = codigo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(String login, String senha){
		this.codigo = -1;
		this.nome = null;
		this.login = login;
		this.senha = senha;
	}	

	public Usuario(int id){
		this.codigo = id;
		this.nome = null;
		this.login = null;
		this.senha = null;
	}	
	
	
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", login="
				+ login + ", senha=" + senha + "]";
	}	
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	

}
