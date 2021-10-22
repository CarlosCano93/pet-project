package com.ckno.petproject;


import com.ckno.petproject.pokemon.PokemonController;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {WireMockConfiguration.class})
class PetProjectApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WireMockServer mockService;

    @BeforeEach
    void setUp() throws IOException {
        try (var fis = new FileInputStream("src/test/resources/pokeapi-response.json")) {
            mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/pokeapi/pokemon/charmander"))
                    .willReturn(WireMock.aResponse()
                            .withStatus(HttpStatus.OK.value())
                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            .withBody(StreamUtils.copyToString(fis, StandardCharsets.UTF_8))));
        }
    }

    @Test
    void return_200_if_pokemon_exists() throws Exception {
        mockMvc.perform(get("/v1/pokemon/{name}/exists", "charmander"))
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

@TestConfiguration
class WireMockConfiguration {
    @Autowired
    private WireMockServer wireMockServer;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer pokeApiMock() {
        return new WireMockServer(9090);
    }
}