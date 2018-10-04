package com.apap.tutorial4.repository;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author USER
 *
 */
public interface FlightDB extends JpaRepository<FlightModel, Long>{
	
}
