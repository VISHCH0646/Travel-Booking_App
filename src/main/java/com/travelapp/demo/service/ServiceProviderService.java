package com.travelapp.demo.service;

import java.util.List;

import com.travelapp.demo.entity.TravelOptions;

public interface ServiceProviderService {
	
	public String addTravelOption(int id, TravelOptions theOptions);

	public List<TravelOptions> getAllTravelOptions(int id);

	public TravelOptions getTravelOption(int sid, int id);

	public TravelOptions updateTravel(int id, TravelOptions theTravelOptions);
}
