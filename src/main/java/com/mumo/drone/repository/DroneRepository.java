package com.mumo.drone.repository;

import com.mumo.drone.entity.Drone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Integer> {

    Optional<Drone> findBySerialNumber(String serialNumber);

    Page<Drone> findAllByStateIgnoreCase(Pageable pageable, String state);

}
