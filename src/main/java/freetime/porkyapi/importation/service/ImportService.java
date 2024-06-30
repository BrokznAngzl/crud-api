package freetime.porkyapi.importation.service;


import freetime.porkyapi.importation.model.ImportEntity;
import freetime.porkyapi.importation.repository.ImportRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ImportService {
    @Autowired
    private ImportRepository importRepo;
//    @Autowired
//    private ImportDAO importDAO;

    public ResponseEntity<?> saveImport(ImportEntity importation, Boolean isEdit) {
        try {
            importRepo.save(importation);
            log.info("save {} successfully.", importation);
            return isEdit ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    public ResponseEntity<?> getAllImport(ImportEntity importation) {
//        try {
//            List<?> result = new ArrayList<>();
//            result = (importation != null) ? importDAO.getImportWithFarmName(import) : importDAO.getImportWithFarmName();
//            log.info("get all import successfully.");
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    public ResponseEntity<?> getImportById(BigInteger id) {
        try {
            Optional<ImportEntity> result = importRepo.findById(id);
            log.info("get import by id {} successfully.", id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> deleteImport(BigInteger id) {
        try {
            importRepo.deleteById(id);
            log.info("delete import id{} successfully.", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
