package freetime.porkyapi.export.controller;


import freetime.porkyapi.export.model.ExportEntity;
import freetime.porkyapi.export.model.ExportRequestModel;
import freetime.porkyapi.export.service.ExportService;
import freetime.porkyapi.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${cors.allowed.origin}")
@Log4j2
@RequestMapping("/porkyapi/export")
@RestController
public class ExportController {

    @Autowired
    private ExportService exportService;

    @PostMapping
    public ResponseEntity<?> createExport(@RequestBody ExportEntity export) {
        if (!Validator.validateExport(export)) {
            log.warn("Invalid Export data for {}", export);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Export data");
        }

        log.info("Creating Export {}", export);
        return exportService.saveExport(export, HttpStatus.CREATED, true);
    }

    @PutMapping
    public ResponseEntity<?> editExport(@RequestBody ExportEntity export) {
        if (!Validator.validateExport(export)) {
            log.info("Invalid Export data for {}", export);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Export data");
        }

        log.info("Updating Export {}", export);
        return exportService.saveExport(export, HttpStatus.OK, false);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteExport(@RequestBody ExportEntity export) {
        if (!Validator.validateID(export.getExportID())) {
            log.warn("Invalid Export data for {}", export);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Export data");
        }
        return exportService.deleteExport(export.getExportID());
    }

    @GetMapping
    public ResponseEntity<?> getAllExports() {
        try {
            log.info("Retrieving all Exports");
            return exportService.getAllExport(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/find")
    public ResponseEntity<?> findExport(@RequestBody ExportRequestModel export) {
        try {
            if (!Validator.validateExport(export)){
                log.warn("Invalid Export data for {}", export);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Export data");
            }
            log.info("Retrieving all Exports");
            return exportService.getAllExport(export);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}