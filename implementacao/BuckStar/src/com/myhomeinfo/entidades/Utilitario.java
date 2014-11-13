package com.myhomeinfo.entidades;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Utilitario {
	private String key = "4b4ca*1";	
	
    public Utilitario() {
		super();
	}

	public String md5(String valor){  
        try {     
        	valor += key;
        	MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(valor.getBytes());  
            
            return new BigInteger(1, digest.digest()).toString(16).trim();  
        }catch(Exception e) {  
            throw new RuntimeException("Erro ao calcular MD5 de " + valor, e);  
        }     
    }
	
	public String valorXML(String fileXML, String campo){
		try {
			Element raiz = lerXML(fileXML).getDocumentElement();
			return raiz.getElementsByTagName(campo).item(0).getFirstChild().getNodeValue();
		} catch (ParserConfigurationException e) {
			System.out.println("O parser não foi configurado corretamente.");
			e.printStackTrace();
			return "";
		} catch (SAXException e) {
			System.out.println("Problema ao fazer o parse do arquivo.");
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			System.out.println("O arquivo não pode ser lido.");
			e.printStackTrace();
			return "";
		}
	}

	private Document lerXML(String arquivoXML) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(arquivoXML);
		return doc;
	}
	
}
