package com.fabiopetricola.resources.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.fabiopetricola.resources.bean.TokenBearerBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public final class TokenMapper {
	
	
	public static TokenBearerBean converterAccesstokenMapper(String token) throws JsonSyntaxException ,IllegalArgumentException{
		Assert.isTrue(StringUtils.isNotEmpty(token),"Token non presente");
		Gson json=new Gson();
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String[] blocchi= token.split("\\.");
		String header = new String(decoder.decode(blocchi[0]));
		String payload = new String(decoder.decode(blocchi[1]));
		
		return json.fromJson(payload,TokenBearerBean.class);
	}
	
	
	public static List<String> recuperaAutorizzazionidalToken(String[] scope){
		List<String> autorizzazioniList=new ArrayList<String>();
		if(scope!=null && scope.length>0) {
			for(String s:scope) {
				autorizzazioniList.add(s);
			}
		}
		return autorizzazioniList;
	}
}
