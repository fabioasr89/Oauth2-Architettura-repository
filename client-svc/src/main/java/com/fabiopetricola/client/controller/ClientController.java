package com.fabiopetricola.client.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fabiopetricola.client.bean.LibroBean;


@RestController
public class ClientController {
	
	
	private WebClient webClient;
	
	@GetMapping("/api")
	public ResponseEntity<List<LibroBean>> libri(@RegisteredOAuth2AuthorizedClient("libri-oidc-authorization-code") OAuth2AuthorizedClient authorizedClient){
		System.out.println("Chiamata client inizio...");
		LibroBean[] libri=null;
		try {
			webClient=WebClient.create();
			libri=webClient.get().
					uri("http://localhost:8090/services/libri/lista")
					.header(HttpHeaders.AUTHORIZATION, authorizedClient.getAccessToken().getTokenValue())
					.retrieve().
					bodyToMono(LibroBean[].class).block();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<List<LibroBean>>(Arrays.asList(libri), HttpStatus.OK);

				
	}
	

}
