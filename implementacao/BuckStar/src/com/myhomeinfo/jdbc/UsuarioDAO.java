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
	private UsuarioComumDAO usuDAO = new UsuarioComumDAO();
	
	
	public UsuarioDAO() {
	}

	@Override
	public String toString() {
		return "UsuarioDAO [con=" + con + "]";
	}

	/**
	 * Cadastra no banco de dados um usu�rio.
	 * @param usuario Objeto com todos os dados a serem inseridos no banco.
	 * @return Verdadeiro ser for inserido com sucesso e False caso n�o consiga realizar a inser��o.
	 */
	public boolean cadastrar(Usuario usuario){
		String nome = usuario.getNome();
		String login = usuario.getLogin();
		String senha =  usuario.getSenha();
		
		String[][] vet = { {"id_usuario", ""}, {"cod_usuario", ""}, {"des_nome", nome}, {"val_login", login} , {"val_senha", Utilitario.md5(senha)} }; 

		return usuDAO.cadastrar("usuario", vet);
	}
	
	/**
	 * Cadastra ou altera no banco de dados um usu�rio, caso o usu�rio esteja com um c�digo n�o existente no banco ele alterar�.
	 * @param usuario Objeto com todos os dados a serem inseridos no banco.
	 * @return Verdadeiro ser for inserido/cadastrado com sucesso e False caso n�o consiga realizar a inser��o/atualiza��o.
	 */	
	public boolean salvar(Usuario usuario){
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
		String[][] vet = { {"cod_usuario", codigo}, {"des_nome", nome}, {"val_login", login} , {"val_senha", Utilitario.md5(senha)} };
		return usuDAO.alterar("usuario", vet);
	}
	
	/**
	 * Remove do banco de dados um usu�rio.
	 * @param usuario Objeto com todos os dados, ou apenas o c�digo a ser removido do banco.
	 * @return Verdadeiro ser for removido com sucesso e False caso n�o consiga remover.
	 */
	public boolean remover(Usuario usuario){
		return (usuDAO.remover("usuario", "usuario.cod_usuario = " + usuario.getCodigo()))? true: false;
	}
	
	public List<Usuario> buscarTodos(){
		return usuDAO.buscarUsuarioTodos();
	}
	
	public Usuario buscar(int id){
		return usuDAO.buscarUsuario(id);
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

	public Usuario autenticar(Usuario usr){
		String sql = "SELECT id_usuario, cod_usuario, des_nome, val_login, val_senha FROM usuario WHERE ((val_login = ?) AND (val_senha = ?)) ORDER BY cod_usuario ASC;";
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usr.getLogin());

			String senha = Utilitario.md5(usr.getSenha());
			preparador.setString(2, senha);
			ResultSet resultado = preparador.executeQuery();
            if(resultado.next()){
            	int chave = resultado.getInt("id_usuario");
            	int id = resultado.getInt("cod_usuario");
                String nome = resultado.getString("des_nome");
                String lgn = resultado.getString("val_login");
                String sen = resultado.getString("val_senha");

                return new Usuario(chave, id, nome, lgn, sen);
            }
		} catch (SQLException e) {
			System.out.println("Erro no comando SQL de Consulta: " + e.getMessage() + "\n" + "Comando com erro: " + sql);
		}
		return null;
	}
}