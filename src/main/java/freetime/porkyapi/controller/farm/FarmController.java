package freetime.porkyapi.controller.farm;

import freetime.porkyapi.controller.farm.dao.FarmDAO;
import freetime.porkyapi.model.FarmEntity;
import freetime.porkyapi.service.FarmService;
import freetime.porkyapi.validator.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@CrossOrigin("${cors.allowed.origin}")
@Log4j2
@RequestMapping("/porkyapi/farm")
@RestController
public class FarmController {

    @Autowired
    private FarmService farmService;
    @Autowired
    private FarmDAO farmDAO;


    @PostMapping
    public ResponseEntity<?> createFarm(@RequestBody FarmEntity farm) {
        if (!Validator.validateFarm(farm)) {
            log.warn("Invalid farm data for {}", farm);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid farm data");
        }

        log.info("Creating farm {}", farm);
        return farmService.saveFarm(farm);
    }

    @PutMapping
    public ResponseEntity<?> editFarm(@RequestBody FarmEntity farm) {
        if (!Validator.validateFarm(farm)) {
            log.info("Invalid farm data for {}", farm);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid farm data");
        }

        log.info("Updating farm {}", farm);
        return farmService.saveFarm(farm);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFarm(@RequestBody FarmEntity farm) {
        if (!Validator.validateID(farm.getFarmID())) {
            log.warn("Invalid farm data for {}", farm);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid farm ID");
        }
        return farmService.deleteFarm(farm.getFarmID());
    }

    @GetMapping
    public ResponseEntity<?> getAllFarms() {
        try {
            log.info("Retrieving all farms");
            return farmService.getAllFarm();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/find")
    public ResponseEntity<?> findFarm(@RequestBody FarmEntity farm) {
        try {
            log.info("find farm {}", farm);
            List<FarmEntity> queryResult = farmDAO.findFarm(farm);
            return ResponseEntity.ok(queryResult);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{farmID}")
    public ResponseEntity<?> getFarm(@PathVariable BigInteger farmID) {
        try {
            if (!Validator.validateID(farmID)) {
                log.warn("Invalid farm data for {}", farmID);
                return ResponseEntity.notFound().build();
            }

            log.info("Retrieving farm {}", farmID);
            return farmService.getFarmById(farmID);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}