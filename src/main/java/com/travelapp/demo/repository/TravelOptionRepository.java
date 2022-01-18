package com.travelapp.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travelapp.demo.entity.TravelOptions;

public interface TravelOptionRepository extends JpaRepository<TravelOptions, Integer> {
	
	@Query(value="select * from travel_option where source=?1 and destination=?2 and date=?3 and type='FLIGHT'",nativeQuery = true)
	public List<TravelOptions> searchFlights(String source,String destination,String date);
	
	@Query(value="select * from travel_option where source=?1 and destination=?2 and date=?3 and type='BUS'",nativeQuery = true)
	public List<TravelOptions> searchBuses(String source,String destination,String date);
	
	@Query(value="select * from travel_option where source=?1 and destination=?2 and date=?3 and type='TRAIN'",nativeQuery = true)
	public List<TravelOptions> searchTrains(String source, String destination, String date);

}
