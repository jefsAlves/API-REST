package com.ibm.application.spb.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.experation}")
	private Long experation;

	public String generationToken(String userName) {
		return Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + experation))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean validationToken(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String userName = claims.getSubject();
			Date experation = claims.getExpiration();
			Date nowDate = new Date();
			if (userName != null && experation != null && nowDate.before(experation)) {
				return true;
			}
		}
		return false;
	}

	public String findUserNameToken(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	public Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
