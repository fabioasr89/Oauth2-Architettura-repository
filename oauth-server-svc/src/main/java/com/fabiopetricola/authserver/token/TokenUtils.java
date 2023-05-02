package com.fabiopetricola.authserver.token;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.naming.OperationNotSupportedException;

import com.nimbusds.jose.jwk.RSAKey;

public final class TokenUtils {
	
	private TokenUtils() throws Exception{
		throw new OperationNotSupportedException("La classe seguente non può essere istanziata");
	}
	
	
	
	public static RSAKey firmaTokenConChiaviRSA() throws NoSuchAlgorithmException {
		KeyPair keyPair=inizializza();
		RSAPublicKey publicKey=(RSAPublicKey)keyPair.getPublic();
		RSAPrivateKey privateKey=(RSAPrivateKey)keyPair.getPrivate();
		return new RSAKey.Builder(publicKey).
				privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
	}
	
	//Metodo per gestire la generazione delle chiavi con RSA
	private static KeyPair inizializza() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		return keyPairGenerator.generateKeyPair();
	}
}
