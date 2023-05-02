package com.fabiopetricola.resources.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

public class UserDetailsCustom implements UserDetails{
	
	private String username;
	private List<String> ruoli;
	
	public UserDetailsCustom(String username) {
		this.username=username;
	}

	private enum RuoliEnum{
		LIBRI_READ(1,"libri_read"),
		LIBRI_WRITE(2,"libri_write");
		
		private String descrizione;
		private Integer id;
		
		
		private RuoliEnum(Integer id,String descrizione) {
			this.id=id;
			this.descrizione=descrizione;
		}
		

	}
	
	public void setRuoli(List<String> ruoli) {
		this.ruoli=ruoli;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> listaAutorizzazioni=null;
		if(!CollectionUtils.isEmpty(ruoli)) {
			listaAutorizzazioni=new ArrayList<GrantedAuthority>();
			for(RuoliEnum r:RuoliEnum.values()) {
				listaAutorizzazioni.add(new SimpleGrantedAuthority(r.descrizione));
			}
		}
		return listaAutorizzazioni;
	}

	@Override
	public String getPassword() {
		
		return null;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
}
