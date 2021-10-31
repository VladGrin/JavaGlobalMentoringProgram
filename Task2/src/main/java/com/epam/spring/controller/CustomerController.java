package com.epam.spring.controller;

import com.epam.spring.entity.Customer;
import com.epam.spring.entity.CustomerBuilder;
import com.epam.spring.repository.CustomerRepository;
import com.epam.spring.validation.CustomerValidationError;
import com.epam.spring.validation.CustomerValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    public CustomerRepository repository;

    @RequestMapping(value = "/customer", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(CustomerValidationErrorBuilder.fromBindingsErrors(errors));
        }
        Customer savedCustomer = repository.save(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(savedCustomer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
        Optional<Customer> customerById = repository.findById(id);
        return customerById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/customer")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) {
        repository.delete(CustomerBuilder.create().withId(id).builder());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/customer")
    public ResponseEntity<Customer> deleteCustomer(@RequestBody Customer customer) {
        repository.delete(customer);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomerValidationError handleException(Exception exception) {
        return new CustomerValidationError(exception.getMessage());
    }
}
