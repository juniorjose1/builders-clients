package br.com.builders.apiclients.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.builders.apiclients.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authenticate) {
		User user = (User) authenticate.getPrincipal();
		
		Date actualDate = new Date();
		
		Date expirationDate = new Date(actualDate.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("Clients API")
				.setSubject(user.getId().toString())
				.setIssuedAt(actualDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public Boolean verifyToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public Long getUserId(String token) {
		Claims bodyToken = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(bodyToken.getSubject());
	}

}
