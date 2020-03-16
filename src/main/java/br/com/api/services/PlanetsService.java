package br.com.api.services;

import java.util.List;

import br.com.api.domain.Planet;

public interface PlanetsService {

	Planet create(Planet planet);

	Integer countFilms(String name);

	List<Planet> getAll();

	Planet findById(String id);

	Planet findByName(String name);

	void delete(String id);

}
