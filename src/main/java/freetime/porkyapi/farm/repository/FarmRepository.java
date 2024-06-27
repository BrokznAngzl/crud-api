package freetime.porkyapi.farm.repository;

import freetime.porkyapi.farm.model.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface FarmRepository extends JpaRepository<FarmEntity, BigInteger> {
    FarmEntity findByFarmName(String name);
}
