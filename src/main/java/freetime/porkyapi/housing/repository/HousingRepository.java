package freetime.porkyapi.housing.repository;

import freetime.porkyapi.housing.model.HousingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface HousingRepository extends JpaRepository<HousingEntity, BigInteger> {

    public HousingEntity findByHousingName(String housingName);
//    @Query("SELECT new freetime.porkyapi.housing.model.HousingEntity(h.housingID, h.housingName, h.stallQuanity, h.farm.farmName) " +
//            "FROM HousingEntity h JOIN h.farmID f")
//    List<HousingEntity> findAllWithFarmName();
}
