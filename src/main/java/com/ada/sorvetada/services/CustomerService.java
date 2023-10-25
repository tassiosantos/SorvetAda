package com.ada.sorvetada.services;

import com.ada.sorvetada.dtos.CustomerDto;
import com.ada.sorvetada.entities.Customer;
import com.ada.sorvetada.repositories.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public List<Customer> getAllActive() {
        boolean active = true;
        return customerRepository.findByActive(active);
    }


    public Customer getById(Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        Customer cliente = optCustomer.orElseThrow(() -> new RuntimeException("Does not exist customer by id"));
        return cliente;
    }


    public CustomerDto createNewCustomer(Customer customer) {
        return CustomerDto.builder().id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail()).cpf(customer.getCpf())
                .password(customer.getPassword()).active(customer.isActive()).build();
    }

    public List<Customer> getByName(String name) {
        return customerRepository.findByName(name);
    }

    public void deleteClient(Long id) {
        customerRepository.deleteById(id);
    }

    public void activateDisableCustomer(boolean active, Long id) {
        customerRepository.activeUser(active, id);
    }

    public CustomerDto saveCustumer(CustomerDto customerDTO) {
        Customer customer = new Customer(customerDTO.getName(),
                customerDTO.getCpf(), customerDTO.getEmail(), passwordEncoder.encode(customerDTO.getPassword()),
                customerDTO.isActive());
        Customer savedCustomer = customerRepository.save(customer);
        return createNewCustomer(savedCustomer);
    }

    public CustomerDto updateCustomer(CustomerDto customerDTO) {
        Optional<Customer> optCustomer = customerRepository.findById(customerDTO.getId());
        Customer cliente = optCustomer.orElseThrow(() -> new RuntimeException("Não existe cliente com esse id"));
        if (customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty())
            cliente.setEmail(customerDTO.getEmail());
        if (customerDTO.getName() != null && !customerDTO.getName().isEmpty())
            cliente.setName(customerDTO.getName());
        Customer savedCustomer = customerRepository.save(cliente);
        return createNewCustomer(savedCustomer);
    }

}
