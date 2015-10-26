package com.cargacerta.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

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
	@Column(nullable = false)
	private String token;
	@Column
	private double latitude;
	@Column
	private double longitude;
	
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
	
	@JsonIgnore
	public String getToken() {
		return token;
	}
	
	@JsonSetter
	public void setToken(String token) {
		this.token = token;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
