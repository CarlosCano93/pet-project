package com.ckno.petproject.domain;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.entity.User;
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
public class UserServiceShould {

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
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void call_login_and_throw_exception_if_doest_find_user() {
        assertThrows(NoSuchElementException.class,
                () -> userService.login(UserDto.builder().build()));
    }

    @Test
    void call_login_and_return_user() {
        given(userRepository.findByNameAndPassword(USER_DTO.getName(), USER_DTO.getPassword()))
                .willReturn(Optional.of(USER));

        assertThat(userService.login(USER_DTO))
                .isEqualTo(USER);
    }
}