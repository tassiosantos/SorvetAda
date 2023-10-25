package com.ada.sorvetada.repositories;

import com.ada.sorvetada.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByActive(boolean active);

    @Query("SELECT c FROM Customer c WHERE c.name ILIKE concat('%', :name, '%') ORDER BY c.name")
    List<Customer> findByName(@Param("nome") String nome);

    @Modifying
    @Query("UPDATE Customer c SET c.active = :active WHERE c.id = :id")
    void activeUser(@Param("active") boolean active, @Param("id") Long id);

    Optional<Customer> findByEmail(String email);

    Page<Customer> findAllByActive(Pageable pageable);

    Page<Customer> findAll(Pageable pageable);
}
