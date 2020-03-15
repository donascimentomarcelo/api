package br.com.api.dto;

import java.io.Serializable;

import br.com.api.domain.Planet;

public class PlanetDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String name;
	public String climate;
	public String terrain;
	
	public PlanetDto() {
		
	}
	
	public PlanetDto(Planet planet) {
		this.name = planet.getName();
		this.climate = planet.getClimate();
		this.terrain = planet.getTerrain();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Planet fromEntity() {
		return new Planet(getName(), getClimate(), getTerrain());
	}
}
