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
    private String name;

}
