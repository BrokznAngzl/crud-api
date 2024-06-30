package freetime.porkyapi.breeds.controller;

import freetime.porkyapi.breeds.model.BreedsEntity;
import freetime.porkyapi.breeds.service.BreedsService;
import freetime.porkyapi.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${cors.allowed.origin}")
@Log4j2
@RequestMapping("/porkyapi/breeds")
@RestController
public class BreedsController {

    @Autowired
    private BreedsService breedsService;

    @PostMapping
    public ResponseEntity<?> createBreeds(@RequestBody BreedsEntity breeds) {
        if (!Validator.validateBreeds(breeds)) {
            log.warn("Invalid breeds data for {}", breeds);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid breeds data");
        }

        log.info("Creating breeds {}", breeds);
        return breedsService.saveBreeds(breeds, false);
    }

    @PutMapping
    public ResponseEntity<?> editBreeds(@RequestBody BreedsEntity breeds) {
        if (!Validator.validateBreeds(breeds)) {
            log.info("Invalid breeds data for {}", breeds);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid breeds data");
        }

        log.info("Updating breeds {}", breeds);
        return breedsService.saveBreeds(breeds, true);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBreeds(@RequestBody BreedsEntity breeds) {
        if (!Validator.validateID(breeds.getBreedsID())) {
            log.warn("Invalid breeds data for {}", breeds);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid breeds ID");
        }
        return breedsService.deleteBreeds(breeds.getBreedsID());
    }

    @GetMapping
    public ResponseEntity<?> getAllBreeds() {
        try {
            log.info("Retrieving all breedss");
            return breedsService.getAllBreeds();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/breedsname")
    public ResponseEntity<?> getBreedsByName(@RequestParam String name) {
        try {
            log.info("find {} in breeds table", name);
            return breedsService.findBreedsByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}