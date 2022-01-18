package com.travelapp.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelapp.demo.entity.TravelOptions;
import com.travelapp.demo.repository.TravelOptionRepository;

@Service
public class SearchServiceImpl implements SearchService {
	
	private TravelOptionRepository travelOptionRepo;
	
	@Autowired
	public SearchServiceImpl(TravelOptionRepository theOptionRepository) {
		travelOptionRepo=theOptionRepository;
	}

	@Override
	public List<TravelOptions> searchFlights(String source, String destination, String date) {
		List<TravelOptions> list= travelOptionRepo.searchFlights(source,destination,date);
		 list.forEach(e->{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				try{
				   c.setTime(sdf.parse(e.getDate().toString()));
				}catch(ParseException s){
				   s.printStackTrace();
				 }
				c.add(Calendar.DAY_OF_MONTH, 1); 
				e.setDate(c.getTime());
		 });
		 return list;
	}

	@Override
	public List<TravelOptions> searchBuses(String source, String destination, String date) {
		List<TravelOptions> list= travelOptionRepo.searchBuses(source,destination,date);
		 list.forEach(e->{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				try{
				   c.setTime(sdf.parse(e.getDate().toString()));
				}catch(ParseException s){
				   s.printStackTrace();
				 }
				c.add(Calendar.DAY_OF_MONTH, 1); 
				e.setDate(c.getTime());
		 });
		 return list;
	}

	@Override
	public List<TravelOptions> searchTrain(String source, String destination, String date) {
		List<TravelOptions> list= travelOptionRepo.searchTrains(source,destination,date);
		 list.forEach(e->{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				try{
				   c.setTime(sdf.parse(e.getDate().toString()));
				}catch(ParseException s){
				   s.printStackTrace();
				 }
				c.add(Calendar.DAY_OF_MONTH, 1); 
				e.setDate(c.getTime());
		 });
		 return list;
	}

}
