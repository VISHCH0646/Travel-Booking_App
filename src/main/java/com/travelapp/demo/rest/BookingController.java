package com.travelapp.demo.rest;


import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.travelapp.demo.entity.Booking;
import com.travelapp.demo.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	private BookingService bookingService;
	
	public BookingController(BookingService theBookingService) {
		bookingService=theBookingService;
	}
	
	@PostMapping("{cid}/flight/{tid}")
	public Map<String, Object> bookFlight(@PathVariable("cid") int cid,
			@PathVariable("tid") int tid,
			@RequestBody Booking theBooking
			) {
		return bookingService.bookTravel(cid,tid,theBooking);
	}
	
	@PostMapping("{cid}/bus/{tid}")
	public Map<String, Object> bookBus(@PathVariable("cid") int cid,
			@PathVariable("tid") int tid,
			@RequestBody Booking theBooking
			
			) {
		return bookingService.bookTravel(cid,tid,theBooking);
	}
	
	@PostMapping("{cid}/train/{tid}")
	public Map<String, Object> bookTrain(@PathVariable("cid") int cid,
			@PathVariable("tid") int tid,
			@RequestBody Booking theBooking
			
			) {
		return bookingService.bookTravel(cid,tid,theBooking);
	}
	
	@GetMapping("{cid}/cancel/{tid}")
	public String cancelBooking(@PathVariable("cid") int cid,@PathVariable int tid) {
		
		return bookingService.cancelBooking(cid,tid);
	}

}
