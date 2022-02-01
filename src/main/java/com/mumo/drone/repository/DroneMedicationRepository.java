package com.mumo.drone.repository;

import com.mumo.drone.entity.Drone;
import com.mumo.drone.entity.DroneMedication;
import com.mumo.drone.entity.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneMedicationRepository extends JpaRepository<DroneMedication, Integer> {

    Optional<DroneMedication> findByDrone(Drone drone);

    Page<DroneMedication> findByDrone(Pageable pageable, Drone drone);

    Page<DroneMedication> findByDroneAndActive(Pageable pageable, Drone drone, boolean active);

    Optional<DroneMedication> findByMedication(Medication medication);
}
