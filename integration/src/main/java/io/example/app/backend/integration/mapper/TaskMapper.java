package io.example.app.backend.integration.mapper;

import io.example.app.backend.integration.entity.Task;
import io.example.app.backend.rest.model.task.TaskRequest;
import io.example.app.backend.rest.model.task.TaskResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskResponse taskToTaskResponse(Task task);

    Task taskRequestToTask(TaskRequest taskRequest);

    @AfterMapping
    default void afterTaskToTaskResponseMapping(@MappingTarget TaskResponse taskResponse) {
        taskResponse.addLink();
    }
}
