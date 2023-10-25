package com.ada.sorvetada.repositories;

import com.ada.sorvetada.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
