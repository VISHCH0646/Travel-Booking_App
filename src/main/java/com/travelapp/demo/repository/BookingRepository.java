package com.travelapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelapp.demo.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
