package com.musala.dronetask.scheduletask;

import com.musala.dronetask.services.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditLogTask {

    private final AuditLogService logService;

    //@Scheduled(cron = "0 0 0 * * *") // midnight everyday
    @Scheduled(fixedDelay = 1000)
    public void logBatteryCapacity() {

        System.out.println("running audit log");
        logService.checkDroneBatteryLevel();

    }
}
