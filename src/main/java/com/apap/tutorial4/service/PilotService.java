package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;

/**
 * 
 * PilotService
 *
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	PilotModel deletePilot(Long id);
	PilotModel updatePilotName(Long id, String name);
	PilotModel updatePilotFlyHour(Long id, int flyHour);
	void addPilot(PilotModel pilot);
}
