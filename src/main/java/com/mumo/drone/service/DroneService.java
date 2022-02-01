package com.mumo.drone.service;

import com.mumo.drone.controller.dto.*;
import com.mumo.drone.entity.Drone;
import com.mumo.drone.entity.DroneMedication;
import com.mumo.drone.entity.Medication;
import com.mumo.drone.enumeration.Model;
import com.mumo.drone.enumeration.State;
import com.mumo.drone.repository.DroneMedicationRepository;
import com.mumo.drone.repository.DroneRepository;
import com.mumo.drone.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

import static com.mumo.drone.entity.Drone.saveDrone;
import static com.mumo.drone.entity.DroneMedication.saveShipment;
import static com.mumo.drone.enumeration.State.IDLE;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DroneService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DroneMedicationRepository droneMedicationRepository;

    public DroneDto registerDrone(DroneDto droneDto) {
        if (droneRepository.findBySerialNumber(droneDto.getSerialNumber()).isEmpty()) {
            //check if drone model submitted exists
            if(Arrays.stream(Model.values()).anyMatch(n -> n.name().equalsIgnoreCase(droneDto.getModel()))){
                log.info("Saving drone");
                droneRepository.save(saveDrone(droneDto));
            }else {
                throw new EntityNotFoundException("Model does not exist");
            }
        } else {
            throw new EntityExistsException("Serial number exists");
        }
        return droneDto;
    }

    public Page<Drone> getDrones(Pageable pageable, String state) {
        if (hasText(state)) {
            return droneRepository.findAllByStateIgnoreCase(pageable, state.trim());
        }
        return droneRepository.findAll(pageable);
    }

    public Drone getDroneById(Integer id) {
        return droneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Drone not found"));
    }

    public DroneProjection getLoadedMeds(Integer droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException("Drone not found"));
        Page<DroneMedication> droneMedicationOptional = droneMedicationRepository.findByDrone(Pageable.unpaged(), drone);

        return DroneProjection.builder()
                .droneId(drone.getId())
                .loadedMeds(convertToDto(droneMedicationOptional))
                .build();
    }

    public DroneProjection getActiveLoadedMeds(Integer droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException("Drone not found"));
        Page<DroneMedication> droneMedications = droneMedicationRepository.findByDroneAndActive(Pageable.unpaged(), drone, true);

        return DroneProjection.builder()
                .droneId(drone.getId())
                .loadedMeds(convertToDto(droneMedications))
                .build();
    }

    public ResponseDto loadDrone(LoadDroneDto loadDroneDto, Integer droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException("Drone not found"));
        Medication medication = medicationRepository.findByCode(loadDroneDto.getMedicationCode())
                .orElseThrow(() -> new EntityNotFoundException("Medication not found"));
        String message;

        //check drone state
        if (Objects.equals(drone.getState(), IDLE.name())) {
            //check weight limit vs medication weight
            if (drone.getWeightLimit() > medication.getWeight()) {
                //check drone battery
                if (drone.getBattery() > 25) {
                    droneMedicationRepository.save(saveShipment(drone, medication));
                    drone.setState(State.valueOf(drone.getState()).next().name());
                    message = "success";
                } else {
                    message = "Drone battery is below 25%";
                }
            } else {
                message = "Medication exceeds drone weight limit";
            }
        } else {
            message = "Drone is not in " + IDLE.name() + " state";
        }

        return ResponseDto.builder()
                .message(message)
                .build();
    }

    private List<MedicationProjection> convertToDto(Page<DroneMedication> droneMedications) {
        List<MedicationProjection> medicationProjections = new ArrayList<>();
        for (DroneMedication droneMedication : droneMedications.toList()) {
            MedicationProjection medicationProjection = MedicationProjection.builder()
                    .code(droneMedication.getMedication().getCode())
                    .weight(droneMedication.getMedication().getWeight())
                    .name(droneMedication.getMedication().getName())
                    .active(droneMedication.isActive())
                    .build();
            medicationProjections.add(medicationProjection);
        }

        return medicationProjections;
    }

}
