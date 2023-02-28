package io.example.app.backend.rest.controller;

import io.example.app.backend.rest.controller.version.RestApiVersion;
import io.example.app.backend.rest.model.user.UserRequest;
import io.example.app.backend.rest.model.user.UserResponse;
import io.example.app.backend.rest.service.UserRestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(RestApiVersion.version + "/user")
public class UserController {

    private final UserRestService service;

    @GetMapping
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam(name = "search", required = false) String search) {
        List<UserResponse> userResponse = service.searchUsers(search);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Integer id) {
        UserResponse userResponse = service.getUserById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = service.createUser(userRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        service.deleteUser(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
