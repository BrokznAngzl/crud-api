package freetime.porkyapi.customer.controller;

import freetime.porkyapi.customer.model.CustomerEntity;
import freetime.porkyapi.customer.service.CustomerService;
import freetime.porkyapi.customer.dao.CustomerDAO;
import freetime.porkyapi.customer.model.CustomerEntity;
import freetime.porkyapi.customer.service.CustomerService;
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
@RequestMapping("/porkyapi/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerEntity customer) {
        if (!Validator.validateCustomer(customer)) {
            log.warn("Invalid customer data for {}", customer);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid customer data");
        }

        log.info("Creating customer {}", customer);
        return customerService.saveCustomer(customer, false);
    }

    @PutMapping
    public ResponseEntity<?> editCustomer(@RequestBody CustomerEntity customer) {
        if (!Validator.validateCustomer(customer)) {
            log.info("Invalid customer data for {}", customer);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid customer data");
        }

        log.info("Updating customer {}", customer);
        return customerService.saveCustomer(customer, true);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCustomer(@RequestBody CustomerEntity customer) {
        if (!Validator.validateID(customer.getCustomerID())) {
            log.warn("Invalid customer data for {}", customer);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid customer ID");
        }
        return customerService.deleteCustomer(customer.getCustomerID());
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        try {
            log.info("Retrieving all customers");
            return customerService.getAllCustomer();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PostMapping("/find")
//    public ResponseEntity<?> findCustomer(@RequestBody CustomerEntity customer) {
//        try {
//            log.info("find customer {}", customer);
//            List<CustomerEntity> queryResult = customerDAO.findCustomer(customer);
//            return ResponseEntity.ok(queryResult);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @GetMapping("/customername")
    public ResponseEntity<?> getCustomerByName(@RequestParam String name) {
        try {
            log.info("find {} in customer table", name);
            return customerService.findCustomerByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}