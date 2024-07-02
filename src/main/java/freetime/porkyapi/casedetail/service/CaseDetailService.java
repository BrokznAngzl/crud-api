package freetime.porkyapi.casedetail.service;

import freetime.porkyapi.casedetail.dao.CaseDetailDAO;
import freetime.porkyapi.casedetail.model.CaseDetailEntity;
import freetime.porkyapi.casedetail.repository.CaseDetailRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CaseDetailService {
    @Autowired
    private CaseDetailRepository caseDetailRepository;
    @Autowired
    private CaseDetailDAO caseDetailDAO;

    public ResponseEntity<?> saveCaseDetail(CaseDetailEntity caseDetail, Boolean isEdit) {
        try {
            caseDetailRepository.save(caseDetail);
            log.info("save {} successfully.", caseDetail);
            return isEdit ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getAllCaseDetail(CaseDetailEntity caseDetail) {
        try {
            List<?> result = new ArrayList<>();
            result = (caseDetail != null) ? caseDetailDAO.getCaseDetailWhere(caseDetail) : caseDetailRepository.findAll();
            log.info("get all caseDetail successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getCaseDetailById(BigInteger id) {
        try {
            Optional<CaseDetailEntity> result = caseDetailRepository.findById(id);
            log.info("get caseDetail by id {} successfully.", id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> deleteCaseDetail(BigInteger id) {
        try {
            caseDetailRepository.deleteById(id);
            log.info("delete caseDetail id{} successfully.", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> findCaseDetailByName(@RequestParam String name) {
        try {
            log.info("find caseDetail by name {}.", name);
            return ResponseEntity.ok(caseDetailRepository.findByCause(name));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
