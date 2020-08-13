package com.softplan.guilherme.teste.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.softplan.guilherme.teste.domain.Usuario;
import com.softplan.guilherme.teste.service.dto.UsuarioDTO;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO usuarioDTO);

    UsuarioDTO toDto(Usuario usuario);

    List<UsuarioDTO> toListDto(List<Usuario> usuario);

}
