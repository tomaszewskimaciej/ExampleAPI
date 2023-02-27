package io.example.app.backend.rest.service;

import io.example.app.backend.rest.model.user.UserRequest;
import io.example.app.backend.rest.model.user.UserResponse;
import io.example.app.backend.rest.service.adapter.UserServiceAdapter;
import org.springframework.stereotype.Service;

@Service
public class UserRestService {
    private final UserServiceAdapter adapter;

    public UserRestService(UserServiceAdapter adapter) {
        this.adapter = adapter;
    }

    public UserResponse createUser(UserRequest userRequest){
        return adapter.createUser(userRequest);
    }

    public void deleteUser(Integer id){
        adapter.deleteUser(id);
    }
}
