package io.example.app.backend.rest.service;

import io.example.app.backend.rest.model.user.UserResponse;
import io.example.app.backend.rest.service.adapter.UserServiceAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserRestServiceTest {

    @Mock
    private UserServiceAdapter adapter;

    @InjectMocks
    private UserRestService service;

    @Test
    public void shouldReturnUser() {
        //given
        UserResponse expected = exampleUserResponse();
        given(adapter.getUserById(any())).willReturn(expected);
        //when
        final UserResponse actual = service.getUserById(any());
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateUser() {
        //given
        UserResponse expected = exampleUserResponse();
        given(adapter.createUser(any())).willReturn(expected);
        //when
        final UserResponse actual = service.createUser(any());
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCallAdapterDeleteUser() {
        //given
        Integer id = 1;
        //when
        service.deleteUser(id);
        //then
        verify(adapter, times(1)).deleteUser(id);
    }



    public static UserResponse exampleUserResponse() {
        return new UserResponse(1, "Maciek", "Tom", "MaciekTom@gmail.com");
    }
}