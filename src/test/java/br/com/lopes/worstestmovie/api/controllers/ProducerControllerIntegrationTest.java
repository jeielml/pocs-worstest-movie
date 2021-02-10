package br.com.lopes.worstestmovie.api.controllers;

import br.com.lopes.worstestmovie.WorstestMovieApplicationTests;
import org.junit.Before;
import org.junit.Test;
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

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getProducer1WithStatusCode200() throws Exception {
        String jsonContent = "{\"id\":1,\"name\":\"Producer 1\",\"wins\":[{\"id\":5,\"year\":1900},{\"id\":6,\"year\":1999},{\"id\":1,\"year\":2008},{\"id\":2,\"year\":2009}]}";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
    }

    @Test
    public void shouldReturnNotFoundWhenProducerNotExists() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getTopAndTailProducersOnAwardsWithStatusCode200() throws Exception {
        final String jsonContent = "{\"min\":[{\"producer\":\"Producer 1\",\"interval\":1,\"previousWin\":2008,\"followingWin\":2009},{\"producer\":\"Producer 2\",\"interval\":1,\"previousWin\":2018,\"followingWin\":2019}],\"max\":[{\"producer\":\"Producer 1\",\"interval\":99,\"previousWin\":1900,\"followingWin\":1999}]}j";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/winners/intervals/top-tail-awards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
    }

}