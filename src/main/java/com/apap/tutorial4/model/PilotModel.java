package com.apap.tutorial4.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author USER
 *
 */
@Entity
@Table(name = "pilot")
public class PilotModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "fly_hour", nullable = false)
	private int flyHour;
	
	@OneToMany(mappedBy = "pilot", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<FlightModel> pilotFlight;
}
