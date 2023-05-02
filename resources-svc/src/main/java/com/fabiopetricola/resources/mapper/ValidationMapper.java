package com.fabiopetricola.resources.mapper;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.fabiopetricola.resources.bean.FormDataEncoder;
import com.fabiopetricola.resources.exception.AuthException;

public final class ValidationMapper {
	
	private ValidationMapper() throws OperationNotSupportedException {
		throw new OperationNotSupportedException("Questa classe non può essere istanziata");
	}
	
	
	public static void validateFormDatar(FormDataEncoder formData) throws AuthException,IllegalArgumentException{
		if(formData==null) {
			throw new AuthException("Request non valorizzata");
		}
		//Assert.isTrue(StringUtils.isNotBlank(formData.getIdClient()),"IdClient non valorizzato");
		//Assert.isTrue(StringUtils.isNotBlank(formData.getClientSecret()),"Client secret non valorizzato");
		Assert.isTrue(StringUtils.isNotBlank(formData.getToken()),"AccessToken non valorizzato");

		
	}
}
