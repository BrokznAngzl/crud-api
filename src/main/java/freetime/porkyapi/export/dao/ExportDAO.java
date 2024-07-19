package freetime.porkyapi.export.dao;

import freetime.porkyapi.export.model.ExportRequestModel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface ExportDAO {
    public List<?> getAllExport(ExportRequestModel export);
    public BigDecimal getExportSum(BigInteger exportID);
}
