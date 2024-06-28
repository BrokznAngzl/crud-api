package freetime.porkyapi.housing.service;

import freetime.porkyapi.housing.dao.HousingDAO;
import freetime.porkyapi.housing.model.HousingEntity;
import freetime.porkyapi.housing.repository.HousingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Log4j2
@Service
public class HousingService {
    @Autowired
    private HousingRepository housingRepo;
    @Autowired
    private HousingDAO housingDAO;

    public ResponseEntity<?> saveHousing(HousingEntity housing) {
        try {
            housingRepo.save(housing);
            log.info("save {} successfully.", housing);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getAllHousing() {
        try {
            log.info("get all housing successfully.");
            return ResponseEntity.ok(housingDAO.getHousingWithFarmName());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getHousingById(BigInteger id) {
        try {
            Optional<HousingEntity> result = housingRepo.findById(id);
            log.info("get housing by id {} successfully.", id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> deleteHousing(BigInteger id) {
        try {
            housingRepo.deleteById(id);
            log.info("delete housing id{} successfully.", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
