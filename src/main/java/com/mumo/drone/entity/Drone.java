package com.mumo.drone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mumo.drone.enumeration.Model;
import com.mumo.drone.controller.dto.DroneDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @SequenceGenerator(name = "drone_id_generator", allocationSize = 5)
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

}
