package co.pragra.swaggerbank.service;

import co.pragra.swaggerbank.domain.Customer;
import co.pragra.swaggerbank.exception.CustomException;
import co.pragra.swaggerbank.repo.CustomerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer createCustomer(Customer customer) {
        return this.customerRepo.save(customer);
    }

    public Optional<Customer> getCustomerByID(long id) {
        return customerRepo.findById(id);
    }
}
