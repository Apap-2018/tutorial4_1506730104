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
	void addPilot(PilotModel pilot);
}
