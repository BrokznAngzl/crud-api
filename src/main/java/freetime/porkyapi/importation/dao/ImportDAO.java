package freetime.porkyapi.importation.dao;

import freetime.porkyapi.housing.model.HousingEntity;
import freetime.porkyapi.importation.model.ImportEntity;
import freetime.porkyapi.importation.model.ImportRequestModel;

import java.util.List;

public interface ImportDAO {
    public List<?> getImportWithHousingName();
    public List<?> getImportWithHousingName(ImportRequestModel housing);
}
