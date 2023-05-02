package com.fabiopetricola.resources.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.fabiopetricola.resources.bean.LibroBean;
import com.fabiopetricola.resources.mapper.LibriMapper;

@Service
public class LibroServiceImpl implements LibroService{

	@Override
	public LibroBean[] libriBean() {
		
		return LibriMapper.libriRandom();
	}

}
