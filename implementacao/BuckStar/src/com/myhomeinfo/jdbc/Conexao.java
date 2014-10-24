package com.myhomeinfo.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static String caminho = "";
	private static String usuario = "";
	private static String senha = "";

	public static Connection getConnection(){
		Connection con = null;
		try{
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Driver JDBC nao encontrado\n" + e.getMessage());
			}

			/* Será configurado via XML */
			caminho = "jdbc:postgresql://localhost:5432/buckstar";
			usuario = "postgres";
			senha = "alex";

			con = DriverManager.getConnection(caminho, usuario, senha);
		} catch(SQLException e){
			System.out.println("Nao foi possivel estabelecer a conexao com o banco de dados\n" + e.getMessage());
		}
		return con;
	}
}
