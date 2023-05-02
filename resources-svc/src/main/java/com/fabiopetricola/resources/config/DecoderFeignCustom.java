package com.fabiopetricola.resources.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;


public class DecoderFeignCustom {
	
	 	@Bean
	    public Encoder encoder(ObjectFactory<HttpMessageConverters> converters) {
	        return new SpringFormEncoder(new SpringEncoder(converters));
	   }
	 	
	 	  @Bean
	 	    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
	 	        return new BasicAuthRequestInterceptor("libri-oidc", "secret");
	 	    }
}
