package com.homeinfo.auditoria;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Auditoria {
	private Calendar dataAlteracao;
	private Calendar horaAlteracao;
	private String usuarioAlteracao;
	private String tipoAlteracao;
	
	public Auditoria() {
		super();
	}

	public Auditoria(Calendar dataAlteracao, Calendar horaAlteracao,
			String usuarioAlteracao, String tipoAlteracao) {
		super();
		this.dataAlteracao = dataAlteracao;
		this.horaAlteracao = horaAlteracao;
		this.usuarioAlteracao = usuarioAlteracao;
		this.tipoAlteracao = tipoAlteracao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public Calendar getHoraAlteracao() {
		return horaAlteracao;
	}

	public String getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public String getTipoAlteracao() {
		return tipoAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public void setHoraAlteracao(Calendar horaAlteracao) {
		this.horaAlteracao = horaAlteracao;
	}

	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public void setTipoAlteracao(String tipoAlteracao) {
		this.tipoAlteracao = tipoAlteracao;
	}
	
	public String getDataFormatada(){
		String sr = "";
		if(dataAlteracao != null){
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");  
			sr = s.format(dataAlteracao.getTime());
		}
        return sr;
	}
	
	public String getHoraFormatada(){
		String str = "";
		if(horaAlteracao != null){
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			str = sdf.format(horaAlteracao.getTime());
		}
        return str;
	}	
	
}
