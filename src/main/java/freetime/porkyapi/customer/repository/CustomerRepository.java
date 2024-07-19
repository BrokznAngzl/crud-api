package freetime.porkyapi.customer.repository;

import freetime.porkyapi.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CustomerRepository extends JpaRepository<CustomerEntity, BigInteger> {
    CustomerEntity findByCustomerName(String name);
}
