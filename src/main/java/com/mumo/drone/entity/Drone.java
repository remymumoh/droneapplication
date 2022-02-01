package com.mumo.drone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mumo.drone.controller.dto.DroneDto;
import com.mumo.drone.enumeration.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;

import static com.mumo.drone.enumeration.State.IDLE;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "drone")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drone_id_generator")
    @SequenceGenerator(name = "drone_id_generator", allocationSize = 5, initialValue = 100)
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer weightLimit;

    @Column(nullable = false)
    private Integer battery;

    @Column(nullable = false)
    private String state;

    @JsonIgnore
    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<DroneMedication> droneMedication;

    public static Drone saveDrone(DroneDto droneDto) {
        return new Drone(droneDto);
    }

    private Drone(DroneDto droneDto) {
        this.battery = droneDto.getBattery();
        this.model = Model.valueOf(droneDto.getModel().toUpperCase(Locale.ROOT)).name();
        this.serialNumber = droneDto.getSerialNumber();
        this.weightLimit = droneDto.getWeightLimit();
        this.state = IDLE.name();
    }
}
