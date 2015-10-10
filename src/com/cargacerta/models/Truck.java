package com.cargacerta.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Truck {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn
	private TruckType type;
	@Column
	private String plate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TruckType getType() {
		return type;
	}
	
	public void setType(TruckType type) {
		this.type = type;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public void setPlate(String plate) {
		this.plate = plate;
	}
}
