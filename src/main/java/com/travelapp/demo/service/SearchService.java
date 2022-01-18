package com.travelapp.demo.service;

import java.util.List;

import com.travelapp.demo.entity.TravelOptions;

public interface SearchService {
	
	public List<TravelOptions> searchFlights(String source, String destination, String date);
	
	public List<TravelOptions> searchBuses(String source, String destination, String date);

	public List<TravelOptions> searchTrain(String source, String destination, String date);

}
