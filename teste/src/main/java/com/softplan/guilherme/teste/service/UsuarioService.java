package com.softplan.guilherme.teste.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.softplan.guilherme.teste.domain.Usuario;
import com.softplan.guilherme.teste.repository.UsuarioRepository;
import com.softplan.guilherme.teste.service.dto.UsuarioDTO;
import com.softplan.guilherme.teste.service.mapper.UsuarioMapper;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;
	
	public UsuarioDTO save(UsuarioDTO dto) {
		Usuario usuario = usuarioMapper.toEntity(dto);
		return usuarioMapper.toDto(usuarioRepository.save(usuario));
	}
	
	public UsuarioDTO findById(Long id) throws NotFoundException {
		return usuarioMapper.toDto(usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Registro não encontrado")));
	}
	
	public List<UsuarioDTO> findAll(){
		return usuarioMapper.toListDto(usuarioRepository.findAll());
	}
	
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

}
