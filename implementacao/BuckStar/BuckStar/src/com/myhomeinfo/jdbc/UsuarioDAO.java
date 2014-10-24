package com.myhomeinfo.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myhomeinfo.entidades.Usuario;

public class UsuarioDAO {
	private Connection con = Conexao.getConnection();
	
	public UsuarioDAO() {
	}
	
	public boolean cadastrar(Usuario usuario){
		String sql = "INSERT INTO usuario(id_usuario, cod_usuario, des_nome, val_login, val_senha) VALUES (DEFAULT,?,?,?,?)";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usuario.getCodigo());
			preparador.setString(2, usuario.getNome());
			preparador.setString(3, usuario.getLogin());
			preparador.setString(4, usuario.getSenha());
			
			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com sucesso!");
			return true;
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Insert: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
			return false;
		}
	}
	public boolean alterar(Usuario usuario){
		String sql = "UPDATE usuario SET cod_usuario = ?, des_nome = ?, val_login = ?, val_senha = ? WHERE (usuario.cod_usuario = ?)";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usuario.getCodigo());
			preparador.setString(2, usuario.getNome());
			preparador.setString(3, usuario.getLogin());
			preparador.setString(4, usuario.getSenha());
			preparador.setInt(5, usuario.getCodigo());
			
			preparador.execute();
			preparador.close();
			System.out.println("Alterado com sucesso!");
			return true;
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Update: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
			return false;
		}
	}
	
	public boolean remover(Usuario usuario){
		String sql = "DELETE FROM usuario WHERE (usuario.cod_usuario = ?)";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usuario.getCodigo());
			
			preparador.execute();
			preparador.close();
			System.out.println("Removido com sucesso!");
			return true;
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Delete: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
			return false;
		}		
	}
	
	public List<Usuario> buscarTodos(){
		String sql = "SELECT id_usuario, cod_usuario, des_nome, val_login, val_senha FROM usuario ORDER BY cod_usuario ASC NULLS LAST;";
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Usuario usu = new Usuario();
				usu.setCodigo(resultado.getInt("cod_usuario"));
				usu.setNome(resultado.getString("des_nome"));
				usu.setLogin(resultado.getString("val_login"));
				usu.setSenha(resultado.getString("val_senha"));
				lista.add(usu);
			}
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return lista;
	}
}
