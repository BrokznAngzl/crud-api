package freetime.porkyapi.death.controller;

import freetime.porkyapi.death.model.DeathEntity;
import freetime.porkyapi.death.model.DeathRequestModel;
import freetime.porkyapi.death.service.DeathService;
import freetime.porkyapi.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${cors.allowed.origin}")
@Log4j2
@RequestMapping("/porkyapi/death")
@RestController
public class DeathController {

    @Autowired
    private DeathService deathService;

    @PostMapping
    public ResponseEntity<?> createDeath(@RequestBody DeathEntity death) {
        if (!Validator.validateDeath(death)) {
            log.warn("Invalid Death data for {}", death);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Death data");
        }

        log.info("Creating Death {}", death);
        return deathService.saveDeath(death, HttpStatus.CREATED, true);
    }

    @PutMapping
    public ResponseEntity<?> editDeath(@RequestBody DeathEntity death) {
        if (!Validator.validateDeath(death)) {
            log.info("Invalid Death data for {}", death);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Death data");
        }

        log.info("Updating Death {}", death);
        return deathService.saveDeath(death, HttpStatus.OK, false);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDeath(@RequestBody DeathEntity death) {
        if (!Validator.validateID(death.getDeathID())) {
            log.warn("Invalid Death data for {}", death);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Death data");
        }
        return deathService.deleteDeath(death.getDeathID());
    }

    @GetMapping
    public ResponseEntity<?> getAllDeaths() {
        try {
            log.info("Retrieving all Deaths");
            return deathService.getAllDeath(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/find")
    public ResponseEntity<?> findDeath(@RequestBody DeathRequestModel death) {
        try {
            if (!Validator.validateDeath(death)){
                log.warn("Invalid Death data for {}", death);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Death data");
            }
            log.info("Retrieving all Deaths");
            return deathService.getAllDeath(death);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}