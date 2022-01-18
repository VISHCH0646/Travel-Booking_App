package com.travelapp.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelapp.demo.entity.ServiceProvider;
import com.travelapp.demo.entity.TravelOptions;
import com.travelapp.demo.exception.CustomerNotFoundException;
import com.travelapp.demo.repository.ServiceProviderRepository;
import com.travelapp.demo.repository.TravelOptionRepository;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {
	
	private ServiceProviderRepository serviceProviderRepo;
	
	private TravelOptionRepository travelOptionsRepo;

	@Autowired
	public ServiceProviderServiceImpl(ServiceProviderRepository theServiceProvider,TravelOptionRepository theTravelOptions) {
		serviceProviderRepo=theServiceProvider;
		travelOptionsRepo=theTravelOptions;
	}
	@Override
	public String addTravelOption(int id, TravelOptions theOptions) {
		ServiceProvider sp=serviceProviderRepo.getById(id);
		List<TravelOptions> list=new ArrayList<>();
		if(sp.isAuthenticated()) {
			if(sp.getSptype()==theOptions.getSptype()) {
				list.add(theOptions);
				theOptions.setServiceProvider(sp);
				sp.setTravelOptions(list);
				serviceProviderRepo.save(sp);
				return theOptions.getSptype()+" has been added.";
			}
			else {
				throw new CustomerNotFoundException("Operation Not Allowed: Service Provider is of type: "+sp.getSptype());
			}
		}
		else {
			throw new CustomerNotFoundException("Service Provider not Authenticated ID: "+id);
		}
	}
	@Override
	public List<TravelOptions> getAllTravelOptions(int id) {
		ServiceProvider sp=serviceProviderRepo.getById(id);
		if(sp.isAuthenticated()) {
			return sp.getTravelOptions();
		}
		throw new CustomerNotFoundException("Service Provider not Authenticated ID: "+id);
	}
	@Override
	public TravelOptions getTravelOption(int sid, int id) {
		ServiceProvider sp=serviceProviderRepo.getById(sid);
		if(sp.isAuthenticated()) {
			TravelOptions to=travelOptionsRepo.findById(id).get();
			if(to.getServiceProvider().getId()==sid) {
				System.out.println(to);
				return to;
			}
			else {
				//throw exception
				throw new CustomerNotFoundException("No TravelOption Found with id:"+id+"for SP ID: "+id);
			}
		}
		else {
			//throw Exception
			throw new CustomerNotFoundException("Service Provider not Authenticated ID: "+id);
		}
	}
	
	@Override
	public TravelOptions updateTravel(int id, TravelOptions theTravelOptions) {
		
		ServiceProvider sp=serviceProviderRepo.getById(id);
		if(sp.isAuthenticated()) {
			TravelOptions to=travelOptionsRepo.findById(theTravelOptions.getId()).get();
			if(to.getServiceProvider().getId()==id) {
				if(sp.getSptype()==theTravelOptions.getSptype()) {
					List<TravelOptions> list=new ArrayList<>();
					list.add(theTravelOptions);
					theTravelOptions.setServiceProvider(sp);
					sp.setTravelOptions(list);
					serviceProviderRepo.save(sp);
					return theTravelOptions;
				}
				else {
					throw new CustomerNotFoundException("Operation Not Allowed: Service Provider is of type: "+sp.getSptype());
				}
			}
			throw new CustomerNotFoundException("No TravelOption Found with id:"+theTravelOptions.getId()+" for SP ID: "+id);
		}
		throw new CustomerNotFoundException("Service Provider not Authenticated ID: "+id);
	}
}
