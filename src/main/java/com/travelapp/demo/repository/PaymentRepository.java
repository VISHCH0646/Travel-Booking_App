package com.travelapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travelapp.demo.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	@Query(value="select * from payments where cardno=?1",nativeQuery = true)
	public Payment getCardInfo(String cardno); 
}
