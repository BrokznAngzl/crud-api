package freetime.porkyapi.casedetail.controller;

import freetime.porkyapi.casedetail.dao.CaseDetailDAO;
import freetime.porkyapi.casedetail.model.CaseDetailEntity;
import freetime.porkyapi.casedetail.service.CaseDetailService;
import freetime.porkyapi.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("${cors.allowed.origin}")
@Log4j2
@RequestMapping("/porkyapi/case")
@RestController
public class CaseDetailController {

    @Autowired
    private CaseDetailService caseDetailService;
    @Autowired
    private CaseDetailDAO caseDAO;

    @PostMapping
    public ResponseEntity<?> createCase(@RequestBody CaseDetailEntity caseDetail) {
        if (!Validator.validateCase(caseDetail)) {
            log.warn("Invalid case data for {}", caseDetail);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid case data");
        }

        log.info("Creating case {}", caseDetail);
        return caseDetailService.saveCaseDetail(caseDetail, false);
    }

    @PutMapping
    public ResponseEntity<?> editCase(@RequestBody CaseDetailEntity caseDetail) {
        if (!Validator.validateCase(caseDetail)) {
            log.info("Invalid case data for {}", caseDetail);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid case data");
        }

        log.info("Updating case {}", caseDetail);
        return caseDetailService.saveCaseDetail(caseDetail, true);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCase(@RequestBody CaseDetailEntity caseDetail) {
        if (!Validator.validateID(caseDetail.getCauseID())) {
            log.warn("Invalid case data for {}", caseDetail);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid case ID");
        }
        return caseDetailService.deleteCaseDetail(caseDetail.getCauseID());
    }

    @GetMapping
    public ResponseEntity<?> getAllCase() {
        try {
            log.info("Retrieving all case");
            return caseDetailService.getAllCaseDetail(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/casename")
    public ResponseEntity<?> getCaseByName(@RequestParam String name) {
        try {
            log.info("find {} in case table", name);
            return caseDetailService.findCaseDetailByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/find")
    public ResponseEntity<?> findCase(@RequestBody CaseDetailEntity caseDetail) {
        try {
            log.info("find case {}", caseDetail);
            List<?> queryResult = caseDAO.getCaseDetailWhere(caseDetail);
            return ResponseEntity.ok(queryResult);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}