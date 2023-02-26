package io.example.app.backend.integration.service;

import io.example.app.backend.integration.entity.User;
import io.example.app.backend.integration.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {
    UserRepository userRepository;

    public UserEntityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
