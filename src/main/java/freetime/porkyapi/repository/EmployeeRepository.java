package freetime.porkyapi.repository;

import freetime.porkyapi.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, BigInteger> {

}
