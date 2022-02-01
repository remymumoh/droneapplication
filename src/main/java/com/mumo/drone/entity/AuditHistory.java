package com.mumo.drone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audit_history")
public class AuditHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_id_generator")
    @SequenceGenerator(name = "audit_id_generator", allocationSize = 5)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String droneSerialNumber;

    @Column(nullable = false)
    private Integer battery;

    @Column(nullable = false)
    private Instant timeStamp;

    public static AuditHistory createAudit(Drone drone) {
        return new AuditHistory(drone);
    }

    private AuditHistory(Drone drone) {
        this.battery = drone.getBattery();
        this.droneSerialNumber = drone.getSerialNumber();
        this.timeStamp = Instant.now();
    }
}
