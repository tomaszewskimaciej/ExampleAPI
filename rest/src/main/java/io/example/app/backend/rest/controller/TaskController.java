package io.example.app.backend.rest.controller;

import io.example.app.backend.rest.controller.version.RestApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestApiVersion.version + "/task")
public class TaskController {

}
