package freetime.porkyapi.report.adgreport.controller;

import freetime.porkyapi.report.adgreport.model.ADGRptRequestModel;
import freetime.porkyapi.report.adgreport.service.ADGRptService;
import freetime.porkyapi.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${cors.allowed.origin}")
@Log4j2
@RequestMapping("/porkyapi/adgreport")
@RestController
public class ADGRptController {

    @Autowired
    private ADGRptService ADGRptService;

    @GetMapping
    public ResponseEntity<?> getAllRptADG() {
        try {
            log.info("Retrieving all Average Daily Grain Report");
            return ADGRptService.getAllADGReport(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/find")
    public ResponseEntity<?> findDeathRpt(@RequestBody ADGRptRequestModel requestModel) {
        try {
            if (!Validator.validateADGRpt(requestModel)) {
                log.warn("Invalid Average Daily Grain Report data for {}", requestModel);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Average Daily Grain Report data");
            }
            log.info("Retrieving all Average Daily Grain Report");
            return ADGRptService.getAllADGReport(requestModel);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}