package freetime.porkyapi.breeds.service;

import freetime.porkyapi.breeds.model.BreedsEntity;
import freetime.porkyapi.breeds.repository.BreedsRepository;
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
public class BreedsService {
    @Autowired
    private BreedsRepository breedsRepository;

    public ResponseEntity<?> saveBreeds(BreedsEntity breeds, Boolean isEdit) {
        try {
            breedsRepository.save(breeds);
            log.info("save {} successfully.", breeds);
            return isEdit ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getAllBreeds() {
        try {
            List<BreedsEntity> result = breedsRepository.findAll();
            log.info("get all breeds successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getBreedsById(BigInteger id) {
        try {
            Optional<BreedsEntity> result = breedsRepository.findById(id);
            log.info("get breeds by Id {} successfully.", id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> deleteBreeds(BigInteger id) {
        try {
            breedsRepository.deleteById(id);
            log.info("delete breeds id {} successfully.", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> findBreedsByName(String name) {
        try {
            BreedsEntity result = breedsRepository.findByBreedsName(name);
            log.info("find breeds by name {} successfully.", name);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

