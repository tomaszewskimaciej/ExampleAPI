package io.example.app.backend.rest.service;

import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.rest.model.task.TaskRequest;
import io.example.app.backend.rest.model.task.TaskResponse;
import io.example.app.backend.rest.service.adapter.TaskServiceAdapter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskRestService {

    private final TaskServiceAdapter adapter;

    public TaskRestService(TaskServiceAdapter adapter) {
        this.adapter = adapter;
    }

    public List<TaskResponse> searchTasks(String search) {
        return adapter.searchTasks(search);
    }

    public TaskResponse getTaskById(Integer id) {
        return adapter.getTaskById(id);
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        return adapter.createTask(taskRequest);
    }

    public TaskResponse updateTask(Integer id, TaskRequest taskRequest) {
        return adapter.updateTask(id, taskRequest);
    }

    public void deleteTask(Integer id) {
        adapter.deleteTask(id);
    }

    public TaskResponse changeStatus(Integer id, TaskStatusType statusType) {
        return adapter.changeStatus(id, statusType);
    }

    public TaskResponse assignUser(Integer taskId, Integer userId) {
        return adapter.assignUser(taskId, userId);
    }
}
