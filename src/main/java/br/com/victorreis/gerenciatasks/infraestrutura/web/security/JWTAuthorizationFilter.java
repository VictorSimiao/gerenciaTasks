package br.com.victorreis.gerenciatasks.infraestrutura.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//Pegando a inform��o do header
		String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
		
		if (token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			UsernamePasswordAuthenticationToken authentication = getAuthentcation(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(request, response);
	}
	
	//Extraindo informa��es do token
	private UsernamePasswordAuthenticationToken getAuthentcation(String token) {
		String username = Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY) //decodificando o token
			.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, "")) //tirando o Bearer : s� sobra o token
			.getBody().getSubject(); //pegando o username
		
		if (username != null) {
			return new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.NO_AUTHORITIES);
		}
		
		return null;
	}

}
