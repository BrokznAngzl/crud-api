package freetime.porkyapi.customer.service;

import freetime.porkyapi.customer.model.CustomerEntity;
import freetime.porkyapi.customer.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Log4j2
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<?> saveCustomer(CustomerEntity customer, Boolean isEdit) {
        try {
            customerRepository.save(customer);
            log.info("save {} successfully.", customer);
            return isEdit ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getAllCustomer() {
        try {
            List<CustomerEntity> result = customerRepository.findAll();
            log.info("get all customer successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> deleteCustomer(BigInteger id) {
        try {
            customerRepository.deleteById(id);
            log.info("delete customer id {} successfully.", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> findCustomerByName(String name) {
        try {
            CustomerEntity result = customerRepository.findByCustomerName(name);
            log.info("find customer name {} successfully.", name);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

