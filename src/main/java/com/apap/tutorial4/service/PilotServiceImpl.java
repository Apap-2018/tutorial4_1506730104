package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.PilotDB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * PilotServiceImpl
 *
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDB pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}

	@Override
	public PilotModel deletePilot(Long id) {
		List<PilotModel> pilotList = pilotDb.findAll();
		for(int i = 0; i < pilotList.size(); i++) {
			if(pilotList.get(i).getId() == id) {
				PilotModel deleted = pilotList.get(i);
				pilotDb.deleteById(id);
				return deleted;
			}
		}
		return null;
	}
}
