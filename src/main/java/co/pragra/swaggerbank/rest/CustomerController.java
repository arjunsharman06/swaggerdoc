package co.pragra.swaggerbank.rest;

import co.pragra.swaggerbank.domain.Customer;
import co.pragra.swaggerbank.domain.Error;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.pragra.swaggerbank.service.CustomerService;

import java.net.URI;

@RestController
@RequestMapping("/api")
@Api(value="Customer Controller",tags = {"customer","account"})
public class CustomerController {
    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @ApiResponse(code = 201,message = "Customer is created",response = Customer.class)
    @ApiResponses({
            @ApiResponse(code = 400,message ="Customer not created",response = Error.class)
    })
    @PostMapping(value = "/customer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = this.service.createCustomer(customer);
        if (newCustomer != null) {
            URI uri = URI.create("https://localhost/customer" + customer.getId());
            return ResponseEntity.status(HttpStatus.OK).body(newCustomer);
        }
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new Error(400,String.format("Customer with name %s is not created", customer.getName())));
    }

    @ApiOperation(value = "customer/{id}",response = Customer.class)
    //api returning multiple responses and it returns an array
    @ApiResponses({
            @ApiResponse(code=200,message="Successfully found",response = Customer.class),
            @ApiResponse(code=404,message="Customer not found",response = Error.class),
            @ApiResponse(code=500,message="OOps something went wrong",response = Error.class),
    })
    @GetMapping(value = "customer/{id}")
    public ResponseEntity<?> getByID(@PathVariable Long id) {
        try {
            if (service.getCustomerByID(id).isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(service.getCustomerByID(id));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(400, String.format("No customer found for %d as ID", id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(500,"OOps Something went wrong"));
        }
    }
}
