package com.ckno.petproject;

import com.ckno.petproject.application.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AcceptancePetProjectApplication {

    @Autowired
    private MockMvc mockMvc;
    public static final UserDto USER = UserDto.builder()
            .name("Carlos")
            .password("carlos01")
            .build();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void say_hello_pet() throws Exception {
        this.mockMvc.perform(get("/v1/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .equals("Hello Pet");
    }

    @Test
    void login_return_unauthorized_if_is_not_in_db() throws Exception {
        String userJson = objectMapper.writeValueAsString(USER);

        this.mockMvc.perform(
                post("/v1/login/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
