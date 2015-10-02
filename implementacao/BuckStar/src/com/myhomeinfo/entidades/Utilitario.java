package com.myhomeinfo.entidades;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.persistence.Entity;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

@Entity
public class Utilitario {
	private static String key = "4b4ca*1";

    public Utilitario() {
		super();
	}

	public static String md5(String valor){
        try {
        	valor += key;
        	MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(valor.getBytes());

            return new BigInteger(1, digest.digest()).toString(16).trim();
        }catch(Exception e) {
            return Excecoes.erroString(e);
        }
    }
	
	public static String valorXML(String fileXML, String campo){
		try {
			Element raiz = lerXML(fileXML).getDocumentElement();
			return raiz.getElementsByTagName(campo).item(0).getFirstChild().getNodeValue();
		} catch (ParserConfigurationException e) {
			return Excecoes.erroString(e);
		} catch (SAXException e) {
			return Excecoes.erroString(e);
		} catch (IOException e) {
			return Excecoes.erroString(e);
		}
	}

	private static Document lerXML(String arquivoXML) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(arquivoXML);
		return doc;
	}
}
