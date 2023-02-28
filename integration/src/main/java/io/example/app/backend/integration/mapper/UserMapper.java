package io.example.app.backend.integration.mapper;

import io.example.app.backend.integration.entity.User;
import io.example.app.backend.rest.model.user.UserRequest;
import io.example.app.backend.rest.model.user.UserResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);

    User userRequestToUser(UserRequest userRequest);

    @AfterMapping
    default void afterUserToUserResponseMapping(@MappingTarget UserResponse userResponse) {
        userResponse.addLink();
    }
}