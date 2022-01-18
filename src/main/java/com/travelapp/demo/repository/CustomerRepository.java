package com.travelapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelapp.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
