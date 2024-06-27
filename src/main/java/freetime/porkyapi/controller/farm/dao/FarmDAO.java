package freetime.porkyapi.controller.farm.dao;

import freetime.porkyapi.model.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface FarmDAO {
    public List<FarmEntity> findFarm(FarmEntity farm);
}
