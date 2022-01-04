package com.musala.dronetask.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.musala.dronetask.enums.DroneModel;
import com.musala.dronetask.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MS_DRONE", indexes = { 
		@Index(columnList = "serial_number", unique = true)
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
	@Enumerated(EnumType.STRING)
	private DroneModel model;
	@Column(name = "state", length=100, nullable = false)
	@Enumerated(EnumType.STRING)
	private DroneState state;
	@Column(name = "medications")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
