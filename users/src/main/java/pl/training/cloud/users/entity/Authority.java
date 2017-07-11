package pl.training.cloud.users.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "authorities")
@Entity
@Data
public class Authority {

    @GeneratedValue
    @Id
    private Long id;
    private String login;
    private String authority;

    public Authority() {
    }

    public Authority(String login, String authority) {
        this.login = login;
        this.authority = authority;
    }

}
