package com.travelapp.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="service_provider")
public class ServiceProvider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="authenticated")
	private boolean authenticated;
	
	@Enumerated(EnumType.STRING)
	@Column(name="sptype")
	private ServiceProviderTypes sptype;
	
	@JsonIgnore
	@OneToMany(mappedBy = "serviceProvider",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<TravelOptions> travelOptions;
	
	@JsonIgnore
	@OneToMany(mappedBy = "b_serviceProvider",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Booking> bookings;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public ServiceProviderTypes getSptype() {
		return sptype;
	}

	public void setSptype(ServiceProviderTypes sptype) {
		this.sptype = sptype;
	}
	
	

	public List<TravelOptions> getTravelOptions() {
		return travelOptions;
	}

	public void setTravelOptions(List<TravelOptions> travelOptions) {
		this.travelOptions = travelOptions;
	}
	

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "ServiceProvider [id=" + id + ", name=" + name + ", password=" + password + ", authenticated="
				+ authenticated + ", sptype=" + sptype + "]";
	}
	
	public void add(TravelOptions temp) {
		if(travelOptions==null)
		{
			travelOptions=new ArrayList<>();
		}
		travelOptions.add(temp);
		temp.setServiceProvider(this);
	}
	
}
