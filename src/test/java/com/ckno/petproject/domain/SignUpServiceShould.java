package com.ckno.petproject.domain;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.entity.User;
import com.ckno.petproject.domain.port.SignUpClientPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SignUpServiceShould {
    private static final String NAME_CARLOS = "Carlos";
    private static final String PASSWORD_CARLOS01 = "carlos01";

    private static final User USER = User.builder()
            .name(NAME_CARLOS)
            .password(PASSWORD_CARLOS01)
            .build();

    private static final UserDto USER_DTO = UserDto.builder()
            .name(NAME_CARLOS)
            .password(PASSWORD_CARLOS01)
            .build();

    @Mock
    private SignUpClientPort signUpClient;

    @InjectMocks
    private SingUpService signUpService;

    @Test
    void call_sign_up_and_return_new_user() {
        given(signUpClient.signUp(USER))
                .willReturn(USER);

        assertThat(signUpService.signUp(USER_DTO))
                .isEqualTo(USER);
    }
}
