package io.example.app.backend.rest.service.adapter;

import io.example.app.backend.rest.model.user.UserRequest;
import io.example.app.backend.rest.model.user.UserResponse;

import java.util.List;

public interface UserServiceAdapter {

    List<UserResponse> searchUsers(String search);

    UserResponse createUser(UserRequest userRequest);

    void deleteUser(Integer id);
}
