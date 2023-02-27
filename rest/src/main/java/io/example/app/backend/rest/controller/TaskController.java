package io.example.app.backend.rest.controller;

import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.rest.controller.version.RestApiVersion;
import io.example.app.backend.rest.model.task.TaskRequest;
import io.example.app.backend.rest.model.task.TaskResponse;
import io.example.app.backend.rest.service.TaskRestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(RestApiVersion.version + "/task")
public class TaskController {

    private final TaskRestService service;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> searchTasks(@RequestParam(name = "search", required = false) String search) {
        List<TaskResponse> taskResponses = service.searchTasks(search);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Integer id) {
        TaskResponse taskResponse = service.getTaskById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskResponse);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = service.createTask(taskRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Integer id,
                                                   @RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = service.updateTask(id, taskRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Integer id) {
        service.deleteTask(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/change-status/{taskId}/{status}")
    public ResponseEntity<TaskResponse> changeStatus(@PathVariable("taskId") Integer id,
                                                     @PathVariable("status") TaskStatusType statusType) {
        TaskResponse taskResponse = service.changeStatus(id, statusType);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskResponse);
    }

    @PutMapping("/assign-user/{taskId}/{userId}")
    public ResponseEntity<TaskResponse> assignUser(@PathVariable("taskId") Integer taskId,
                                                   @PathVariable("userId") Integer userId) {
        TaskResponse taskResponse = service.assignUser(taskId, userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskResponse);
    }

}
