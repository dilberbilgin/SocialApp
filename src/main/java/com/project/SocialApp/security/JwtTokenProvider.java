package com.project.SocialApp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${socialapp.app.secret}")
    private String APP_SECRET;

    @Value("${socialapp.expires.in}")
    private long EXPIRES_IN;

    // jwt token olusturan metod
    public String generateJwtToken(Authentication auth) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal(); //principal = authenticate edecegimiz User ve JwtUserDetailse'e cast ediliyor
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId())) // subject userimiz oluyor
                .setIssuedAt(new Date()) // key ne zaman olusturuldu. su anda
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET) // key'i olusturdugumuz algoritma
                .compact();
    }

    // yukarida olusturulan key icerisinden id'yi cekicez.
    Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(APP_SECRET) // pars ederken bu key'i kullaniyoruz. bu key'e gore asagidaki tokeni coz
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject()); //longa donusturuyoruz. user id 'imizi aldik.
    }

    boolean validateToken(String token) { //bu token dogru mu diye kontrol etmeliyiz.
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}


