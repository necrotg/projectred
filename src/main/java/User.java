import jakarta.persistence.Entity;
import lombok.Data;

import javax.management.relation.Role;

@Entity
@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String senha;
    private Roles role;
}
