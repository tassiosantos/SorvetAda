package com.ada.sorvetada.services;

import com.ada.sorvetada.dtos.CustomerDto;
import com.ada.sorvetada.entities.Customer;
import com.ada.sorvetada.repositories.CustomerRepository;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

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


    public CustomerDto getById(Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        Customer customer = optCustomer.orElseThrow(() -> new RuntimeException("Does not exist customer by id"));
        return createNewCustomer(customer);
    }

    public CustomerDto getByEmail(String email) {
        Optional<Customer> optCustomer = customerRepository.findByEmail(email);
        Customer customer = optCustomer.orElseThrow(() -> new RuntimeException("Does not exist customer by email"));
        return createNewCustomer(customer);
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

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void activateDisableCustomer(boolean active, Long id) {
        customerRepository.activeUser(active, id);
    }

/*    public CustomerDto saveCustumer(CustomerDto customerDTO) {
        Customer customer = new Customer(customerDTO.getName(),
                customerDTO.getCpf(), customerDTO.getEmail(), passwordEncoder.encode(customerDTO.getPassword()),
                customerDTO.isActive());
        Customer savedCustomer = customerRepository.save(customer);
        return createNewCustomer(savedCustomer);
    }*/
public CustomerDto saveCustumer(CustomerDto customerDTO) {
    Customer customer = new Customer(customerDTO.getName(),
            customerDTO.getCpf(), customerDTO.getEmail(), customerDTO.getPassword(),
            customerDTO.isActive());
    Customer savedCustomer = customerRepository.save(customer);
    return createNewCustomer(savedCustomer);
}

    public CustomerDto updateCustomer(CustomerDto customerDTO) {
        Optional<Customer> optCustomer = customerRepository.findById(customerDTO.getId());
        Customer customer = optCustomer.orElseThrow(() -> new RuntimeException("Doesn't exist client by id"));
        if (customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty())
            customerDTO.setEmail(customerDTO.getEmail());
        if (customerDTO.getName() != null && !customerDTO.getName().isEmpty())
            customer.setName(customerDTO.getName());
        Customer savedCustomer = customerRepository.save(customer);
        return createNewCustomer(savedCustomer);
    }


}
