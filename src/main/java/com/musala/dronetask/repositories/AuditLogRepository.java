package com.musala.dronetask.repositories;

import com.musala.dronetask.entities.AuditLog;
import org.springframework.data.repository.CrudRepository;

public interface AuditLogRepository extends CrudRepository<AuditLog, Long> {

}
