package com.softplan.guilherme.teste.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softplan.guilherme.teste.TesteApplication;
import com.softplan.guilherme.teste.configuration.H2TestProfileJPAConfig;
import com.softplan.guilherme.teste.service.UsuarioService;
import com.softplan.guilherme.teste.service.dto.UsuarioDTO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest(classes = {TesteApplication.class,
        H2TestProfileJPAConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioResourceTest {

    private static final String URL = "/api/usuario";

    @Autowired
    private MockMvc mockMvc;
    
    private UsuarioDTO dto;
    
    private UsuarioDTO initDTO() {
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
    	usuarioDTO.setNome("nome");
		return usuarioDTO;
    }
    
    @BeforeAll
    private void initTest() {
    	dto = initDTO();
    }

    
    private void createDTO() throws Exception {

        this.mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(asJsonString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("nome")))
                .andExpect(jsonPath("$.id", is(1)));
        
        List<UsuarioDTO> list = this.mockMvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(2)))
        .andExpect(jsonPath("$[0].nome", is("nomeNome")))
        .andExpect(jsonPath("$[0].email", is("emailEmail")));
        
        return list.get(0);
    }

    @Test
    public void alterar() throws Exception {

        UsuarioDTO usuarioDTO = createDTO();
        usuarioDTO.setNome("nome2");

        this.mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON).content(asJsonString(usuarioDTO)))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL + "/" + usuarioDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(usuarioDTO.getId())))
                .andExpect(jsonPath("$.nome", is(usuarioDTO.getNome())));
    }

    @Test
    public void excluir() throws Exception {

        UsuarioDTO usuarioDTO = createDTO();

        this.mockMvc.perform(delete(URL + "/" + usuarioDTO.getId()))
                .andExpect(status().isOk());

        this.mockMvc.perform(get(URL + "/" + usuarioDTO.getId()))
                .andExpect(status().isNotFound());

    }
}