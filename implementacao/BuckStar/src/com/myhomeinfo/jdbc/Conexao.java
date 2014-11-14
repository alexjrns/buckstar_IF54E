package com.myhomeinfo.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.myhomeinfo.entidades.Utilitario;

public class Conexao {
	private static String driver = "";
	private static String servidor = "";
	private static String porta = "";
	private static String banco = "";
	
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

			String localXML = "D:/git_hub/BuckStar_IF54E/implementacao/BuckStar/WebContent/WEB-INF/config/conf.xml";
			driver = Utilitario.valorXML(localXML, "driver");
			servidor = Utilitario.valorXML(localXML, "servidor");
			porta = Utilitario.valorXML(localXML, "porta");
			banco = Utilitario.valorXML(localXML, "banco");
			caminho = "jdbc:" + driver + "://" + servidor + ":" + porta + "/" + banco;
			usuario = Utilitario.valorXML(localXML, "usuario");
			senha = Utilitario.valorXML(localXML, "senha");

			con = DriverManager.getConnection(caminho, usuario, senha);
		} catch(SQLException e){
			System.out.println("Nao foi possivel estabelecer a conexao com o banco de dados\n" + e.getMessage());
		}
		return con;
	}
}
