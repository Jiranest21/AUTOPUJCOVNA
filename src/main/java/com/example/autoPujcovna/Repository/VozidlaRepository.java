package com.example.autoPujcovna.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.autoPujcovna.Model.Vozidla;

@Repository
public interface VozidlaRepository extends JpaRepository<Vozidla, Long> {

}
