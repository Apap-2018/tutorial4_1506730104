package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;
import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * FlightController
 *
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping("flight/delete/{flight_number}")
	public String deleteFlight(@PathVariable (value = "flight_number") String flight_number, Model model) {
		FlightModel deleted = flightService.deleteFlight(flight_number);
		model.addAttribute("flight", deleted);
		model.addAttribute("pilot", deleted.getPilot());
		return "delete-pilot";
	}
	
	@RequestMapping("/flight/update/flight-number/{flightNumber}/new-origin/{origin}/new-destination/{destination}/new-time/{time}/new-pilot/{pilot}")
	public String updatePilot(@PathVariable (value = "flightNumber") String flightNumber, @PathVariable String origin, @PathVariable String destination, @PathVariable String time, @PathVariable String licenseNumber, Model model) {
		FlightModel updated = flightService.updateFlight(flightNumber, origin, destination, time, licenseNumber);
		model.addAttribute("flight", updated);
		model.addAttribute("pilot", updated.getPilot());
		return "update-flight";
	}
	
	@RequestMapping(value = {"flight/view-flight/{flightNumber}"})
	public String viewFlight(@PathVariable (value = "flightNumber") String flightNumber, Model model){
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("flight", flight);
		model.addAttribute("pilot", flight.getPilot());
		return "view-flight";
	}
}
