package com.fabiopetricola.resources.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormDataEncoder {
	
	@JsonProperty(value = "token")
	private String token;
	
	
	
	public FormDataEncoder(String accessToken) {
		this.token=accessToken;
		
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}

	


	
	
}
