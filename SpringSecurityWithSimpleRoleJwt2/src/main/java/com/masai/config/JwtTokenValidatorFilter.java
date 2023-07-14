package com.masai.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter class to validate JWT token for authenticated requests.
 */
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

	/**
     * Filters the incoming request to validate the JWT token and set the authentication in the SecurityContext.
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
	
		
		String jwt= request.getHeader(SecurityConstants.JWT_HEADER);

		
		if(jwt != null) {
						
			try {

				jwt = jwt.substring(7);

				
				SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
				
				

				Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				
				
				String username= String.valueOf(claims.get("username"));
				
				
				String role= (String)claims.get("role");
				
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(role));
				
				Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);

				
				 
				
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid Token received..");
			}
			
			
			
		}
		
		filterChain.doFilter(request, response);
		
		
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
	
		return request.getServletPath().equals("/signIn");
	}

}
