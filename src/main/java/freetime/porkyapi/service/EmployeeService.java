package freetime.porkyapi.service;

import freetime.porkyapi.model.EmployeeEntity;
import freetime.porkyapi.farm.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    public ResponseEntity<?> insertEmployee(EmployeeEntity employee) {
        try {
            employeeRepo.save(employee);
            log.info("save {} successfully.", employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
