package io.example.app.backend.rest.model.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
}
