package freetime.porkyapi.importation.repository;

import freetime.porkyapi.importation.model.ImportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ImportRepository extends JpaRepository<ImportEntity, BigInteger> {
    ImportEntity findImportEntityByImportID(BigInteger importId);
}
