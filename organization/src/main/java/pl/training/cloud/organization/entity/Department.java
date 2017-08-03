package pl.training.cloud.organization.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "departments")
@Entity
@Data
public class Department {

    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String name;

    @Column
//    @Enumerated(EnumType.STRING)
    private StatusType status;


}
