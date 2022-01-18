package com.travelapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelapp.demo.entity.Admin;

public interface  AdminRepository extends JpaRepository<Admin, Integer> {

}
