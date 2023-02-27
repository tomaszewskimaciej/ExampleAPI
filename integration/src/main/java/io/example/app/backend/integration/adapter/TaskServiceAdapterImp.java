package io.example.app.backend.integration.adapter;

import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.integration.entity.Task;
import io.example.app.backend.integration.entity.User;
import io.example.app.backend.integration.mapper.TaskMapper;
import io.example.app.backend.integration.service.TaskService;
import io.example.app.backend.rest.model.task.TaskRequest;
import io.example.app.backend.rest.model.task.TaskResponse;
import io.example.app.backend.rest.service.adapter.TaskServiceAdapter;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceAdapterImp implements TaskServiceAdapter {

    private final TaskService service;
    private final TaskMapper mapper;

    public TaskServiceAdapterImp(TaskService service, TaskMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public TaskResponse search(Integer id) {
        Task task = service.findone(id);
        return mapper.taskToTaskResponse(task);
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = service.save(mapper.taskRequestToTask(taskRequest));
        return mapper.taskToTaskResponse(task);
        //return mapper.taskToTaskResponse(service.save(mapper.taskRequestToTask(taskRequest)));
    }

    @Override
    public TaskResponse updateTask(Integer id, TaskRequest taskRequest) {
        Task task = service.update(id, mapper.taskRequestToTask(taskRequest));
        return mapper.taskToTaskResponse(task);
    }

    @Override
    public void deleteTask(Integer id) {
        service.delete(id);
    }

    @Override
    public TaskResponse changeStatus(Integer id, TaskStatusType statusType) {
        Task task = service.changeStatus(id, statusType);
        return mapper.taskToTaskResponse(task);
    }

    @Override
    public TaskResponse assignUser(Integer taskId, Integer userId) {
        Task task = service.assignUser(taskId, userId);
        return mapper.taskToTaskResponse(task);
    }
}
