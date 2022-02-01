package com.mumo.drone.controller;

import com.mumo.drone.controller.dto.DroneDto;
import com.mumo.drone.controller.dto.ResponseDto;
import com.mumo.drone.entity.Drone;
import com.mumo.drone.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/drone")
public class DispatchController {

    private final DroneService droneService;

    @PostMapping
    public ResponseEntity<DroneDto> registerDrone(@RequestBody @Valid DroneDto droneDto) {
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<Drone>> getDrones(Pageable pageable,
                                                 @RequestParam(value = "state", required = false) String state) {
        return null;
    }

    @GetMapping("/{droneId}")
    public ResponseEntity<Drone> getDroneById(@PathVariable Integer droneId) {
        return null;
    }

    @GetMapping("/{droneId}/loaded-meds")
    public ResponseEntity getDroneLoadedMeds(@PathVariable Integer droneId) {
        return null;
    }

    @GetMapping("/{droneId}/loaded-meds/active")
    public ResponseEntity getActiveDroneLoadedMeds(@PathVariable Integer droneId) {
       return null;
    }

    @PostMapping("/{droneId}/load-drone")
    public ResponseEntity<ResponseDto> loadDrone(@RequestBody @Valid LoadDroneDto loadDroneDto, @PathVariable Integer droneId) {
        return null;
    }
}
