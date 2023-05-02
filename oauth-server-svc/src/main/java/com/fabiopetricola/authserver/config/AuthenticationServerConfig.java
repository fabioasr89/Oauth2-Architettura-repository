package com.fabiopetricola.authserver.config;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;

import com.fabiopetricola.authserver.token.TokenUtils;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class AuthenticationServerConfig {
	
	@Value("${authentication.server.issue}")
	private String issue;
	/*
	@Value("${authentication.server.redirectUri}")
	private String redirectUri;*/
	


	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain securityOauthFilterChain(HttpSecurity httpSecurity) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
		return httpSecurity.formLogin(Customizer.withDefaults()).build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
	    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}
	
	@Bean
	public RegisteredClientRepository registerdClientSVC() {
		RegisteredClient rc=RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("libri-oidc")
				.clientSecret("{noop}secret")
				.redirectUri("http://127.0.0.1:8081/login/oauth2/code/libri-oidc") //qua recupero il codice di autorizzazione con le grant personalizzate
				.redirectUri("http://127.0.0.1:8081/api") //dalla pagina precedente, chiamo il servizio passando il codice di autorizzazione recuperato con la chiamata precedente
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
				.scope(OidcScopes.OPENID)
				.scope("libri.read")
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true)
						.build()).build();
		return new InMemoryRegisteredClientRepository(rc);
	}
	
	@Bean
	public ProviderSettings providerSettings() {
		return ProviderSettings.builder().issuer(issue).build();
	}

	
	///token
	//firmiamo i token  con le chiavi generate
	@Bean
	@Qualifier("jwkSource")
	public JWKSource<SecurityContext> jwkSourceSecurityContext() throws NoSuchAlgorithmException{
		RSAKey rsaKey=TokenUtils.firmaTokenConChiaviRSA();
		JWKSet jwkSet=new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}
	
	
}
