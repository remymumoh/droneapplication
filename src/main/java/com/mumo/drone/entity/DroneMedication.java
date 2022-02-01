package com.mumo.drone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "drone_medication")
public class DroneMedication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drone_med_seq")
    @SequenceGenerator(name = "drone_med_seq", allocationSize = 5)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Drone drone;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medication medication;

    private boolean active;

    public static DroneMedication saveShipment(Drone drone, Medication medication) {
        return new DroneMedication(drone, medication);
    }

    private DroneMedication(Drone drone, Medication medication) {
        this.drone = drone;
        this.medication = medication;
        this.active = true;
    }

}
