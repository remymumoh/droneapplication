package com.mumo.drone.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class DroneDto {

    @NotNull
    @Max(100)
    private String serialNumber;

    @NotNull
    private String modelId;

    @NotNull
    @Max(500)
    private Integer weightLimit;

    @NotNull
    @Max(100)
    @Min(0)
    private Integer battery;
}
