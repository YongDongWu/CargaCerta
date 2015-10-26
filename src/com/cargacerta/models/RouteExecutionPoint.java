package com.cargacerta.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	private RouteExecutionPeriod routeExecutionPeriod;
	@Column(nullable = false)
	private double latitude;
	@Column(nullable = false)
	private double longitude;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date executionDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public RouteExecutionPeriod getRouteExecutionPeriod() {
		return routeExecutionPeriod;
	}
	
	public void setRouteExecutionPeriod(RouteExecutionPeriod routeExecutionPeriod) {
		this.routeExecutionPeriod = routeExecutionPeriod;
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
	
	public Date getExecutionDate() {
		return executionDate;
	}
	
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
}
