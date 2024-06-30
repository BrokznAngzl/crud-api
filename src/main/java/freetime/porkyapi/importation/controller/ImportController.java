package freetime.porkyapi.importation.controller;

import freetime.porkyapi.importation.model.ImportEntity;
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
    public ResponseEntity<?> createImport(@RequestBody ImportEntity Importation) {
        if (!Validator.validateImport(Importation)) {
            log.warn("Invalid Importation data for {}", Importation);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Importation data");
        }

        log.info("Creating Importation {}", Importation);
        return importService.saveImport(Importation, false);
    }

    @PutMapping
    public ResponseEntity<?> editImport(@RequestBody ImportEntity Importation) {
        if (!Validator.validateImport(Importation)) {
            log.info("Invalid Importation data for {}", Importation);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Importation data");
        }

        log.info("Updating Importation {}", Importation);
        return importService.saveImport(Importation, true);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteImport(@RequestBody ImportEntity Importation) {
        if (!Validator.validateID(Importation.getImportID())) {
            log.warn("Invalid Importation data for {}", Importation);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Importation data");
        }
        return importService.deleteImport(Importation.getImportID());
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

    @PostMapping("/find")
    public ResponseEntity<?> findImport(@RequestBody ImportEntity Importation) {
        try {
            log.info("Retrieving all Importations");
            return importService.getAllImport(Importation);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}