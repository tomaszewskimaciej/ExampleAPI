package io.example.app.backend.integration.adapter;

import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.integration.entity.Task;
import io.example.app.backend.integration.mapper.TaskMapper;
import io.example.app.backend.integration.service.TaskService;
import io.example.app.backend.rest.model.task.TaskRequest;
import io.example.app.backend.rest.model.task.TaskResponse;
import io.example.app.backend.rest.service.adapter.TaskServiceAdapter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceAdapterImp implements TaskServiceAdapter {

    private final TaskService service;
    private final TaskMapper mapper;

    public TaskServiceAdapterImp(TaskService service, TaskMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<TaskResponse> searchTasks(String search) {
        List<Task> taskList = service.searchTasks(search);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList) {
            taskResponseList.add(mapper.taskToTaskResponse(task));
        }
        return taskResponseList;
    }

    @Override
    public TaskResponse getTaskById(Integer id) {
        Task task = service.getTaskById(id);
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
