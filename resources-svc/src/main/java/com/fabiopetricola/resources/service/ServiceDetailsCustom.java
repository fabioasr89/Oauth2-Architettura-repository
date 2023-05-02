package com.fabiopetricola.resources.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fabiopetricola.resources.bean.UserDetailsCustom;

@Service
public class ServiceDetailsCustom implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(StringUtils.isNotBlank(username)) {
			if(username.equalsIgnoreCase("fabio")) {
				UserDetails user=new UserDetailsCustom(username);
				return user;
			}
		}
		
		throw new UsernameNotFoundException("Username non valorizzato o non autorizzato ad accedere. Username:"+username);
	}

}
