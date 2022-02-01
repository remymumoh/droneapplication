package com.mumo.drone.controller;

import com.mumo.drone.controller.dto.DroneDto;
import com.mumo.drone.controller.dto.DroneProjection;
import com.mumo.drone.controller.dto.LoadDroneDto;
import com.mumo.drone.controller.dto.ResponseDto;
import com.mumo.drone.entity.Drone;
import com.mumo.drone.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/drone")
public class DispatchController {

    private final DroneService droneService;

    @PostMapping
    public ResponseEntity<DroneDto> registerDrone(@RequestBody @Valid DroneDto droneDto) {
        return new ResponseEntity<>(droneService.registerDrone(droneDto), OK);
    }

    @GetMapping
    public ResponseEntity<Page<Drone>> getDrones(Pageable pageable,
                                                 @RequestParam(value = "state", required = false) String state) {
        return new ResponseEntity<>(droneService.getDrones(pageable, state), OK);
    }

    @GetMapping("/{droneId}")
    @Cacheable(value = "drone", key = "#droneId")
    public ResponseEntity<Drone> getDroneById(@PathVariable Integer droneId) {
        return new ResponseEntity<>(droneService.getDroneById(droneId), OK);
    }

    @GetMapping("/{droneId}/loaded-meds")
    @Cacheable(value = "drone", key = "#droneId")
    public ResponseEntity<DroneProjection> getDroneLoadedMeds(@PathVariable Integer droneId) {
        return new ResponseEntity<>(droneService.getLoadedMeds(droneId), OK);
    }

    @GetMapping("/{droneId}/loaded-meds/active")
    @Cacheable(value = "drone", key = "#droneId")
    public ResponseEntity<DroneProjection> getActiveDroneLoadedMeds(@PathVariable Integer droneId) {
        return new ResponseEntity<>(droneService.getActiveLoadedMeds(droneId), OK);
    }

    @PostMapping("/{droneId}/load-drone")
    public ResponseEntity<ResponseDto> loadDrone(@RequestBody @Valid LoadDroneDto loadDroneDto, @PathVariable Integer droneId) {
        return new ResponseEntity<>(droneService.loadDrone(loadDroneDto, droneId), OK);
    }
}
