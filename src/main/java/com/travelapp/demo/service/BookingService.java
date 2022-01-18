package com.travelapp.demo.service;

import java.util.List;
import java.util.Map;

import com.travelapp.demo.entity.Booking;

public interface BookingService {

	public Map<String, Object> bookTravel(int cid, int tid, Booking theBooking);

	public String cancelBooking(int cid, int tid);

	public List<Booking> getBookings(int id, int tid);

}
