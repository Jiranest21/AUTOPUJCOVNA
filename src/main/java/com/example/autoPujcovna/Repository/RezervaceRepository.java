package com.example.autoPujcovna.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.autoPujcovna.Model.Rezervace;


@Repository
public interface RezervaceRepository extends JpaRepository<Rezervace, Long> {
    @Query("SELECT r FROM Rezervace r WHERE r.vozidla.id = :carId AND (r.startDate >= :startDate AND r.endDate <= :endDate)")
    List<Rezervace> findOverlappingReservations(@Param("carId") Long carId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
