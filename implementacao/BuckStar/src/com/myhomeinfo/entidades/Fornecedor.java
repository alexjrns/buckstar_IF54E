package com.myhomeinfo.entidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fornecedor {
	/* Atributos */
	//Private
	private int codigo;
	private String razaoSocial;
	private String CNPJ;
	private String nomeFantasia;
	private String inscricaoEstadual;
	private String logradouro;
	private String numero;
	private String complemento;
	private String CEP;
	private String cidade;
	private String estado;
	private String telefone;
	private String eMail;
	private String site;
	private Calendar dataDeCadastro;
	private boolean desativado;
	
	/* Construtores */
	public Fornecedor() {
		super();
		this.codigo = 0;
		this.razaoSocial = "";
		this.CNPJ = "";
		this.nomeFantasia = "";
		this.inscricaoEstadual = "";
		this.logradouro = "";
		this.numero = "";
		this.complemento = "";
		this.CEP = "";
		this.cidade = "";
		this.estado = "";
		this.telefone = "";
		this.eMail = "";
		this.site = "";
		this.dataDeCadastro = null;
		this.desativado = false;
	}

	public Fornecedor(int codigo, String razaoSocial, String cNPJ,
			String nomeFantasia, String inscricaoEstadual, String logradouro,
			String numero, String complemento, String cEP, String cidade,
			String estado, String telefone, String eMail, String site,
			Calendar dataDeCadastro, boolean desativado) {
		super();
		this.codigo = codigo;
		this.razaoSocial = razaoSocial;
		this.CNPJ = cNPJ;
		this.nomeFantasia = nomeFantasia;
		this.inscricaoEstadual = inscricaoEstadual;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.CEP = cEP;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone = telefone;
		this.eMail = eMail;
		this.site = site;
		this.dataDeCadastro = dataDeCadastro;
		this.desativado = desativado;
	}
	
	public Fornecedor(int codigo) {
		super();
		this.codigo = codigo;
		this.razaoSocial = "";
		this.CNPJ = "";
		this.nomeFantasia = "";
		this.inscricaoEstadual = "";
		this.logradouro = "";
		this.numero = "";
		this.complemento = "";
		this.CEP = "";
		this.cidade = "";
		this.estado = "";
		this.telefone = "";
		this.eMail = "";
		this.site = "";
		this.dataDeCadastro = null;
		this.desativado = false;
	}
	
	public Fornecedor(int codigo, Calendar data) {
		super();
		this.codigo = codigo;
		this.razaoSocial = "";
		this.CNPJ = "";
		this.nomeFantasia = "";
		this.inscricaoEstadual = "";
		this.logradouro = "";
		this.numero = "";
		this.complemento = "";
		this.CEP = "";
		this.cidade = "";
		this.estado = "";
		this.telefone = "";
		this.eMail = "";
		this.site = "";
		this.dataDeCadastro = data;
		this.desativado = false;
	}	

	/* Gets */
	public int getCodigo() {
		return codigo;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCEP() {
		return CEP;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public String geteMail() {
		return eMail;
	}

	public String getSite() {
		return site;
	}

	public Calendar getDataDeCadastro() {
		return dataDeCadastro;
	}
	
	public String getDataFormatada(){
		String sr = "";
		if(dataDeCadastro != null){
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");  
			sr = s.format(dataDeCadastro.getTime());
		}
        return sr;
	}	

	public boolean isDesativado() {
		return desativado;
	}

	/* Sets */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setDataDeCadastro(Calendar dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public void setDesativado(boolean desativado) {
		this.desativado = desativado;
	}	
}