package br.com.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.api.domain.Planet;

public class PlanetDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "O campo NOME é obrigatório")
	@Length(min = 3, max=80, message = "Digite um NOME de 3 a 80 caracteres")
	public String name;
	
	@NotEmpty(message = "O campo CLIMA é obrigatório")
	@Length(min = 3, max=80, message = "Digite um CLIMA de 3 a 80 caracteres")
	public String climate;
	
	@NotEmpty(message = "O campo TERRENO é obrigatório")
	@Length(min = 3, max=80, message = "Digite um TERRENO de 3 a 80 caracteres")
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
