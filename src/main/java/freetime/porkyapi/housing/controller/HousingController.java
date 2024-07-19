package freetime.porkyapi.housing.controller;

import freetime.porkyapi.housing.dao.HousingDAO;
import freetime.porkyapi.housing.model.HousingEntity;
import freetime.porkyapi.housing.service.HousingService;
import freetime.porkyapi.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${cors.allowed.origin}")
@Log4j2
@RequestMapping("/porkyapi/housing")
@RestController
public class HousingController {

    @Autowired
    private HousingService housingService;


    @PostMapping
    public ResponseEntity<?> createHousing(@RequestBody HousingEntity housing) {
        if (!Validator.validateHousing(housing)) {
            log.warn("Invalid housing data for {}", housing);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid housing data");
        }

        log.info("Creating housing {}", housing);
        return housingService.saveHousing(housing, false);
    }

    @PutMapping
    public ResponseEntity<?> editHousing(@RequestBody HousingEntity housing) {
        if (!Validator.validateHousing(housing)) {
            log.info("Invalid housing data for {}", housing);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid housing data");
        }

        log.info("Updating housing {}", housing);
        return housingService.saveHousing(housing, true);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteHousing(@RequestBody HousingEntity housing) {
        if (!Validator.validateID(housing.getHousingID())) {
            log.warn("Invalid housing data for {}", housing);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid housing ID");
        }
        return housingService.deleteHousing(housing.getHousingID());
    }

    @GetMapping
    public ResponseEntity<?> getAllHousings() {
        try {
            log.info("Retrieving all housings");
            return housingService.getAllHousing(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/find")
    public ResponseEntity<?> findHousing(@RequestBody HousingEntity housing) {
        try {
            log.info("Retrieving all housings");
            return housingService.getAllHousing(housing);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/housingname")
    public ResponseEntity<?> getHousingByName(@RequestParam String name) {
        try {
            log.info("find {} in housing table", name);
            return housingService.findHousingByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}