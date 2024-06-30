package freetime.porkyapi.breeds.repository;

import freetime.porkyapi.breeds.model.BreedsEntity;
import freetime.porkyapi.farm.model.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface BreedsRepository extends JpaRepository<BreedsEntity, BigInteger> {
    BreedsEntity findByBreedsName(String name);
}
