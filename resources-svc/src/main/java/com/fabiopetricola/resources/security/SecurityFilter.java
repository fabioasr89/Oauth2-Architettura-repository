package com.fabiopetricola.resources.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fabiopetricola.resources.bean.FormDataEncoder;
import com.fabiopetricola.resources.bean.IntrospectionBean;
import com.fabiopetricola.resources.bean.TokenBearerBean;
import com.fabiopetricola.resources.bean.UserDetailsCustom;
import com.fabiopetricola.resources.mapper.TokenMapper;
import com.fabiopetricola.resources.service.AuthService;
import com.fabiopetricola.resources.service.ServiceDetailsCustom;

public class SecurityFilter extends OncePerRequestFilter{
	
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private ServiceDetailsCustom serviceDetailsCustom;
	
	private static final Logger logger=LoggerFactory.getLogger(SecurityFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest httpReq=(HttpServletRequest)request;
		Enumeration<String> headerName=httpReq.getHeaderNames();
		String tokenB64=null;
		if (headerName != null) {
            while (headerName.hasMoreElements()) {
            		String h=headerName.nextElement();
            		if(h.equalsIgnoreCase("authorization")) {
            			tokenB64=(String)httpReq.getHeader(h);
            			break;
            		}
                    
            }
            if(StringUtils.isNotBlank(tokenB64)) {
            	try {
            		TokenBearerBean tokenBearer=TokenMapper.converterAccesstokenMapper(tokenB64);
            		FormDataEncoder formData=new FormDataEncoder(tokenB64);
            		IntrospectionBean introspection=authService.introspect(formData);
            		if(introspection.isActive()) {
            			//il token è valido
            			UserDetailsCustom user=(UserDetailsCustom)serviceDetailsCustom.loadUserByUsername(tokenBearer.getSub());
            			logger.debug("User recuperato:"+user.getUsername());
            			//recupero le autorizzazioni dal token
            			user.setRuoli(TokenMapper.recuperaAutorizzazionidalToken(tokenBearer.getScope()));
            			logger.debug("Autorizzazioni recuperate dal token:"+String.valueOf(Arrays.asList(user.getAuthorities())));
            			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
   					 	authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
   					 	SecurityContextHolder.getContext().setAuthentication(authentication);
            		}
            	}catch(Exception e) {
            		logger.error("Errore durante la fase di profilazione utente",e);
            	}
            	
            	
            }
		}
		
		filterChain.doFilter(request, response);;
	}

}
