package com.springboot.Main.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; 
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthebticationFilter extends OncePerRequestFilter
{
	
	@Autowired
	private UserDetailsService detailsService;
	
	@Autowired
	private JwtTokenHelper helper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	 {
		// 1) get token header.
		String requestToken = request.getHeader("Authorization");
		System.out.println(requestToken);
		
		// 2) We can check token start for Bearer or not 
		String username=null;
		String token =null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer"))
		{
			//Actual Fetch the token in requestToken
			token = requestToken.substring(7);
		
		   try 
		   {
			    //Fetch the Username for token.
				username = this.helper.getUsernameFromToken(token);
		   } 
		   catch (IllegalArgumentException e) 
		   {
				System.out.println("Unable to get JWT Token");
		   }
		   catch (ExpiredJwtException e)
		   {
			   System.out.println("JWT token as expired");
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		}
		else
		{
			System.out.println("JWT token dose not being with Bearer ");
		}
		
		// 3) Once We get the token , then now validate
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			// Find the UserDetails using UserDeatailsService method loadUserByUsername.
			UserDetails userDetails =this.detailsService.loadUserByUsername(username);
			
			//check the Token validate or not 
			if(this.helper.validateToken(token, userDetails))
			{
				//All right now so ....
				// authentication ....
				
				
				//create Authentication object  ,Because this object we can pass setAuthentication. 
				// UsernamePasswordAuthenticationToken this class we can pass the parameter (1.principal      2.credentials     3.authorities )
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
				//this line we can use the set Details in UsernamePasswordAuthenticationToken method.
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				// Set Authentication by using SecurityContextHolder.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("Invalid JWT Token.....");
			}
		}
		else
		{
			System.out.println("username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
	}	
}
