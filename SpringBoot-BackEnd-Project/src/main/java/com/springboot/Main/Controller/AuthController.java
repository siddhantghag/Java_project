package com.springboot.Main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Main.Payload.JwtAuthRequest;
import com.springboot.Main.Payload.JwtAuthResponces;
import com.springboot.Main.Security.JwtTokenHelper;

@RestController
@RequestMapping("/api/auth/")
public class AuthController 
{
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService detailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/createtoken/login")
	public ResponseEntity<JwtAuthResponces> createToken(@RequestBody JwtAuthRequest authRequest) throws Exception
	{
		System.out.println(authRequest);
		
		try
		 {
			 this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					 (authRequest.getUsername(), authRequest.getPassword()));
		 }
		 catch (UsernameNotFoundException e) 
		 {
			e.printStackTrace(); 
			throw new Exception("Invalid Username and Password...");	
		 }
		 catch(BadCredentialsException b)
		 {
			 b.printStackTrace();
			 throw new Exception("Bad credential....");
		 }
		String token=this.jwtTokenHelper.doGenerateToken(authRequest.getUsername());
	    return ResponseEntity.ok(new JwtAuthResponces(token));
	}
}
