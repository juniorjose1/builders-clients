package br.com.builders.apiclients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.builders.apiclients.config.security.TokenService;
import br.com.builders.apiclients.controller.dto.TokenDto;
import br.com.builders.apiclients.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginForm loginForm){
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(loginForm.getLogin(), loginForm.getPassword());
		Authentication authenticate = authManager.authenticate(authentication);
		String token = tokenService.generateToken(authenticate);
		return ResponseEntity.ok(new TokenDto(token, "Bearer"));
	}

}
