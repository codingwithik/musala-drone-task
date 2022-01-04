package com.musala.dronetask.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name = "MS_MEDICATION", indexes = {
		@Index(columnList = "code", unique = true)
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medication implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="drone_id", nullable = false)
	private Long droneId;
	@Column(name="name", nullable = false, length = 100)
	private String name;
	@Column(name = "weight", nullable = false)
	private Integer weight;
	@Column(name="code", nullable = false, unique = true, length = 70)
	private String code;
	@Column(name="image", nullable = true, columnDefinition = "Text")
	private String image;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Medication(long id, String name, Integer weight, String code, String image) {
		this.droneId = id;
		this.name = name;
		this.weight = weight;
		this.code =  code;
		this.image = image;
	}

	@PrePersist
	private void setCreatedAt() {
		createdAt = new Date();
	}

	@PreUpdate
	private void setUpdatedAt() {
		updatedAt = new Date();
	}
}
