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
	
	public String updatePilotName(@PathVariable Optional<String> licenseNumber, @PathVariable Optional<Integer> flyHour, Model model) {
		if (licenseNumber.isPresent()) {
			PilotModel archive = pilotService.updatePilotFlyHour(licenseNumber.get(), flyHour.get());
			
			if(archive == null) {
				model.addAttribute("lisensi", licenseNumber.get());
				return "error-update-flyHour";
			} else {
				model.addAttribute("pilot", archive);
				return "view-pilot-updated";
			}
		} else {
			model.addAttribute("lisensi", licenseNumber.get());
			return "error-update-flyHour";
		}
	}
	
	public String updatePilotFlyHour(@PathVariable Optional<String> licenseNumber, @PathVariable Optional<Integer> flyHour, Model model) {
		if (licenseNumber.isPresent()) {
			PilotModel archive = pilotService.updatePilotFlyHour(licenseNumber.get(), flyHour.get());
			
			if(archive == null) {
				model.addAttribute("lisensi", licenseNumber.get());
				return "error-update-flyHour";
			} else {
				model.addAttribute("pilot", archive);
				return "view-pilot-updated";
			}
		} else {
			model.addAttribute("lisensi", licenseNumber.get());
			return "error-update-flyHour";
		}
	}
}
