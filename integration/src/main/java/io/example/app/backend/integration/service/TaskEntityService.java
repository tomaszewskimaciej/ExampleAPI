package io.example.app.backend.integration.service;

import io.example.app.backend.integration.entity.Task;
import io.example.app.backend.integration.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskEntityService {
    TaskRepository repository;

    public TaskEntityService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllAccounts() {
        return repository.findAll();
    }

    public Task getTaskById(int id) {
        return repository.findById(id).get();
    }

    public void saveOrUpdate(Task task) {
        repository.save(task);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}

