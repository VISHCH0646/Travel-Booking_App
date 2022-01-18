package com.travelapp.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="bookings")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="fare")
	private double fare;
	
	@Column(name="seats")
	private int seats;
	
	@Column(name="booking_date")
	private Date booking_date;
	
	@Column(name="payment_status")
	private boolean payment_status;
	
	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy = "booking",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	List<Passengers> passengers;
	
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="service_provider_id")
	private ServiceProvider b_serviceProvider;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="travel_option_id")
	private TravelOptions travelOptions;

	
	public TravelOptions getTravelOptions() {
		return travelOptions;
	}

	public void setTravelOptions(TravelOptions travelOptions) {
		this.travelOptions = travelOptions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public int getSeats() {
		return seats;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Date getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}

	public boolean isPayment_status() {
		return payment_status;
	}

	public void setPayment_status(boolean payment_status) {
		this.payment_status = payment_status;
	}

	public List<Passengers> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passengers> passengers) {
		this.passengers = passengers;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ServiceProvider getB_serviceProvider() {
		return b_serviceProvider;
	}

	public void setB_serviceProvider(ServiceProvider b_serviceProvider) {
		this.b_serviceProvider = b_serviceProvider;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", fare=" + fare + ", seats=" + seats + ", booking_date=" + booking_date
				+ ", payment_status=" + payment_status + "]";
	}
	
}
