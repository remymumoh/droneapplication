package com.mumo.drone.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MedicationProjection {

    private String name;

    private String code;

    private Integer weight;

    private boolean active;
}
