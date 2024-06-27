package freetime.porkyapi.farm.repository;

import freetime.porkyapi.model.HousingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface HousingRepository extends JpaRepository<HousingEntity, BigInteger> {

}
