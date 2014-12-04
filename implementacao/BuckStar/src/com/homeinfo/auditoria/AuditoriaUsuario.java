package com.homeinfo.auditoria;

import com.myhomeinfo.entidades.Usuario;

public class AuditoriaUsuario {
	private int codigo;
	private Auditoria auditoria;
	private Usuario usuario;
	
	public AuditoriaUsuario() {
		super();
		codigo = 0;
		auditoria = new Auditoria();
		usuario = new Usuario();
	}

	public int getCodigo() {
		return codigo;
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
