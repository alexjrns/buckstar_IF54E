package com.homeinfo.auditoria;

import java.util.List;

import com.myhomeinfo.jdbc.UsuarioComumDAO;

public class AuditoriaUsuarioDAO {
	
	public AuditoriaUsuarioDAO() {
		super();
	}

	public List<AuditoriaUsuario> buscarTodos(){
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		return usuDAO.buscarTodasAuditoria();
	}
	
	
}
