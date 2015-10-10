package com.cargacerta.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RoutePoint {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Route route;
	@Column(nullable = false)
	private double latitue;
	@Column(nullable = false)
	private double longitude;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public double getLatitue() {
		return latitue;
	}
	
	public void setLatitue(double latitue) {
		this.latitue = latitue;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
