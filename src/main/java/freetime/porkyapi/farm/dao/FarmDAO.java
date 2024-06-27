package freetime.porkyapi.farm.dao;

import freetime.porkyapi.farm.model.FarmEntity;

import java.util.List;

public interface FarmDAO {
    public List<FarmEntity> findFarm(FarmEntity farm);
}
