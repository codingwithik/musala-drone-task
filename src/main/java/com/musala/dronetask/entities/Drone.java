package com.musala.dronetask.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MS_DRONE", indexes = { 
		@Index(columnList = "serialNumber", unique = true)
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Drone implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "serial_number", length=100, nullable = false, unique=true)
	private String serialNumber;
	@Column(name = "weight_limit", nullable = false)
	private Integer weightLimit;
	@Column(name = "battery_capacity", nullable = false)
	private Integer batteryCapacity;
	@Column(name = "model", length=100, nullable = false)
	private String model;
	@Column(name = "state", length=100, nullable = false)
	private String state;
	@Column(name = "medications")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Medication> medications;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	

	@PrePersist
	private void setCreatedAt() {
		createdAt = new Date();
	}

	@PreUpdate
	private void setUpdatedAt() {
		updatedAt = new Date();
	}
}
