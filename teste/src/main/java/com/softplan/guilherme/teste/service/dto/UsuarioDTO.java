package com.softplan.guilherme.teste.service.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;

}
