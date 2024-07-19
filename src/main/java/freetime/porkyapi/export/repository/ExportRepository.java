package freetime.porkyapi.export.repository;


import freetime.porkyapi.export.model.ExportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ExportRepository extends JpaRepository<ExportEntity, BigInteger> {
    ExportEntity findExportEntityByExportID(BigInteger exportID);
    ExportEntity findExportEntityByExportCode(String exportCode);
}
