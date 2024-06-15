package freetime.porkyapi.repository;

import freetime.porkyapi.model.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface FarmRepository extends JpaRepository<FarmEntity, BigInteger> {
    FarmEntity findByFarmName(String name);
}
