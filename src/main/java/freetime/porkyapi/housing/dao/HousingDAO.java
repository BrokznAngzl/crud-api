package freetime.porkyapi.housing.dao;

import freetime.porkyapi.housing.model.HousingEntity;

import java.util.List;

public interface HousingDAO {
    public List<?> getHousingWithFarmName();
    public List<?> getHousingWithFarmName(HousingEntity housing);
}
