package com.fabiopetricola.resources.mapper;

import java.util.Random;

import javax.naming.OperationNotSupportedException;

import com.fabiopetricola.resources.bean.LibroBean;

public final class LibriMapper {
	

	private LibriMapper() throws OperationNotSupportedException {
		throw new OperationNotSupportedException("La classe non può essere istanziata");
	}
	
	
	public static LibroBean[] libriRandom(){
		LibroBean[] libri=new LibroBean[15];
		Random rand=new Random();
		for(int i=0;i<15;i++) {
			LibroBean libro=new LibroBean("Titolo"+i,(rand.nextInt(100)+1900),rand.nextInt(3)+1);
			libri[i]=libro;
		}
		return libri;
	}
}
