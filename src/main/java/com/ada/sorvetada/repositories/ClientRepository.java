package com.ada.sorvetada.repositories;

import com.ada.sorvetada.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
