package freetime.porkyapi.breeds.dao;

import freetime.porkyapi.breeds.model.BreedsEntity;
import freetime.porkyapi.farm.model.FarmEntity;

import java.util.List;

public interface BreedsDAO {
    public List<?> findBreeds(BreedsEntity breeds);
}
