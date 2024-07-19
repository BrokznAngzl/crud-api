package freetime.porkyapi.casedetail.dao;

import freetime.porkyapi.casedetail.model.CaseDetailEntity;
import freetime.porkyapi.housing.model.HousingEntity;

import java.util.List;

public interface CaseDetailDAO {
    public List<?> getCaseDetailWhere(CaseDetailEntity caseDetail);
}
