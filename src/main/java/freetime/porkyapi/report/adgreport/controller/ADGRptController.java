package freetime.porkyapi.report.adgreport.controller;

import freetime.porkyapi.report.adgreport.model.ADGRptRequestModel;
import freetime.porkyapi.report.adgreport.service.ADGRptService;
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
            log.info("Retrieving all Average Daily Grain Report");
            return ADGRptService.getAllADGReport(requestModel);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}