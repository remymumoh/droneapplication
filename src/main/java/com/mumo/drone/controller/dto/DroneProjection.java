package com.mumo.drone.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class DroneProjection {

    private Integer droneId;

    private List<MedicationProjection> loadedMeds;
}
