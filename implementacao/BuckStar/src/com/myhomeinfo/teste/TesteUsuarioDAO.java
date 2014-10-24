package com.myhomeinfo.teste;
import java.util.List;

import com.myhomeinfo.entidades.Usuario;
import com.myhomeinfo.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {	
	public static void main(String[] args) {
		//cadastrar();
		//alterar();
		//remover();
		//testeBuscarTodos();
		testeBuscar();
		testeAutenticar();
	}
	
	public static void cadastrar(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usu = new Usuario(15, "Teste DAO", "t_dao", "senha");
		usuDAO.cadastrar(usu);
	}
	
	public static void alterar(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usu = new Usuario(15, "Teste DAO", "t_dao", "senha");
		usuDAO.cadastrar(usu);
		
		Usuario usuUpd = new Usuario(15, "Teste DAO - UPDATE", "t_dao", "senha");
		usuDAO.alterar(usuUpd);		
	}
	
	public static void remover(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usu = new Usuario(15, "Teste DAO", "t_dao", "senha");
		usuDAO.cadastrar(usu);
		
		usuDAO.remover(usu);
	}
	
	public static void testeBuscar(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario u = usuDAO.buscar(1);
		System.out.println("Codigo: " + u.getCodigo() + "\t|" + "Nome: " + u.getNome() + "\t|" + "Login: " + u.getLogin() + "\t|" + "Senha: " + u.getSenha());
		
		List<Usuario> lst = usuDAO.buscar("Alex");
		for(Usuario u2: lst){
			System.out.println("Codigo: " + u2.getCodigo() + "\t|" + "Nome: " + u2.getNome() + "\t|" + "Login: " + u2.getLogin() + "\t|" + "Senha: " + u2.getSenha());
		}		

	}	
	
	public static void testeBuscarTodos(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		List<Usuario> lst = usuDAO.buscarTodos();
		for(Usuario u: lst){
			System.out.println("Codigo: " + u.getCodigo() + "\t|" + "Nome: " + u.getNome() + "\t|" + "Login: " + u.getLogin() + "\t|" + "Senha: " + u.getSenha());
		}
	}
	
	public static void testeAutenticar(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usu = new Usuario("alex", "12345");
		if(usuDAO.autenticar(usu))
			System.out.println("Logado com sucesso!");
		else 
			System.out.println("Usuário e/ou senha incorretos!");
	}

}
