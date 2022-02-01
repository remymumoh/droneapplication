package com.mumo.drone.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class DroneDto {

    @NotNull
    @Size(max = 100)
    private String serialNumber;

    @NotNull
    private String model;

    @NotNull
    @Max(500)
    private Integer weightLimit;

    @NotNull
    @Max(100)
    @Min(0)
    private Integer battery;
}
