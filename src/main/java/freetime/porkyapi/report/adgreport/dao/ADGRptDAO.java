package freetime.porkyapi.report.adgreport.dao;

import freetime.porkyapi.report.adgreport.model.ADGRptRequestModel;
import freetime.porkyapi.report.deathreport.model.DeathRptRequestModel;

import java.util.List;

public interface ADGRptDAO {
    List<?> findAverageDailyGrain(ADGRptRequestModel requestModel);
}
