package io.example.app.backend.rest.controller;

import io.example.app.backend.rest.controller.version.RestApiVersion;
import io.example.app.backend.rest.model.user.UserRequest;
import io.example.app.backend.rest.model.user.UserResponse;
import io.example.app.backend.rest.service.UserRestService;
import io.example.app.backend.rest.utility.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
@ContextConfiguration(classes = UserController.class)
class UserControllerTest extends BaseUnitTest {
    @MockBean
    private UserRestService service;

    @Test
    public void shouldReturnUser() throws Exception {
        //given
        UserResponse expected = exampleUserResponse();
        given(service.getUserById(any())).willReturn(expected);
        MockHttpServletRequestBuilder mockHttp = get(RestApiVersion.version + "/user/1")
                .contentType(MediaType.APPLICATION_JSON);
        //when
        UserResponse actual = jsonToObject(callApi(mockHttp, HttpStatus.OK), UserResponse.class);
        //then
        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getFirstName(), actual.getFirstName()),
                () -> assertEquals(expected.getLastName(), actual.getLastName()),
                () -> assertEquals(expected.getEmail(), actual.getEmail())
                );
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //given
        UserResponse expected = exampleUserResponse();
        given(service.createUser(any())).willReturn(expected);
        MockHttpServletRequestBuilder mockHttp = post(RestApiVersion.version + "/user")
                .content(jsonToString(expected))
                .contentType(MediaType.APPLICATION_JSON);
        //when
        UserResponse actual = jsonToObject(callApi(mockHttp, HttpStatus.OK), UserResponse.class);
        //then
        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getFirstName(), actual.getFirstName()),
                () -> assertEquals(expected.getLastName(), actual.getLastName()),
                () -> assertEquals(expected.getEmail(), actual.getEmail())
        );
    }

    @Test
    public void ShouldReturnOkStatusAfterCreatingUser() throws Exception{
        UserResponse expected = exampleUserResponse();
        given(service.createUser(any())).willReturn(expected);
        MockHttpServletRequestBuilder mockHttp = post(RestApiVersion.version + "/user")
                .content(jsonToString(expected))
                .contentType(MediaType.APPLICATION_JSON);

        callApi(mockHttp,HttpStatus.OK);
    }



    @Test
    public void shouldDeleteUser() throws Exception {
        //given
        Integer id = 1;
        //when
        String response = callApi(delete(RestApiVersion.version +"/user/"+id), HttpStatus.NO_CONTENT);
        //then
        verify(service, times(1)).deleteUser(id);
        assertEquals("", response);
    }

    public static UserResponse exampleUserResponse() {
        return new UserResponse(1, "Maciek", "Tom", "MaciekTom@gmail.com");
    }
}