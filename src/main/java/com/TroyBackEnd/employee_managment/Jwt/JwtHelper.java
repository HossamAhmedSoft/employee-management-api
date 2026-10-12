package com.TroyBackEnd.employee_managment.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtHelper {

    @Value("${jwt.secret}")
    private String JWT_SECRET;


    // استخراج ال username من التوكن
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // استخراج Claim معين
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())   // بدل setSigningKey
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // إنشاء توكن عادي
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // إنشاء توكن مع extra claims
    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails) {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 ساعة
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // التحقق من صلاحية التوكن
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        final Date tokenExpiraationDate = extractClaims(token, Claims::getExpiration);
        boolean usernameMatch = Objects.equals(username, userDetails.getUsername());
        boolean tokenIsExpired = tokenExpiraationDate.before(new Date(System.currentTimeMillis()));


        return usernameMatch && !tokenIsExpired;
    }

    // التأكد إن التوكن مش منتهي
    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration)
                .before(new Date());
    }

    // تحويل ال secret ل SecretKey
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}