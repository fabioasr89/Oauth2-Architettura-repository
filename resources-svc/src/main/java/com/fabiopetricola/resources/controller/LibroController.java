package com.fabiopetricola.resources.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabiopetricola.resources.bean.LibroBean;
import com.fabiopetricola.resources.service.LibroService;

@RestController
@RequestMapping("/services/libri")
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping("/lista")
	public LibroBean[] libri(){
		LibroBean[] libri=libroService.libriBean();
		
		return libri;
	}
}
