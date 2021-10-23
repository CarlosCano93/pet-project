package com.ckno.petproject;

import com.ckno.petproject.urlshortener.HashGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UrlShortenerE2ETests {

    private static final String HASH = "asd-qwe-zxc";
    private static final String GOOGLE_URL = "https://www.google.com";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HashGenerator hashGenerator;

    @Test
    void return_200_and_hash_if_url_hash_created() throws Exception {
        String jsonResponse = """
                {
                    "hash": %s
                }
                """.formatted(HASH);

        when(hashGenerator.newHash()).thenReturn(HASH);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/shortener?url=" + GOOGLE_URL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    void redirect_to_the_saved_url() throws Exception {
        when(hashGenerator.newHash()).thenReturn(HASH);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/shortener?url=" + GOOGLE_URL));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/shortener?hash=" + HASH))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl(GOOGLE_URL));
    }
}
