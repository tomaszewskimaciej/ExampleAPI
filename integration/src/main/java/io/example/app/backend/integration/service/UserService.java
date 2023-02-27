package io.example.app.backend.integration.service;

import io.example.app.backend.common.exception.ExampleAppException;
import io.example.app.backend.common.exception.type.ExampleAppErrorType;
import io.example.app.backend.integration.entity.Task;
import io.example.app.backend.integration.entity.User;
import io.example.app.backend.integration.repository.TaskRepository;
import io.example.app.backend.integration.repository.UserRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    public TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<User> searchUsers(String search) {
        Specification<User> spec = Specification.where(null);
        if (search != null && !search.isEmpty()) {
            String[] criteria = search.split(",");
            for (String criterion : criteria) {
                String[] parts = criterion.split(":");
                if (parts.length == 2) {
                    String propertyName = parts[0];
                    String value = parts[1];
                    spec = spec.and((root, query, builder) -> {
                        if (propertyName.contains(">")) {
                            String[] propParts = propertyName.split(">");
                            return builder.greaterThan(root.get(propParts[0]), value);
                        } else if (propertyName.contains("<")) {
                            String[] propParts = propertyName.split("<");
                            return builder.lessThan(root.get(propParts[0]), value);
                        } else {
                            return builder.equal(root.get(propertyName), value);
                        }
                    });
                }
            }
        }

        return userRepository.findAll(spec);
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
