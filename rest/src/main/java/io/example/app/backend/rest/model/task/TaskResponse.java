package io.example.app.backend.rest.model.task;

import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.rest.model.user.UserResponse;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class TaskResponse {

    private Integer id;
    private String title;
    private String description;
    private TaskStatusType status;
    private LocalDate finishDate;
    private Set<UserResponse> users;
}
