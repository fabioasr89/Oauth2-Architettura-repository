package com.fabiopetricola.client.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class LibroBean implements Serializable{

	private static final long serialVersionUID = -7374106767320491040L;
	
	
	private String isbn;
	
	private String titolo;
	
	private Integer anno;
	
	private String genere;
	
	public LibroBean() {
		super();
	}

	private enum GenereEnum{
		
		GENERE_THRILLER(1,"Thriller"),
		GENERE_HORROR(2,"Horror"),
		GENERE_STORICO(3,"Storico"),
		GENERE_FANTASY(4,"Fantasy");
		
		public Integer id;
		public String genere;
		
		private GenereEnum(Integer id,String genere) {
			this.id=id;
			this.genere=genere;
		}
		
		
		
		
	}
	
	public LibroBean(String titolo,Integer anno,Integer idGenere) {
		this.titolo=titolo;
		this.anno=anno;
		for(GenereEnum gen:GenereEnum.values()) {
			if(idGenere.equals(gen.id)) {
				this.genere=gen.genere;
				break;
			}
		}
		
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	
}
