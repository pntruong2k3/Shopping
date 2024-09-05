package com.shopping.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		   Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        Set<String> roles = AuthorityUtils.authorityListToSet(authorities);

		System.out.println(roles);
	     if(roles.contains("ADMIN"))
	        {
	            response.sendRedirect("/admin");
	        }else if(roles.contains("USER")) {
	            response.sendRedirect("/");
	        }else {
	        	 response.sendRedirect("/error");
	        }
	    }
		
	}

