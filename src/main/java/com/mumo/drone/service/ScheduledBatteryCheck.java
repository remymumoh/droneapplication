package com.mumo.drone.service;

import com.mumo.drone.entity.Drone;
import com.mumo.drone.repository.AuditHistoryRepository;
import com.mumo.drone.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mumo.drone.entity.AuditHistory.createAudit;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduledBatteryCheck {

    private final DroneRepository droneRepository;
    private final AuditHistoryRepository auditHistoryRepository;

    @Scheduled(fixedRateString = "10000")
    public void checkBatteryLevels() {
        droneRepository.findAll(Pageable.unpaged())
                .toList().forEach(this::createLog);
    }

    @Async
    public void createLog(Drone drone) {
        log.info("Creating log for :" + drone.getSerialNumber());
        auditHistoryRepository.save(createAudit(drone));
    }
}
