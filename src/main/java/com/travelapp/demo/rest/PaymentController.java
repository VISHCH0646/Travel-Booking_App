package com.travelapp.demo.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelapp.demo.entity.Payment;
import com.travelapp.demo.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}



	@PostMapping("/makePayment/{bid}")
	public String makePayment(@RequestBody Payment thePayment,@PathVariable("bid") int bid) {
		
		return paymentService.makePayment(bid,thePayment);
		
	}
	
}
