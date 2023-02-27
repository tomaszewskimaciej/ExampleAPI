package io.example.app.backend.rest.service.adapter;

import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.rest.model.task.TaskRequest;
import io.example.app.backend.rest.model.task.TaskResponse;

public interface TaskServiceAdapter {

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse updateTask(TaskRequest taskRequest);

    void deleteTask(Integer id);

    TaskResponse changeStatus(Integer id, TaskStatusType statusType);

    TaskResponse assignUser(Integer taskId, Integer userId);
}
