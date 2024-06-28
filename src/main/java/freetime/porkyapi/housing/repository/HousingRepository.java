package freetime.porkyapi.housing.repository;

import freetime.porkyapi.housing.model.HousingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface HousingRepository extends JpaRepository<HousingEntity, BigInteger> {

}
