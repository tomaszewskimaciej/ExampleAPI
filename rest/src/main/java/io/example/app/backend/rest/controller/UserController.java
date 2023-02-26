package io.example.app.backend.rest.controller;

import io.example.app.backend.rest.controller.version.RestApiVersion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestApiVersion.version + "/user")
public class UserController {


}
