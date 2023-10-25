package com.ada.sorvetada.controllers;

import com.ada.sorvetada.dtos.CustomerDto;
import com.ada.sorvetada.entities.Customer;
import com.ada.sorvetada.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sorvetada/api/client")
public class CustomerController {
    CustomerService customerService;


    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<Customer> getClientes() {
        return customerService.getAll();
    }


    @PostMapping("")
    public ResponseEntity<CustomerDto> saveCliente(@RequestBody CustomerDto cliente) {
        try {
            CustomerDto savedCliente = customerService.createNewClient(cliente);
            if (savedCliente != null)
                return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
        customerService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
