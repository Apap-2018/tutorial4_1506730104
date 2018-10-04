package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

import java.util.Optional;

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
 * PilotController
 *
 */
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value="/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value="/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = {"pilot/view-pilot/{licenseNumber}"})
	public String viewPilot(@PathVariable (value = "licenseNumber") String licenseNumber, Model model){
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		model.addAttribute("pilotFlight", pilot.getPilotFlight());
		return "view-pilot";
	}
	
	@RequestMapping("pilot/delete/{licenseNumber}")
	public String deletePilot(@PathVariable (value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel deleted = pilotService.deletePilot(licenseNumber);
		model.addAttribute("pilot", deleted);
		return "delete-pilot";
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/new-name/{name}/new-fly-hour/{flyHour}")
	public String updatePilot(@PathVariable (value = "licenseNumber") String licenseNumber, @PathVariable String name, @PathVariable int flyHour, Model model) {
		PilotModel updated = pilotService.updatePilot(licenseNumber, name, flyHour);
		model.addAttribute("pilot", updated);
		return "update-pilot";
	}
}
