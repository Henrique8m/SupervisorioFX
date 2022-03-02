package com.rodrigues.rodrigues.securit;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//Advanced Encryption Standard
public class EncryptionAES {
	
	//O vetor de inicialização (IV) nada mais é do que o "estado" a ser utilizado inicialmente
	 String IV = "AAAAAAAAAAAAAAAA";
	 String textopuro = "teste texto 12345678\0\0\0";
	 String chaveencriptacao = "0123456789abcdef";
	private static byte[] bytes;


	public void testEncrypt() {
        
        try {

	      System.out.println("Texto Puro: " + textopuro);

	      byte[] textoencriptado = encrypt(textopuro, chaveencriptacao);

	      System.out.print("Texto Encriptado: ");

	      for (int i=0; i<textoencriptado.length; i++)
	             System.out.print(new Integer(textoencriptado[i])+" ");

	      System.out.println("");

	      String textodecriptado = decrypt(textoencriptado, chaveencriptacao);

	      System.out.println("Texto Decriptado: " + textodecriptado);
	      /*
	      Runtime rt = Runtime.getRuntime();  
			Process proc = rt.exec("getmac");  
			InputStream input = proc.getInputStream();
			bytes = input.readAllBytes();
			for(byte x : bytes)
			System.out.println(x);
	      */

	   } catch (Exception e) {
	      e.printStackTrace();
	   }
	 }

	 public  byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
	   Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
	   SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
	   encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	   return encripta.doFinal(textopuro.getBytes("UTF-8"));
	 }

	 public String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception{
	   Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
	   SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
	   decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	   return new String(decripta.doFinal(textoencriptado),"UTF-8");
	 }
	 

}




