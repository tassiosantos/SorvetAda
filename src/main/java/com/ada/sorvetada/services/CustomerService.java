package com.ada.sorvetada.services;

import com.ada.sorvetada.dtos.CustomerDto;
import com.ada.sorvetada.entities.Customer;
import com.ada.sorvetada.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAll() {
        return null;
    }


//    public Object getById(Long id) {
//        Optional<Client> optCliente = clientRepository.findById(id);
//        Client cliente = optCliente.orElseThrow(() -> new RuntimeException("Não existe cliente com esse id"));
//        return cliente;
//    }

    public CustomerDto createNewClient(CustomerDto cliente) {
        return cliente;
    }

    public Object getByName(String name) {
        return null;
    }

    public void deleteClient(Long id) {

    }
}
