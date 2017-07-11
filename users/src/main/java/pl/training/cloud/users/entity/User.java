package pl.training.cloud.users.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Data
public class User {

    @GeneratedValue
    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;
    private Long departmentId;

}
