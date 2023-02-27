package io.example.app.backend.integration.mapper;

import io.example.app.backend.integration.entity.User;
import io.example.app.backend.rest.model.user.UserRequest;
import io.example.app.backend.rest.model.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);

    User userRequestToUser(UserRequest userRequest);

}
