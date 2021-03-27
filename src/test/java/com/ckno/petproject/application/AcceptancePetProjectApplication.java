package com.ckno.petproject.application;

import com.ckno.petproject.api.dto.UserDto;
import com.ckno.petproject.adapters.users.entity.UserEntity;
import com.ckno.petproject.adapters.users.UserRepository;
import com.ckno.petproject.domain.model.User;
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

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AcceptancePetProjectApplication {
    private static final String NAME = "ccano";
    private static final String PASSWORD = "ccano01";

    private static final UserEntity USER_ENTITY = UserEntity.builder()
            .name(NAME)
            .password(PASSWORD)
            .build();
    private static final UserDto USER_DTO = UserDto.builder()
            .name(NAME)
            .password(PASSWORD)
            .build();


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void context_load() {
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
        String userJson = objectMapper.writeValueAsString(User.builder().build());

        this.mockMvc.perform(
                post("/v1/login/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void login_return_bad_request_if_the_request_body_is_wrong() throws Exception {
        this.mockMvc.perform(
                post("/v1/login/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("someBody"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void login_return_user() throws Exception {
        userRepository.save(USER_ENTITY);
        String userJson = objectMapper.writeValueAsString(USER_DTO);

        this.mockMvc.perform(
                post("/v1/login/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void sign_up_save_user_and_return_it() throws Exception {
        String userJson = objectMapper.writeValueAsString(USER_DTO);

        this.mockMvc.perform(
                post("/v1/signup/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }
}
