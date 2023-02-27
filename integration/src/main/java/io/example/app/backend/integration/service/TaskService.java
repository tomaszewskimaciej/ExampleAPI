package io.example.app.backend.integration.service;

import io.example.app.backend.common.exception.ExampleAppException;
import io.example.app.backend.common.exception.type.ExampleAppErrorType;
import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.integration.entity.Task;
import io.example.app.backend.integration.entity.User;
import io.example.app.backend.integration.repository.TaskRepository;
import io.example.app.backend.integration.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskService(TaskRepository repository, UserRepository userRepository) {
        this.taskRepository = repository;
        this.userRepository = userRepository;
    }


    public Optional<Task> getTaskById(Integer id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Integer id, Task task) {
        Task taskToBeUpdated = taskRepository.findById(id)
                .orElseThrow(()->new ExampleAppException(ExampleAppErrorType.EA_005));

        taskToBeUpdated.setTitle(task.getTitle());
        taskToBeUpdated.setDescription(task.getDescription());
        taskToBeUpdated.setStatus(task.getStatus());
        taskToBeUpdated.setFinishDate(task.getFinishDate());
        taskToBeUpdated.setUsers(task.getUsers());

        return taskRepository.save(taskToBeUpdated);
    }

    public void delete(Integer id) {
        Task taskToBeDeleted = taskRepository.findById(id)
                .orElseThrow(()->new ExampleAppException(ExampleAppErrorType.EA_005));
        taskRepository.delete(taskToBeDeleted);
    }

    public Task changeStatus(Integer id, TaskStatusType status) throws ExampleAppException {
        if (id == null) {
            throw new ExampleAppException(ExampleAppErrorType.EA_001);
        }
        Task taskToBeUpdated = getTaskById(id)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_005));
        taskToBeUpdated.setStatus(status);

        return taskRepository.save(taskToBeUpdated);
    }

    public Task assignUser(Integer taskId, Integer userId) throws ExampleAppException {
        if (taskId == null || userId == null) {
            throw new ExampleAppException(ExampleAppErrorType.EA_001);
        }
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_005));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_004));

        Set<User> assignedUsers = task.getUsers();
        assignedUsers.add(user);
        task.setUsers(assignedUsers);

        return taskRepository.save(task);
    }

    public Task findone(Integer id){
        return taskRepository.findById(id).orElseThrow();
    }
}

