package com.myhomeinfo.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection(){
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/myemail", "postgres", "alex");
			System.out.println("Conexao com o banco realizada com sucesso!\n");
		} catch(SQLException e){
			System.out.println("Nao foi possivel estabelecer a conexao com o banco de dados\n" + e.getMessage());
		}
		return con;
	}	
}
