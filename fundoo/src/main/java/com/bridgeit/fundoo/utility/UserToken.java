package com.bridgeit.fundoo.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

public class UserToken 
{
	  public static String TOKEN_SECRET="gh2we43jue";
	    public static String generateToken(Integer id) throws Exception
	    {
	    
	        System.out.println(id+"userid");
	            Algorithm algorithm= Algorithm.HMAC256(TOKEN_SECRET);
	        
	        //Algorithm algorithm = Algorithm.rs
	        String token=JWT.create()
	                            .withClaim("ID", id)
	                        
	                            .sign(algorithm);
	            System.out.println(token+"usertoken");
	            return token;        
	    }
	    
	    public static Integer tokenVerify(String token) throws Exception
	    {
	        Integer id;
	        System.out.println(token);
	            Verification verification=JWT.require(Algorithm.HMAC256(UserToken.TOKEN_SECRET));
	            JWTVerifier jwtverifier=verification.build();
	            DecodedJWT decodedjwt=jwtverifier.verify(token);
	            Claim claim=decodedjwt.getClaim("ID");
	            id=claim.asInt();    
	            System.out.println(id+"id");
	        
	            return id;
	    }

	

}
