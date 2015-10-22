package com.cargacerta.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteExecutionPoint {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(nullable = false)
	private RouteExecution routeExecution;
	@Column(nullable = false)
	private double latitude;
	@Column(nullable = false)
	private double longitude;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public RouteExecution getRouteExecution() {
		return routeExecution;
	}
	
	public void setRouteExecution(RouteExecution routeExecution) {
		this.routeExecution = routeExecution;
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
