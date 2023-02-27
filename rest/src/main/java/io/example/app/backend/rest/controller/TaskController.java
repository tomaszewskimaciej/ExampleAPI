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

@RestController
@AllArgsConstructor
@RequestMapping(RestApiVersion.version + "/task")
public class TaskController {

    private final TaskRestService service;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = service.createTask(taskRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskResponse);
    }

    @PutMapping
    public ResponseEntity<TaskResponse> updateTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = service.updateTask(taskRequest);

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

    @PutMapping("/change-status/{id}/{status}")
    public ResponseEntity<TaskResponse> changeStatus(@PathVariable("id") Integer id,
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
