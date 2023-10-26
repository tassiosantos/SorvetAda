package com.ada.sorvetada.repositories;

import com.ada.sorvetada.entities.Icecream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IcecreamRepository extends JpaRepository<Icecream, Long> {
}
