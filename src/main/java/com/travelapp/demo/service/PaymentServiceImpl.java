package com.travelapp.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelapp.demo.entity.Booking;
import com.travelapp.demo.entity.Payment;
import com.travelapp.demo.entity.TravelOptions;
import com.travelapp.demo.exception.CustomerNotFoundException;
import com.travelapp.demo.repository.BookingRepository;
import com.travelapp.demo.repository.PaymentRepository;
import com.travelapp.demo.repository.TravelOptionRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	private PaymentRepository paymentRepository;
	
	private TravelOptionRepository travelOptionRepository;
	
	private BookingRepository bookingRepository;

	@Autowired
	public PaymentServiceImpl(PaymentRepository paymentRepository, TravelOptionRepository travelOptionRepository,
			BookingRepository bookingRepository) {
		super();
		this.paymentRepository = paymentRepository;
		this.travelOptionRepository = travelOptionRepository;
		this.bookingRepository = bookingRepository;
	}

	@Override
	public String makePayment(int bid, Payment thePayment) {
		
		Booking b=bookingRepository.findById(bid).get();
		
		
		if(b==null) {
			//throw exception
			throw new CustomerNotFoundException("No Booking Available with ID: "+bid);
		}
		TravelOptions t=travelOptionRepository.getById(b.getTravelOptions().getId());
		Date d=t.getDate();
		System.out.println(d);
		
		if(b.getStatus().equals("CANCELLED"))
			return "Booking is Cancelled";
		
		if(b.isPayment_status())
			return "Payment is Already successful";
		
		Payment p=paymentRepository.getCardInfo(thePayment.getCardno());
		if(p!=null && p.getExpdate().equals(thePayment.getExpdate()) && p.getCvv()==thePayment.getCvv()) {
			
			
			if(t.getSeats()>=b.getSeats()) {
				int s=t.getSeats()-b.getSeats();
				t.setSeats(s);
			}
			else
				return "Seats Not Available";
			
			b.setPayment_status(true);
			bookingRepository.save(b);
			return "Rs. "+b.getFare()+" Payment Successful... Booking is confirmed";
		}
		else {
			return "Invalid Card Details";
		}
	}
	
	
	
}
