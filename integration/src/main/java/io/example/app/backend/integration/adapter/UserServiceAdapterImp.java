package io.example.app.backend.integration.adapter;

import io.example.app.backend.integration.entity.User;
import io.example.app.backend.integration.mapper.UserMapper;
import io.example.app.backend.integration.service.UserService;
import io.example.app.backend.rest.model.user.UserRequest;
import io.example.app.backend.rest.model.user.UserResponse;
import io.example.app.backend.rest.service.adapter.UserServiceAdapter;
import org.springframework.stereotype.Service;

@Service
public class UserServiceAdapterImp implements UserServiceAdapter {

    private final UserService service;

    private final UserMapper mapper;

    public UserServiceAdapterImp(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User  user = service.save(mapper.userRequestToUser(userRequest));

        return mapper.userToUserResponse(user);
    }

    @Override
    public void deleteUser(Integer id) {
        service.delete(id);
    }
}