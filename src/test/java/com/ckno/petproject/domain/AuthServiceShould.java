package com.ckno.petproject.domain;

import com.ckno.petproject.adapters.entity.UserEntity;
import com.ckno.petproject.domain.model.User;
import com.ckno.petproject.domain.port.AuthClientPort;
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
public class AuthServiceShould {

    private static final String PASSWORD = "carlos01";
    private static final String NAME = "Carlos";
    private static final UserEntity USER_ENTITY = UserEntity.builder()
            .name(NAME)
            .password(PASSWORD).build();
    private static final User USER = User.builder()
            .name(NAME)
            .password(PASSWORD)
            .build();

    @Mock
    private AuthClientPort loginClient;

    @InjectMocks
    private AuthService authService;

    @Test
    void call_login_and_throw_exception_if_doest_find_user() {
        assertThrows(NoSuchElementException.class, () -> authService.login(USER));
    }

    @Test
    void call_login_and_return_user() {
        given(loginClient.login(USER)).willReturn(Optional.of(USER_ENTITY));

        assertThat(authService.login(USER)).isEqualTo(USER_ENTITY);
    }

    @Test
    void call_sign_up_and_return_new_user() {
        given(loginClient.signUp(USER)).willReturn(USER_ENTITY);

        assertThat(authService.signUp(USER)).isEqualTo(USER_ENTITY);
    }
}