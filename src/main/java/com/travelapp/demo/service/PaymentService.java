package com.travelapp.demo.service;

import com.travelapp.demo.entity.Payment;

public interface PaymentService {

	String makePayment(int bid, Payment thePayment);

}
