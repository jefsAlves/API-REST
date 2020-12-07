package com.ibm.application.spb.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.application.spb.dto.EmailDTO;
import com.ibm.application.spb.security.JWTUtil;
import com.ibm.application.spb.security.UserSS;
import com.ibm.application.spb.security.UserService;
import com.ibm.application.spb.services.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService authService;

	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generationToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/forget")
	public ResponseEntity<Void> forget(@Valid @RequestBody EmailDTO emailDTO) {
		authService.sendNewPassword(emailDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
}
