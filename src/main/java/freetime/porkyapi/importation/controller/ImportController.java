package freetime.porkyapi.importation.controller;

import freetime.porkyapi.importation.model.ImportEntity;
import freetime.porkyapi.importation.model.ImportRequestModel;
import freetime.porkyapi.importation.service.ImportService;
import freetime.porkyapi.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${cors.allowed.origin}")
@Log4j2
@RequestMapping("/porkyapi/import")
@RestController
public class ImportController {

    @Autowired
    private ImportService importService;

    @PostMapping
    public ResponseEntity<?> createImport(@RequestBody ImportEntity importation) {
        if (!Validator.validateImport(importation)) {
            log.warn("Invalid Importation data for {}", importation);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Importation data");
        }

        log.info("Creating Importation {}", importation);
        return importService.saveImport(importation, false);
    }

    @PutMapping
    public ResponseEntity<?> editImport(@RequestBody ImportEntity importation) {
        if (!Validator.validateImport(importation)) {
            log.info("Invalid Importation data for {}", importation);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Importation data");
        }

        log.info("Updating Importation {}", importation);
        return importService.saveImport(importation, true);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteImport(@RequestBody ImportEntity importation) {
        if (!Validator.validateID(importation.getImportID())) {
            log.warn("Invalid Importation data for {}", importation);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Importation data");
        }
        return importService.deleteImport(importation.getImportID());
    }

    @GetMapping
    public ResponseEntity<?> getAllImports() {
        try {
            log.info("Retrieving all Importations");
            return importService.getAllImport(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/code")
    public ResponseEntity<?> getImportByCode(@RequestParam String code) {
        try {
            log.info("Retrieving Import code {}", code);
            return importService.getImportByCode(code);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/find")
    public ResponseEntity<?> findImport(@RequestBody ImportRequestModel importation) {
        try {
            if (!Validator.validateImport(importation)){
                log.warn("Invalid Importation data for {}", importation);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Importation data");
            }
            log.info("Retrieving all Importations");
            return importService.getAllImport(importation);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}