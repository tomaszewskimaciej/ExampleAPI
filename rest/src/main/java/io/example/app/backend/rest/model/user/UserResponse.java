package io.example.app.backend.rest.model.user;

import io.example.app.backend.rest.controller.UserController;
import io.example.app.backend.rest.model.task.TaskResponse;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponse extends RepresentationModel<UserResponse> {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public void addLink() {
        this.add(linkTo(methodOn(UserController.class).getUserById(this.getId())).withSelfRel()) ;
    }
}
