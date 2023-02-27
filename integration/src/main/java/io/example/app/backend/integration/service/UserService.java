package io.example.app.backend.integration.service;

import io.example.app.backend.common.exception.ExampleAppException;
import io.example.app.backend.common.exception.type.ExampleAppErrorType;
import io.example.app.backend.integration.entity.Task;
import io.example.app.backend.integration.entity.User;
import io.example.app.backend.integration.filter.PerformSearch;
import io.example.app.backend.integration.repository.TaskRepository;
import io.example.app.backend.integration.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    public TaskRepository taskRepository;
    private final PerformSearch performSearch;

    public UserService(UserRepository userRepository, TaskRepository taskRepository, PerformSearch performSearch) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.performSearch = performSearch;
    }

    public List<User> searchUsers(String search) {
        return performSearch.searchEntities(userRepository, search);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        User userToBeDeleted = userRepository.findById(id)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_001));

        List<Task> taskList = taskRepository.findAll();
        for (Task task : taskList) {
            task.getUsers().remove(userToBeDeleted);
        }

        userRepository.delete(userToBeDeleted);
    }
}
