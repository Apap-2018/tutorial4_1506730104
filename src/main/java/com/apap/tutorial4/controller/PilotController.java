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
	
	@RequestMapping("pilot/delete/{id}")
	public String deletePilot(@PathVariable (value = "id") Long id, Model model) {
		PilotModel deleted = pilotService.deletePilot(id);
		model.addAttribute("pilot", deleted);
		return "delete-pilot";
	}
	
	@RequestMapping("/pilot/update/id/{id}/new-name/{name}")
	public String updatePilotName(@PathVariable (value = "id") Long id, @PathVariable String name, Model model) {
		PilotModel updated = pilotService.updatePilotName(id, name);
		model.addAttribute("pilot", updated);
		return "update-pilot";
	}
	
	@RequestMapping("/pilot/update/id/{id}/new-fly-hour/{flyHour}")
	public String updatePilotFlyHour(@PathVariable (value = "id") Long id, @PathVariable int flyHour, Model model) {
		PilotModel updated = pilotService.updatePilotFlyHour(id, flyHour);
		model.addAttribute("pilot", updated);
		return "update-pilot";
	}
}
