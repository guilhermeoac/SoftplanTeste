package com.softplan.guilherme.teste.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.guilherme.teste.service.UsuarioService;
import com.softplan.guilherme.teste.service.dto.UsuarioDTO;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException{
    	UsuarioDTO result = usuarioService.save(usuarioDTO);
    	return ResponseEntity.created(new URI("/api/usuario")).body(result);
    }

    @PutMapping()
    public ResponseEntity<UsuarioDTO> alterar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.save(usuarioDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok().body(usuarioService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteDigitoUnicoById(@PathVariable Long id){
        usuarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
