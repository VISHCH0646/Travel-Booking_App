package com.travelapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelapp.demo.entity.Passengers;

public interface PassengerRepository extends JpaRepository<Passengers, Integer> {

}
