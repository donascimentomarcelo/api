package br.com.api.services;

import java.util.List;

import br.com.api.domain.Planet;
import br.com.api.dto.PlanetDto;

public interface PlanetsService {

	Planet create(PlanetDto dto);

	Integer countFilms(String name);

	List<Planet> getAll();

	Planet findById(String id);

	Planet findByName(String name);

	void delete(String id);

}
