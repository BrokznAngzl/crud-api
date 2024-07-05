package freetime.porkyapi.deathreport.controller;

import freetime.porkyapi.deathreport.model.DeathRptRequestModel;
import freetime.porkyapi.deathreport.service.DeathRptService;
import freetime.porkyapi.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${cors.allowed.origin}")
@Log4j2
@RequestMapping("/porkyapi/deathreport")
@RestController
public class DeathRptController {

    @Autowired
    private DeathRptService deathRptService;

    @GetMapping
    public ResponseEntity<?> getAllRptDeaths() {
        try {
            log.info("Retrieving all Deaths");
            return deathRptService.getAllDeath(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/find")
    public ResponseEntity<?> findDeathRpt(@RequestBody DeathRptRequestModel death) {
        try {
            if (!Validator.validateDeathRpt(death)) {
                log.warn("Invalid death report data for {}", death);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Death data");
            }
            log.info("Retrieving all Deaths");
            return deathRptService.getAllDeath(death);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}