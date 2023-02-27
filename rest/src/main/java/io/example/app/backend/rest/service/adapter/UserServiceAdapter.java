package io.example.app.backend.rest.service.adapter;

import io.example.app.backend.rest.model.user.UserRequest;
import io.example.app.backend.rest.model.user.UserResponse;

public interface UserServiceAdapter {

    UserResponse createUser(UserRequest userRequest);

    void deleteUser(Integer id);
}
