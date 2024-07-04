package freetime.porkyapi.deathreport.dao;

import freetime.porkyapi.deathreport.model.DeathRptRequestModel;

import java.util.List;

public interface DeathRptDAO {
    List<?> findDeathReport(DeathRptRequestModel deathRptRequestModel);
}
