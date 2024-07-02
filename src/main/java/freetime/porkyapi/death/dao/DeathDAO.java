package freetime.porkyapi.death.dao;

import freetime.porkyapi.death.model.DeathRequestModel;
import freetime.porkyapi.importation.model.ImportRequestModel;

import java.util.List;

public interface DeathDAO {
    public List<?> getAllDeath();
    public List<?> getAllDeathWhere(DeathRequestModel death);
}
