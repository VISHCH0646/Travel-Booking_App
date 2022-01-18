package com.travelapp.demo.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelapp.demo.entity.TravelOptions;
import com.travelapp.demo.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

	private SearchService searchService;
	
	@Autowired
	public SearchController(SearchService theSearchService) {
		searchService=theSearchService;
	}
	
	@GetMapping("/flight")
	public List<TravelOptions> searchFlight(@RequestParam(value="source") String source,
			@RequestParam(value="destination") String destination,
			@RequestParam(value="date") String date
			){
		return searchService.searchFlights(source, destination, date);
	
	}
	
	@GetMapping("/bus")
	public List<TravelOptions> searchBus(@RequestParam(value="source") String source,
			@RequestParam(value="destination") String destination,
			@RequestParam(value="date") String date
			){
		return searchService.searchBuses(source, destination, date);
	
	}
	
	@GetMapping("/train")
	public List<TravelOptions> searchTrain(@RequestParam(value="source") String source,
			@RequestParam(value="destination") String destination,
			@RequestParam(value="date") String date
			){
		return searchService.searchTrain(source, destination, date);
	
	}
	
}
