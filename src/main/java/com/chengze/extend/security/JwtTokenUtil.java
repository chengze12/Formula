package com.chengze.extend.security;

import com.chengze.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private final String CLAIM_KEY_USERNAME="sub";
    private final String CLAIM_KEY_AUDINCE="audince";
    private final String CLAIM_KEY_CREATED="create";
    private final String secret="chengze";
    private static String AUDINCE_UNKWON="unknow";

    private Long expiration=86400L;

    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+ expiration* 1000);
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_USERNAME, new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    private Claims getClaimsFromToken(String token){
        Claims claims;
        try{
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            claims=null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token){
        String username;
        try{
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username= null;
        }
        return username;
    }

    private boolean isTokenExpired(String token){
        final Date expiration =getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public Date getExpirationDateFromToken(String token){
        Date ExpirationDate;
        try{
            final Claims claims = getClaimsFromToken(token);
            ExpirationDate = claims.getExpiration();
        }catch (Exception e){
            ExpirationDate= null;
        }
        return ExpirationDate;
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        User user = (User) userDetails;
        final String username= getUsernameFromToken(token);
//        final Date createDate= getCreateDateFromToken(token);
        return(
                username.equals((userDetails.getUsername()))
                        && !isTokenExpired(token)
                );
    }
}
