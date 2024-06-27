package freetime.porkyapi.farm.service;

import freetime.porkyapi.farm.model.FarmEntity;
import freetime.porkyapi.farm.repository.FarmRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class FarmService {
    @Autowired
    private FarmRepository farmRepo;

    public ResponseEntity<?> saveFarm(FarmEntity farm) {
        try {
            farmRepo.save(farm);
            log.info("save {} successfully.", farm);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getAllFarm() {
        try {
            List<FarmEntity> result = farmRepo.findAll();
            log.info("getAllFarm successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getFarmById(BigInteger id) {
        try {
            Optional<FarmEntity> result = farmRepo.findById(id);
            log.info("getFarmById {} successfully.", id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> deleteFarm(BigInteger id) {
        try {
            farmRepo.deleteById(id);
            log.info("deleteFarm id{} successfully.", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> findFarmByName(String name) {
        try {
            FarmEntity result = farmRepo.findByFarmName(name);
            log.info("findFarmByName {} successfully.", name);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
