package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.FlightDB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * FlightServiceImpl
 *
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDB flightDb;

	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}

	@Override
	public FlightModel deleteFlight(Long id) {
		List<FlightModel> flightList = flightDb.findAll();
		for(int i = 0; i < flightList.size(); i++) {
			if(flightList.get(i).getId() == id) {
				FlightModel deleted = flightList.get(i);
				flightDb.deleteById(id);
				return deleted;
			}
		}
		return null;
	}
}
