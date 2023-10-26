package com.ada.sorvetada.repositories;

import com.ada.sorvetada.entities.Icecream;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IcecreamRepository extends JpaRepository<Icecream, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Icecream i SET i.unitPrice = :unitPrice WHERE i.id = :id")
    void updateUnitPrice(@Param("unitPrice") Double unitPrice, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Icecream i SET i.urlPhoto = :urlPhoto WHERE i.id = :id")
    void updateUrlPhoto(@Param("urlPhoto") String urlPhoto, @Param("id") Long id);




}
