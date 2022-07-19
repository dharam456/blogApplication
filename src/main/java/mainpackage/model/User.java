package mainpackage.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "userinfo")
public class User {
    @Id
    private long  id;
    private String name;
    @Column(nullable = false)
    private String email;
    private String password;
}
