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
	public FlightModel deleteFlight(String flight_number) {
		FlightModel deleted = flightDb.findByFlightNumber(flight_number);
		flightDb.delete(deleted);
		return deleted;
	}

	@Override
	public FlightModel updateFlight(String flight_number, String origin, String destination, String time,
			String licenseNumber) {
		flightDb.findByFlightNumber(flight_number).setOrigin(origin);
		flightDb.findByFlightNumber(flight_number).setDestination(destination);
		//flightDb.findByFlightNumber(flight_number).setTime(time);
		//flightDb.findByFlightNumber(flight_number).setPilot(pilotDb.findByLicenseNumber(licenseNumber));
		FlightModel updated = flightDb.findByFlightNumber(flight_number);
		return updated;
	}

	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}
}
