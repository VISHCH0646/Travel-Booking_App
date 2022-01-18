package com.travelapp.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelapp.demo.entity.Booking;
import com.travelapp.demo.entity.Customer;
import com.travelapp.demo.entity.Passengers;
import com.travelapp.demo.entity.ServiceProvider;
import com.travelapp.demo.entity.TravelOptions;
import com.travelapp.demo.exception.CustomerNotFoundException;
import com.travelapp.demo.repository.BookingRepository;
import com.travelapp.demo.repository.CustomerRepository;
import com.travelapp.demo.repository.ServiceProviderRepository;
import com.travelapp.demo.repository.TravelOptionRepository;

@Service
public class BookingServiceImpl implements BookingService {

	private BookingRepository bookingRepo;
	
	private TravelOptionRepository travelOptionRepository;
	
	private CustomerRepository customerRepository;
	
	private ServiceProviderRepository serviceProviderRepository;
	
	@Autowired
	public BookingServiceImpl(BookingRepository theBookingRepository,
			TravelOptionRepository theTravelOptionRepository,
			CustomerRepository theCustomerRepository,
			ServiceProviderRepository theServiceProviderRepository
			) {
		bookingRepo=theBookingRepository;
		travelOptionRepository=theTravelOptionRepository;
		customerRepository=theCustomerRepository;
		serviceProviderRepository=theServiceProviderRepository;
	}

	@Override
	public HashMap<String, Object> bookTravel(int cid, int tid, Booking theBooking) {
		if(!customerRepository.getById(cid).isAuthenticated())
		{
			//throw exception
			throw new CustomerNotFoundException("Customer Not Authenticated with Id: "+cid);
		}
		theBooking.setSeats(theBooking.getPassengers().size());
		Customer c=customerRepository.getById(cid);
		TravelOptions t=travelOptionRepository.getById(tid);
		ServiceProvider s=serviceProviderRepository.getById(t.getServiceProvider().getId());
		theBooking.setBooking_date(new Date());
		theBooking.setPayment_status(false);
		theBooking.setFare(t.getFare()*theBooking.getSeats());
		theBooking.setB_serviceProvider(s);
		theBooking.setCustomer(c);
		theBooking.setTravelOptions(t);
		theBooking.setStatus("ACTIVE");
		for(Passengers p: theBooking.getPassengers()) {
			p.setBooking(theBooking);
		}
		Booking b=bookingRepo.save(theBooking);
		HashMap<String, Object> resp=new HashMap<>();
		resp.put("Booking_id", b.getId());
		resp.put("Source", t.getSource());
		resp.put("Destination", t.getDestination());
		resp.put("Journey Date", t.getDate().toString());
		resp.put("Amount Payable", b.getFare());
		resp.put("Passengers", b.getPassengers());
		resp.put("Journey Time", t.getTime());
		resp.put("Travel Mode", t.getSptype());
		return resp;
	}

	@Override
	public String cancelBooking(int cid, int tid) {
		Customer c=customerRepository.getById(cid);
		if(!c.isAuthenticated()) {
			//throw exception
			throw new CustomerNotFoundException("Customer Not Authenticated or Invalid Customer Id: "+cid);
		}
		Booking b=bookingRepo.getById(tid);
		if(b.getCustomer().getId()!=cid) {
			return "Booking not Found for Customer id: "+cid;
		}
		if(b.getStatus().equals("CANCELLED"))
			throw new CustomerNotFoundException("Booking Already Cancelled Id: "+b.getId());
		TravelOptions t=travelOptionRepository.findById(b.getTravelOptions().getId()).get();
		if(t.getDate().after(new Date())) {
			t.setSeats(t.getSeats()+b.getSeats());
			b.setStatus("CANCELLED");
			bookingRepo.save(b);
			travelOptionRepository.save(t);
			double charges=b.getFare()*0.2;
			return "Booking Cancelled: \n Charges: "+charges+"\nRefund Amount: "+(b.getFare()-charges);
		}
		return null;
	}

	@Override
	public List<Booking> getBookings(int id, int tid) {
		TravelOptions t=travelOptionRepository.getById(tid);
		ServiceProvider s=serviceProviderRepository.getById(id);
		System.out.println(s);
		if( t.getServiceProvider().getId()!=id)
		{
			//throw new exception
			//Travel Option not found for id: +id
			throw new CustomerNotFoundException("Travel Option not found for id: "+s.getId());
		}
		if(!s.isAuthenticated()) {
			//throw new exception
			//Service provider not Authenticated
			throw new CustomerNotFoundException("Service provider not Authenticated Id: "+s.getId());
		}
		
		Stream<Booking> bookings=s.getBookings().stream();
		return bookings.filter(e->e.getTravelOptions().getId()==tid).toList();//
	}
	
	
	
}
