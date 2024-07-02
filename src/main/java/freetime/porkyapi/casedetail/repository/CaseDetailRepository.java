package freetime.porkyapi.casedetail.repository;

import freetime.porkyapi.casedetail.model.CaseDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CaseDetailRepository extends JpaRepository<CaseDetailEntity, BigInteger> {
    public CaseDetailEntity findByCause(String cause);
}
