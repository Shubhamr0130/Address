package com.example.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwt.helper.JwtUtil;
import com.example.jwt.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get jwt
		//Bearer
		//validate
		
		String requestTokenHeader =request.getHeader("Authorization");
		String username = null;
		String jwtToken=null;
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken=requestTokenHeader.substring(7);
			try {
				username=this.jwtUtil.getUsername(jwtToken);
				System.out.println("Username: "+ username);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("Username1: "+ username);
				
			UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(username);
			//security
			System.out.println("UserDetail: "+ userDetails);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			{
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken	=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				System.out.println("usernamePasswordAuthenticationToken: "+ usernamePasswordAuthenticationToken);
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}else {
			System.out.println("Token is not valid");
		}
		}
		filterChain.doFilter(request, response);
	
	}

}
