package com.example.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.helper.JwtUtil;
import com.example.jwt.model.JwtRequest;
import com.example.jwt.model.JwtResponse;
import com.example.jwt.service.CustomUserDetailsService;

@RestController
public class JwtController {
  
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customDetailsService;
	@RequestMapping(value="/token",method =RequestMethod.POST)
	public ResponseEntity<?> genrateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		System.out.println(jwtRequest);
	try {
	this.authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
	}catch(UsernameNotFoundException e) {
		e.printStackTrace();
		throw new Exception("Bad Creadentials");
	}catch (BadCredentialsException e) {
		e.printStackTrace();
		throw new Exception("Bad Creadentials");
	}
     
	UserDetails userDetails=this.customDetailsService.loadUserByUsername(jwtRequest.getUsername());
	String token = this.jwtUtil.generateToken(userDetails);
	System.out.println("JWT" + token);
	
	//{"token":"value"}
	
	return ResponseEntity.ok(new JwtResponse(token));
	}
}
