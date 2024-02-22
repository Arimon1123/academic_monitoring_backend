package com.example.backend_academic_monitoring.Config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {


	private final JwtUtil jwtTokenUtil;

	@Value("${jwt.accesTokenCookieName}")
	private String cookieName;
	public static final Logger LOGGER = LoggerFactory.getLogger(CustomJwtAuthenticationFilter.class);
	@Autowired
	public CustomJwtAuthenticationFilter(JwtUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		 try{
			String jwtToken = extractJwtFromRequest(request);
			if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
				UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwtToken), "",
						jwtTokenUtil.getRolesFromToken(jwtToken));
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				LOGGER.info("{}",usernamePasswordAuthenticationToken);
			} else {
				System.out.println("Cannot set the Security Context");
			}
		 }catch(ExpiredJwtException ex)
		 {
			 String isRefreshToken = request.getHeader("isRefreshToken");
				String requestURL = request.getRequestURL().toString();
				if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshToken")) {
					allowForRefreshToken(ex, request);
				} else
					request.setAttribute("exception", ex);
		 }
		 catch(BadCredentialsException ex)
		 {
			 request.setAttribute("exception", ex);
		 }
		chain.doFilter(request, response);
	}
	
	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		request.setAttribute("claims", ex.getClaims());

	}

	private String extractJwtFromRequest(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request,cookieName);
//		String bearerToken = request.getHeader("Authorization");
//		System.out.println(bearerToken);
//		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//			return bearerToken.substring(7, bearerToken.length());
//		}
//		return null;
		return cookie != null ? cookie.getValue() : null;
	}

}