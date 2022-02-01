package com.mumo.drone.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class MedicationDto {

    @NotNull
    @Pattern(regexp = "([A-Za-z0-9\\-\\_]+)", message = "Only letters, numbers and dashes are allowed")
    private String name;

    @NotNull
    private Integer weight;

    @NotNull
    @Pattern(regexp = "([A-Z0-9\\_]+)", message = "Only uppercase letters, numbers and underscores are allowed")
    private String code;

    @NotNull
    private String image;
}
