package freetime.porkyapi.death.service;


import freetime.porkyapi.death.dao.DeathDAO;
import freetime.porkyapi.death.model.DeathEntity;
import freetime.porkyapi.death.model.DeathRequestModel;
import freetime.porkyapi.death.repository.DeathRepository;
import freetime.porkyapi.importation.model.ImportEntity;
import freetime.porkyapi.importation.repository.ImportRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class DeathService {
    @Autowired
    private DeathRepository deathRepository;
    @Autowired
    private DeathDAO deathDAO;
    @Autowired
    private ImportRepository importRepo;

    public ResponseEntity<?> saveDeath(DeathEntity death, HttpStatus status, boolean isNewRecord) {
        try {
            ImportEntity importation = importRepo.findImportEntityByImportID(death.getImportid());
            BigDecimal deathSum = deathDAO.getDeathSum(death.getImportid());
            BigDecimal importQuantity = importation.getQuanity();

            BigDecimal currentTotal = isNewRecord ? importQuantity.subtract(deathSum)
                    : importQuantity.subtract(deathSum.subtract(getPrevQuantity(death.getDeathID())));

            if ((death.getQuantity().compareTo(currentTotal) <= 0)) {
                deathRepository.save(death);
                log.info("save {} successfully.", death);
                return ResponseEntity.status(status).build();
            } else {
                log.warn("could not save {}", death);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getAllDeath(DeathRequestModel death) {
        try {
            List<?> result = new ArrayList<>();
            result = deathDAO.getAllDeathWhere(death);
            log.info("get all import successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getDeathById(BigInteger id) {
        try {
            Optional<DeathEntity> result = deathRepository.findById(id);
            log.info("get import by id {} successfully.", id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> deleteDeath(BigInteger id) {
        try {
            deathRepository.deleteById(id);
            log.info("delete import id{} successfully.", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private BigDecimal getPrevQuantity(BigInteger id) {
        DeathEntity death = deathRepository.findDeathEntityByDeathID(id);
        return (death.getQuantity() != null)? death.getQuantity() : new BigDecimal(0);
    }
}
