package br.com.lopes.worstestmovie.api.controllers;

import br.com.lopes.worstestmovie.WorstestMovieApplicationTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ProducerControllerIntegrationTest extends WorstestMovieApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ProducerController controller;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getProducer1WithStatusCode200() throws Exception {
        String jsonContent = "{\"id\":1,\"name\":\"Allan Carr\",\"wins\":[{\"id\":1,\"year\":1980}]}";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
    }

    @Test
    public void shouldReturnNotFoundWhenProducerNotExists() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/500")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getTopAndTailProducersOnAwardsWithStatusCode200() throws Exception {
        final String jsonContent = "{\"min\":[{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991}],\"max\":[{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}]}";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/winners/intervals/top-tail-awards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
    }

}