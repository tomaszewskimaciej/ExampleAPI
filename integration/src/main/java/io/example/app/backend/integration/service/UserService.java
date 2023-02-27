package io.example.app.backend.integration.service;

import io.example.app.backend.common.exception.ExampleAppException;
import io.example.app.backend.common.exception.type.ExampleAppErrorType;
import io.example.app.backend.integration.entity.User;
import io.example.app.backend.integration.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public User save(User user) {
        return userRepository.save(user);
        //exception catcher needed
    }

    public void delete(Integer id) {
        User userToBeDeleted = userRepository.findById(id)
                .orElseThrow(() -> new ExampleAppException(ExampleAppErrorType.EA_001));
        userRepository.delete(userToBeDeleted);
    }
}
