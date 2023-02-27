package io.example.app.backend.rest.model.task;

import io.example.app.backend.common.type.TaskStatusType;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class TaskRequest {
    private String title;
    private String description;
    private TaskStatusType status;
    private LocalDate finishDate;
}
