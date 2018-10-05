package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;

/**
 * 
 * FlightService
 *
 */
public interface FlightService {
	void addFlight(FlightModel flight);
	FlightModel deleteFlight(String flight_number);
	FlightModel updateFlight(String flight_number, String origin, String destination, String time, String licenseNumber);
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
}
