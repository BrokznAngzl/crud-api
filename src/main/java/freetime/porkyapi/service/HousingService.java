package freetime.porkyapi.service;

import freetime.porkyapi.model.HousingEntity;
import freetime.porkyapi.repository.HousingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class HousingService {
    @Autowired
    private HousingRepository housingRepo;

    public ResponseEntity<?> insertHousing(HousingEntity housing) {
        try {
            housingRepo.save(housing);
            log.info("save {} successfully.", housing);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
