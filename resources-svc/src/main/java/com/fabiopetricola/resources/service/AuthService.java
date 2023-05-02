package com.fabiopetricola.resources.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.fabiopetricola.resources.bean.FormDataEncoder;
import com.fabiopetricola.resources.bean.IntrospectionBean;
import com.fabiopetricola.resources.exception.AuthException;

import feign.form.FormData;


public interface AuthService {
	
	IntrospectionBean introspect(FormDataEncoder formData) throws AuthException;
}
