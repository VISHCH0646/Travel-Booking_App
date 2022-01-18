package com.travelapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelapp.demo.entity.ServiceProvider;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Integer> {

}
