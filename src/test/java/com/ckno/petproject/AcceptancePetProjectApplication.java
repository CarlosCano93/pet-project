package com.ckno.petproject;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.entity.User;
import com.ckno.petproject.adapters.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AcceptancePetProjectApplication {
    private static final String NAME_CARLOS = "Carlos";
    private static final String PSW_CARLOS_01 = "carlos01";
    private static final String NAME_CANO = "Cano";
    private static final String PSW_CANO_01 = "cano01";
    private static final String NAME_PEPE = "Pepe";
    private static final String PSW_PEPE_01 = "pepe01";

    private static final User EXISITNG_USER = User.builder()
            .name(NAME_CARLOS)
            .password(PSW_CARLOS_01)
            .build();
    private static final UserDto EXISTING_USER_DTO = UserDto.builder()
            .name(NAME_CARLOS)
            .password(PSW_CARLOS_01)
            .build();

    private static final UserDto NOT_REGISTERED_USER_DTO = UserDto.builder()
            .name(NAME_CANO)
            .password(PSW_CANO_01)
            .build();

    private static final UserDto SIGN_UP_USER_DTO = UserDto.builder()
            .name(NAME_PEPE)
            .password(PSW_PEPE_01)
            .build();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void context_load() {
    }

    @BeforeEach
    void setUp() {
        userRepository.save(EXISITNG_USER);
    }

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
    void login_return_not_found_if_is_not_in_db() throws Exception {
        String userJson = objectMapper.writeValueAsString(NOT_REGISTERED_USER_DTO);

        this.mockMvc.perform(
                post("/v1/login/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void login_return_user() throws Exception {
        String userJson = objectMapper.writeValueAsString(EXISTING_USER_DTO);

        this.mockMvc.perform(
                post("/v1/login/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void sign_up_save_user_and_return_it() throws Exception {
        String userJson = objectMapper.writeValueAsString(SIGN_UP_USER_DTO);

        this.mockMvc.perform(
                post("/v1/signup/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }
}
