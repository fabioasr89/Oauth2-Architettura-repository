package com.fabiopetricola.resources.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fabiopetricola.resources.bean.FormDataEncoder;
import com.fabiopetricola.resources.bean.IntrospectionBean;
import com.fabiopetricola.resources.config.DecoderFeignCustom;

import feign.Headers;

@FeignClient(name="oauth-feign",url="${spring.security.oauth2.resourceserver.jwt.issuer-uri}",configuration = DecoderFeignCustom.class)
public interface AuthServerFeign {
	
	
	@PostMapping(value="/oauth2/introspect",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@Headers("Content-Type: application/x-www-form-urlencoded")
	ResponseEntity<IntrospectionBean> introspect(@RequestBody FormDataEncoder formData);
}
