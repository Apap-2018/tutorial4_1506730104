package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;

/**
 * 
 * @author USER
 *
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	
	void addPilot(PilotModel pilot);
}
