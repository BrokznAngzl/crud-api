package freetime.porkyapi.death.dao;

import freetime.porkyapi.death.model.DeathRequestModel;
import freetime.porkyapi.importation.model.ImportRequestModel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface DeathDAO {
    public List<?> getAllDeath();
    public List<?> getAllDeathWhere(DeathRequestModel death);
    public BigDecimal getDeathSum(BigInteger importID);
}
