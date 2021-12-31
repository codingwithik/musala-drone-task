package com.musala.dronetask.entities;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MS_AUDITLOG", indexes = { 
		@Index(columnList = "uuid", unique = true),
		@Index(columnList = "email_address", unique = true)
})
@Getter
@Setter
public class AuditLog {

}
