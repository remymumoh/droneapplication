package com.mumo.drone.repository;

import com.mumo.drone.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {

    Optional<Medication> findByCode(String code);
}
