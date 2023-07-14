package com.masai.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Filter class to generate JWT token for authenticated users.
 */
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

	/**
     * Filters the incoming request to generate and add a JWT token to the response header.
     *
     * @param request  The incoming HttpServletRequest.
     * @param response The outgoing HttpServletResponse.
     * @param filterChain The filter chain to continue the request processing.
     * @throws ServletException If an error occurs during the filter processing.
     * @throws IOException If an I/O error occurs.
     */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
        	
        	System.out.println("authenticationnnn "+authentication);
        	
        	
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
            
            
            String jwt = Jwts.builder()
            		.setIssuer("Kapil")
            		.setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("role",getRole(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+ 30000000)) // expiration time of 8 hours
                    .signWith(key).compact();
            
            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
 
        
        }

        filterChain.doFilter(request, response);
    	
		
		
	}
	
	
	  private String getRole(Collection<? extends GrantedAuthority> collection) {
	        
	    String role="";
		  for(GrantedAuthority ga:collection) {
			  role= ga.getAuthority();
		  }
	    
		  return role;
	    }
		
	
		
		
		
	
	  /**
	     * Checks if the filter should not be applied to the request.
	     *
	     * @param request HTTP servlet request
	     * @return boolean indicating if the filter should not be applied
	     * @throws ServletException if an error occurs during filtering
	     */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	
        return !request.getServletPath().equals("/signIn");	
	}
	

}

