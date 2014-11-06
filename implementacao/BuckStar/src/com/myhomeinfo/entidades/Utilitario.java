package com.myhomeinfo.entidades;

import java.math.BigInteger;
import java.security.MessageDigest;

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
	
}
