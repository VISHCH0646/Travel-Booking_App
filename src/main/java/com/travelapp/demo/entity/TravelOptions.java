package com.travelapp.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author LAXMAN
 *
 */
@Entity
@Table(name="travel_option")
public class TravelOptions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="source")
	private String source;
	
	@Column(name="destination")
	private String destination;

	@Column(name="fare")
	private double fare;
	
	@Column(name="seats")
	private int seats;
	
	@Column(name="date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	@Column(name="time")
	private String time;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private ServiceProviderTypes sptype;
	
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="service_provider_id")
	private ServiceProvider serviceProvider;
	
	@JsonIgnore
	@OneToMany(mappedBy = "travelOptions",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Booking> bookings;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public ServiceProviderTypes getSptype() {
		return sptype;
	}

	public void setSptype(ServiceProviderTypes sptype) {
		this.sptype = sptype;
	}
	
	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "TravelOption [id=" + id + ", source=" + source + ", destination=" + destination + ", fare=" + fare + ", date="
				+ date + ", time=" + time + ", serviceProvider=" + serviceProvider + "]";
	}
	
	
}
