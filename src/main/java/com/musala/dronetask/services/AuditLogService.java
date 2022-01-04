package com.musala.dronetask.services;

import com.musala.dronetask.entities.AuditLog;
import com.musala.dronetask.interfaces.BatteryService;
import com.musala.dronetask.repositories.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final DroneService droneService;
    private final AuditLogRepository logRepository;
    private final BatteryService batteryService;

    private AuditLog save(AuditLog log){
        return logRepository.save(log);
    }

    public void checkDroneBatteryLevel(){

        droneService.findAll()
                .forEach(m -> {
                    int capacity = batteryService.checkBatteryLevel(m.getSerialNumber());
                    AuditLog newAuditLog = new AuditLog();
                    newAuditLog.setDroneBatteryLevel(capacity);
                    newAuditLog.setSerialNumber(m.getSerialNumber());

                    save(newAuditLog);
                });
    }
}
