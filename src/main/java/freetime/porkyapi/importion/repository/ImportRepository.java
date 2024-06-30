package freetime.porkyapi.importion.repository;

import freetime.porkyapi.importion.model.ImportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ImportRepository extends JpaRepository<ImportEntity, BigInteger> {
}
