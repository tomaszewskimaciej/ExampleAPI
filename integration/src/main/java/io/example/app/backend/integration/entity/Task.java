package io.example.app.backend.integration.entity;

import io.example.app.backend.common.type.TaskStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FB_TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatusType status;

    @Column(nullable = false)
    private LocalDate finishDate;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<User> users;
}