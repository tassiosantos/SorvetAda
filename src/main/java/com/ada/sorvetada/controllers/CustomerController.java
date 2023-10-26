package com.ada.sorvetada.controllers;

import com.ada.sorvetada.dtos.CustomerDto;
import com.ada.sorvetada.entities.Customer;
import com.ada.sorvetada.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sorvetada/api/customer")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<Customer> getCustomer() {
        return customerService.getAll();
    }

    @GetMapping("/all/active")
    public List<Customer> getCustomerActive() {
        return customerService.getAllActive();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customer) {
        try {
            CustomerDto savedCustomer = customerService.saveCustumer(customer);
            if (savedCustomer != null)
                return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  
    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customer) {
        try {
            CustomerDto savedCustomer = customerService.updateCustomer(customer);
            if (savedCustomer != null) {
                return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateOrDeactivateCustomer(
            @PathVariable("id") Long id,
            @RequestParam("active") boolean active
    ) {
        try {
            customerService.activateDisableCustomer(active, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getById(id),
                HttpStatus.OK);
    }


    @GetMapping("/email")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(customerService.getByEmail(email),
                HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Customer>> getCustomerByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(customerService.getByName(name), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> activateDisableCustomer(@PathVariable("id") Long id, @RequestParam("active") boolean active) {
        customerService.activateDisableCustomer(active, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ClientDto> getClienteById(@PathVariable("id") Long idClient) {
//        return new ResponseEntity<Client>(clientService.getById(idClient), HttpStatus.OK);
//    }
//
//    @GetMapping("/nome")
//    public ResponseEntity<List<Client>> getClienteByNome(@RequestParam("nome") String name) {
//        return new ResponseEntity<Client>(clientService.getByName(name), HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<CustomerDto> authenticateCustomer(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        CustomerDto authenticatedCustomer = customerService.authenticate(email, password);

        if (authenticatedCustomer != null) {
            return new ResponseEntity<>(authenticatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Ou outro código de status apropriado
        }
    }
}
