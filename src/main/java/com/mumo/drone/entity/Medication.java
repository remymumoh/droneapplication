package com.mumo.drone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_id_generator")
    @SequenceGenerator(name = "med_id_generator", allocationSize = 5)
    @Column(updatable = false, nullable = false)
    private Integer id;

    private String name;

    private Integer weight;

    private String code;

    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "medication")
    private List<DroneMedication> droneMedication;
}
