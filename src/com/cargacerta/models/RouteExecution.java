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

@Entity
public class RouteExecution {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Driver driver;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Truck truck;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Route route;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public Truck getTruck() {
		return truck;
	}
	
	public void setTruck(Truck truck) {
		this.truck = truck;
	}
	
	public Route getRoute() {
		return route;
	}
	
	public void setRoute(Route route) {
		this.route = route;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
