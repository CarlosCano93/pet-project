package com.ckno.petproject.domain;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.entity.User;
import com.ckno.petproject.domain.port.LoginClientPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LoginServiceShould {

    private static final String PASSWORD = "carlos01";
    private static final String NAME = "Carlos";
    private static final User USER = User.builder()
            .name(NAME)
            .password(PASSWORD).build();
    private static final UserDto USER_DTO = UserDto.builder()
            .name(NAME)
            .password(PASSWORD)
            .build();

    @Mock
    private LoginClientPort loginClient;

    @InjectMocks
    private LoginService loginService;

    @Test
    void call_login_and_throw_exception_if_doest_find_user() {
        assertThrows(NoSuchElementException.class,
                () -> loginService.login(USER_DTO));
    }

    @Test
    void call_login_and_return_user() {
        given(loginClient.login(USER_DTO.getName(), USER_DTO.getPassword()))
                .willReturn(Optional.of(USER));

        assertThat(loginService.login(USER_DTO))
                .isEqualTo(USER);
    }
}