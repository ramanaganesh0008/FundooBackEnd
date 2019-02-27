package com.bridgeit.fundoo.utility;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bridgeit.fundoo.model.User;


public class Utility 
{
	private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key,16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret)
    {
    	
        try
        {
            setKey(secret);

        	
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        	
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

        	
            
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    public static void sendEmail(User user,String token,String url)
	{  
	     send("fundoonote2019@gmail.com","iddqzqjjfyjiktzu",user.getEmail(), token,url);  

	}
	
    public static void send(final String from,final String password,String to,String token,String url){  
        //Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject("Verification mail"); 
         
         String msg="you have to verify the account by click this link : http://192.168.0.16:8080/fundoo/"+url+"/"+token;
         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
           
  }  
	
	   public static String OTP() 
	    { 

	        System.out.println("Generating OTP using random() : "); 
	        System.out.print("You OTP is : "); 
	        Random random = new Random(); 
	  	  	
	  	  	int otp=0;
	        for (int i = 0; i < 6; i++) 
	        {
	        	if(i==0 && random.nextInt(10)==0)
	        	{
	        		i=0;
	        	}
	        	else
	        	{
	        		otp=otp*10+random.nextInt(10);
	        	}
	        }
	        for (int i = Integer.toString(otp).length(); i < 6; i++) 
	        {
	        	otp*=10;
			}
	        String otpPassword=otp+"";
	       System.out.println(otpPassword);
	       return otpPassword;
	    }
	  
}

