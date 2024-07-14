package freetime.porkyapi.report.deathreport.dao;

import freetime.porkyapi.report.deathreport.model.DeathRptRequestModel;

import java.util.List;

public interface DeathRptDAO {
    List<?> findDeathReport(DeathRptRequestModel deathRptRequestModel);
}
