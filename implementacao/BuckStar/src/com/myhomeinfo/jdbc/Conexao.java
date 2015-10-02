package com.myhomeinfo.jdbc;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import com.myhomeinfo.entidades.Excecoes;

public class Conexao {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory(){
        if (emf == null) {
            try{
                //Logger.getLogger(Conexao.class.getName()).log(Level.INFO, "Criando conexão com banco de dados");
                emf = Persistence.createEntityManagerFactory("tecnocontrol");
            }catch(PersistenceException e) {
                Excecoes.erro(e);
                emf = null;
            }
        }
        return emf;
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            emf = getEntityManagerFactory();
        }
        return emf.createEntityManager();
    }

    public static void closeFactory() {
        createEntityManager().close();
        emf = null;
    }	
	
	
	
	/*private static String driver = "";
	private static String servidor = "";
	private static String porta = "";
	private static String banco = "";

	private static String caminho = "";
	private static String usuario = "";
	private static String senha = "";

	public static Connection getConnection(){
		Connection con = null;
		try{
			try{
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e){
				Excecoes.erro(e);
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
	}*/
}
