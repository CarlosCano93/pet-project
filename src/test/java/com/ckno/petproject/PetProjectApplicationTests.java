package com.ckno.petproject;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PetProjectApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void say_hello() throws Exception {
        mockMvc.perform(get("/v1/pokemon/hello/{name}", "ditto"))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().string("Hello ditto!"));
    }

    @Test
    void return_200_if_pokemon_exists() throws Exception {
        mockMvc.perform(get("/v1/pokemon/exists/{name}", "charmander"))
               .andExpect(status().is2xxSuccessful());
    }

    @Test
    void return_404_if_pokemon_does_not_exists() throws Exception {
        mockMvc.perform(get("/v1/pokemon/exists/{name}", "digimon"))
               .andExpect(status().isNotFound());
    }

    @Test
    void return_pokemon_if_exists() throws Exception {
        String jsonResponse = """
                {
                    "name": "charmander",
                    "type": "fire"
                }
                """;

        mockMvc.perform(get("/v1/pokemon/{name}", "charmander"))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonResponse));
    }
}