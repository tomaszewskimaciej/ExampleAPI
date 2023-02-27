package io.example.app.backend.integration.service;

import io.example.app.backend.common.exception.ExampleAppException;
import io.example.app.backend.common.exception.type.ExampleAppErrorType;
import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.integration.entity.Task;
import io.example.app.backend.integration.entity.User;
import io.example.app.backend.integration.filter.PerformSearch;
import io.example.app.backend.integration.repository.TaskRepository;
import io.example.app.backend.integration.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private final PerformSearch performSearch;

    public TaskService(TaskRepository repository, UserRepository userRepository, PerformSearch performSearch) {
        this.taskRepository = repository;
        this.userRepository = userRepository;
        this.performSearch = performSearch;
    }

    public List<Task> searchTasks(String search) {
        return performSearch.searchEntities(taskRepository, search);
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_003));
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Integer id, Task task) {
        Task taskToBeUpdated = taskRepository.findById(id)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_003));

        taskToBeUpdated.setTitle(task.getTitle());
        taskToBeUpdated.setDescription(task.getDescription());
        taskToBeUpdated.setStatus(task.getStatus());
        taskToBeUpdated.setFinishDate(task.getFinishDate());
        taskToBeUpdated.setUsers(task.getUsers());

        return taskRepository.save(taskToBeUpdated);
    }

    public void delete(Integer id) {
        Task taskToBeDeleted = taskRepository.findById(id)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_003));

        taskRepository.delete(taskToBeDeleted);
    }

    public Task changeStatus(Integer id, TaskStatusType status) throws ExampleAppException {
        if (id == null) {
            throw new ExampleAppException(ExampleAppErrorType.EA_001);
        }
        Task taskToBeUpdated = taskRepository.findById(id)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_003));
        taskToBeUpdated.setStatus(status);

        return taskRepository.save(taskToBeUpdated);
    }

    public Task assignUser(Integer taskId, Integer userId) throws ExampleAppException {
        if (taskId == null || userId == null) {
            throw new ExampleAppException(ExampleAppErrorType.EA_001);
        }
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_003));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_002));

        Set<User> assignedUsers = task.getUsers();
        assignedUsers.add(user);
        task.setUsers(assignedUsers);

        return taskRepository.save(task);
    }
}

