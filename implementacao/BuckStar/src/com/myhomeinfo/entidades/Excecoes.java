package com.myhomeinfo.entidades;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Excecoes {

	public Excecoes() {
		super();
	}
	
	/* Exception */
	public static void erro(Exception e){
		System.out.println("Ocorreu uma exceção ao tentar realizar uma conversão");
		e.printStackTrace();
	}

	public static String erroString(Exception e){
		e.printStackTrace();
		throw new RuntimeException("Ocorreu uma exceção ao tentar realizar uma conversão");
	}	
	
	/* ParserConfigurationException */
	public static void erro(ParserConfigurationException e){
		System.out.println("Ocorreu uma exceção ao tentar configurar o parser");
		e.printStackTrace();
	}

	public static String erroString(ParserConfigurationException e){
		e.printStackTrace();
		throw new RuntimeException("Ocorreu uma exceção ao tentar configurar o parser");				
	}
	
	/* SAXException */
	public static void erro(SAXException e){
		System.out.println("Ocorreu uma exceção ao tentar fazer o parse do arquivo");
		e.printStackTrace();
	}

	public static String erroString(SAXException e){
		e.printStackTrace();
		throw new RuntimeException("Ocorreu uma exceção ao tentar fazer o parse do arquivo");
	}	
	
	/* IOException */
	public static void erro(IOException e){
		System.out.println("Ocorreu uma exceção ao tentar fazer a leitura de um arquivo");
		e.printStackTrace();
	}
	
	public static String erroString(IOException e){
		e.printStackTrace();
		throw new RuntimeException("Ocorreu uma exceção ao tentar fazer a leitura de um arquivo");	
	}
	
	public static void erro(ClassNotFoundException e){
		System.out.println("Ocorreu uma exceção onde o Driver JDBC nao foi encontrado\n" + e.getMessage());
		e.printStackTrace();
	}
	
	public static void erro(SQLException e){
		System.out.println("Ocorreu uma exceção na consulta SQL\n" + e.getMessage());
		e.printStackTrace();		
	}
}
