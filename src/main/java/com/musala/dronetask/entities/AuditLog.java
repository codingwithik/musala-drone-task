package com.musala.dronetask.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MS_AUDITLOG")
@Getter
@Setter
public class AuditLog implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "serial_number", length=100, nullable = false, unique=true)
	private String serialNumber;
	@Column(name = "drone_battery_level", length=100, nullable = false)
	private Integer droneBatteryLevel;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@PrePersist
	private void setCreatedAt() {
		createdAt = new Date();
	}

}
