package io.example.app.backend.rest.model.task;

import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.rest.controller.TaskController;
import io.example.app.backend.rest.model.user.UserResponse;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskResponse extends RepresentationModel<TaskResponse> {
    private Integer id;
    private String title;
    private String description;
    private TaskStatusType status;
    private LocalDate finishDate;
    private Set<UserResponse> users;

    public void addLink() {
        this.add(linkTo(methodOn(TaskController.class).getTaskById(this.getId())).withSelfRel());
    }
}
