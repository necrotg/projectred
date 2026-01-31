package repositoty;

import model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
   Customer findByEmailIgnoreCase(String email);
   Customer findByCpf(String cpf);
}
