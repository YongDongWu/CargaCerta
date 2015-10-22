package com.cargacerta.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Truck {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn
	private TruckType truckType;
	@Column
	private String plate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TruckType getTruckType() {
		return truckType;
	}
	
	public void setTruckType(TruckType truckType) {
		this.truckType = truckType;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public void setPlate(String plate) {
		this.plate = plate;
	}
}
