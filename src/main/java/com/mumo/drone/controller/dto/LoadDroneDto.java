package com.mumo.drone.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoadDroneDto {

    @NotNull
    private String medicationCode;
}
