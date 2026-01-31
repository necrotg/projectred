package model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User {
    private Long id;
    private Customer customer;
    private String name;
    private String email;
    private String senha;
    private Roles role;
}
