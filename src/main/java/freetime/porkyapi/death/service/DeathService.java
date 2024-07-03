package freetime.porkyapi.death.service;



import freetime.porkyapi.death.dao.DeathDAO;
import freetime.porkyapi.death.model.DeathEntity;
import freetime.porkyapi.death.model.DeathRequestModel;
import freetime.porkyapi.death.repository.DeathRepository;
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
public class DeathService {
    @Autowired
    private DeathRepository deathRepository;
    @Autowired
    private DeathDAO deathDAO;

    public ResponseEntity<?> saveDeath(DeathEntity death, HttpStatus status) {
        try {
            deathRepository.save(death);
            log.info("save {} successfully.", death);
            return ResponseEntity.status(status).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getAllDeath(DeathRequestModel death) {
        try {
            List<?> result = new ArrayList<>();
            result = (death == null)? deathDAO.getAllDeath() : deathDAO.getAllDeathWhere(death);;
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
}
