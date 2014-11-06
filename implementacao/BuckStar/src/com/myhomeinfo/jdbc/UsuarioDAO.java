package com.myhomeinfo.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myhomeinfo.entidades.Usuario;
import com.myhomeinfo.entidades.Utilitario;

public class UsuarioDAO {
	private Connection con = Conexao.getConnection();
	
	public UsuarioDAO() {
	}

	@Override
	public String toString() {
		return "UsuarioDAO [con=" + con + "]";
	}

	/**
	 * Cadastra no banco de dados um usuário.
	 * @param usuario Objeto com todos os dados a serem inseridos no banco.
	 * @return Verdadeiro ser for inserido com sucesso e False caso não consiga realizar a inserção.
	 */
	public boolean cadastrar(Usuario usuario){
		String nome = usuario.getNome();
		String login = usuario.getLogin();
		String senha =  usuario.getSenha();
		
		Utilitario utl = new Utilitario();
		String[][] vet = { {"id_usuario", ""}, {"cod_usuario", ""}, {"des_nome", nome}, {"val_login", login} , {"val_senha", utl.md5(senha)} }; 
		UsuarioComumDAO dao = new UsuarioComumDAO();

		return dao.cadastrar("usuario", vet);
	}
	
	/**
	 * Cadastra ou altera no banco de dados um usuário, caso o usuário esteja com um código não existente no banco ele alterará.
	 * @param usuario Objeto com todos os dados a serem inseridos no banco.
	 * @return Verdadeiro ser for inserido/cadastrado com sucesso e False caso não consiga realizar a inserção/atualização.
	 */	
	public boolean salvar(Usuario usuario){
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		int codg = usuDAO.codAtual("usuario");		
		if((usuario.getCodigo() != 0) && (usuario.getCodigo() < codg))
			return alterar(usuario);
		else
			return cadastrar(usuario);
	}
	
	public boolean alterar(Usuario usuario){
		String codigo = String.valueOf(usuario.getCodigo());
		String nome = usuario.getNome();
		String login = usuario.getLogin();
		String senha =  usuario.getSenha();
		
		//String[][] vet = { {"cod_usuario", codigo}, {"des_nome", nome}, {"val_login", login} , {"val_senha", ("md5(" + senha + ")")} };
		Utilitario utl = new Utilitario();
		String[][] vet = { {"cod_usuario", codigo}, {"des_nome", nome}, {"val_login", login} , {"val_senha", utl.md5(senha)} };
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		return usuDAO.alterar("usuario", vet);
	}
	
	/**
	 * Remove do banco de dados um usuário.
	 * @param usuario Objeto com todos os dados, ou apenas o código a ser removido do banco.
	 * @return Verdadeiro ser for removido com sucesso e False caso não consiga remover.
	 */
	public boolean remover(Usuario usuario){
		UsuarioComumDAO usuDAO = new UsuarioComumDAO();
		return (usuDAO.remover("usuario", "usuario.cod_usuario = " + usuario.getCodigo()))? true: false;
	}
	
	public List<Usuario> buscarTodos(){
		String sql = "SELECT id_usuario, cod_usuario, des_nome, val_login, val_senha FROM usuario ORDER BY cod_usuario ASC;";
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
	
	public Usuario buscar(int id){
		String sql = "SELECT id_usuario, cod_usuario, des_nome, val_login, val_senha FROM usuario WHERE (usuario.cod_usuario = ?) ORDER BY cod_usuario ASC;";
		Usuario usr = new Usuario();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			resultado.next();
			
			usr.setCodigo(resultado.getInt("cod_usuario"));
			usr.setNome(resultado.getString("des_nome"));
			usr.setLogin(resultado.getString("val_login"));
			usr.setSenha(resultado.getString("val_senha"));
			
			preparador.close();
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return usr;
	}

	public List<Usuario> buscar(String nome){
		String sql = "SELECT id_usuario, cod_usuario, des_nome, val_login, val_senha FROM usuario WHERE (des_nome LIKE ?) ORDER BY cod_usuario ASC;";
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%" + nome + "%");
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

	public boolean autenticar(Usuario usr){
		String sql = "SELECT id_usuario, cod_usuario, des_nome, val_login, val_senha FROM usuario WHERE ((val_login = ?) AND (val_senha = ?)) ORDER BY cod_usuario ASC;";
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usr.getLogin());
			
			Utilitario utl = new Utilitario();
			String senha = utl.md5(usr.getSenha());
			preparador.setString(2, senha);
			ResultSet resultado = preparador.executeQuery();
			return (resultado.next() ? true: false);
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return false;
	}
}