package io.example.app.backend.rest.service;

import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.rest.model.task.TaskRequest;
import io.example.app.backend.rest.model.task.TaskResponse;
import io.example.app.backend.rest.service.adapter.TaskServiceAdapter;
import org.springframework.stereotype.Service;

@Service
public class TaskRestService {

    private final TaskServiceAdapter adapter;

    public TaskRestService(TaskServiceAdapter adapter) {
        this.adapter = adapter;
    }

    public TaskResponse search(Integer id){
        return adapter.search(id);
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        return adapter.createTask(taskRequest);
    }

    public TaskResponse updateTask(TaskRequest taskRequest) {
        return adapter.updateTask(taskRequest);
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
