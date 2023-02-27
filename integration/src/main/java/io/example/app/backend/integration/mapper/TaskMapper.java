package io.example.app.backend.integration.mapper;

import io.example.app.backend.integration.entity.Task;
import io.example.app.backend.integration.entity.User;
import io.example.app.backend.rest.model.task.TaskRequest;
import io.example.app.backend.rest.model.task.TaskResponse;
import io.example.app.backend.rest.model.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);


    TaskResponse taskToTaskResponse(Task task);

    Task taskRequestToTask(TaskRequest taskRequest);
}
