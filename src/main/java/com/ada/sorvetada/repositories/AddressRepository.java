package com.ada.sorvetada.repositories;

import com.ada.sorvetada.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
