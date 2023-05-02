package com.fabiopetricola.resources.service;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fabiopetricola.resources.bean.FormDataEncoder;
import com.fabiopetricola.resources.bean.IntrospectionBean;
import com.fabiopetricola.resources.exception.AuthException;
import com.fabiopetricola.resources.feign.AuthServerFeign;
import com.fabiopetricola.resources.mapper.ValidationMapper;
@Service
public class AuthServiceImpl implements AuthService{
	
	
	@Autowired
	private AuthServerFeign authServerFeign;
	
	
	@Override
	public IntrospectionBean introspect(FormDataEncoder formData) throws AuthException {
		IntrospectionBean introspect=null;
		ValidationMapper.validateFormDatar(formData);
		ResponseEntity<IntrospectionBean> response=authServerFeign.introspect(formData);
		if(response.getStatusCode().is2xxSuccessful() && response.getBody()!=null) {
			return response.getBody();
		}
		throw new AuthException("Si è verificato un errore durante la chiamata di introspezione");
	}
	
	
	
	

}
