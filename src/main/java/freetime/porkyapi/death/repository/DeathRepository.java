package freetime.porkyapi.death.repository;

import freetime.porkyapi.death.model.DeathEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface DeathRepository extends JpaRepository<DeathEntity, BigInteger> {
    DeathEntity findDeathEntityByDeathID(BigInteger id);
}
