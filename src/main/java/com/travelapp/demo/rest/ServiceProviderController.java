package com.travelapp.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelapp.demo.entity.Booking;
import com.travelapp.demo.entity.TravelOptions;
import com.travelapp.demo.service.BookingService;
import com.travelapp.demo.service.ServiceProviderService;

@RestController
@RequestMapping("/serviceprovider")
public class ServiceProviderController {
	
	private ServiceProviderService serviceProviderService;
	
	private BookingService bookingService;
	
	
	@Autowired
	public ServiceProviderController(ServiceProviderService serviceProviderService,BookingService bookingService) {
		this.serviceProviderService = serviceProviderService;
		this.bookingService=bookingService;
	}

	@PostMapping("{id}/travelOption")
	public String addTravel(@PathVariable int id, @RequestBody TravelOptions theOptions)
	{
		return serviceProviderService.addTravelOption(id, theOptions);
	}
	
	@GetMapping("{id}/travelOption")
	public List<TravelOptions> getAllTravelOptions(@PathVariable int id) {
		
		 return serviceProviderService.getAllTravelOptions(id);
	}
	
	@GetMapping("{sid}/travelOption/{id}")
	public TravelOptions getTravelOption(@PathVariable("sid") int sid, @PathVariable("id") int id){
		return serviceProviderService.getTravelOption(sid,id);
	}
	
	@PutMapping("{id}/travelOption")
	public TravelOptions updateTravel(@RequestBody TravelOptions theTravelOptions,@PathVariable("id") int id) {
		
		return serviceProviderService.updateTravel(id,theTravelOptions);
		
	}
	
	@GetMapping("{id}/bookings/{tid}")
	public List<Booking> getBookings(@PathVariable("id")int id,@PathVariable("tid")int tid){
		
		return bookingService.getBookings(id,tid);
	}
	
}
