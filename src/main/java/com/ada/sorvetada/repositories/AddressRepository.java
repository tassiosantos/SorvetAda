package com.ada.sorvetada.repositories;

import com.ada.sorvetada.dtos.AddressDto;
import com.ada.sorvetada.entities.Address;
import com.ada.sorvetada.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCustomer(Optional<Customer> byId);

}
