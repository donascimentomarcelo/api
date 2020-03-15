package br.com.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.domain.Planet;
import br.com.api.dto.PlanetDto;
import br.com.api.dto.SwapiPlanetDto;
import br.com.api.exceptions.ObjectNotFoundException;
import br.com.api.repositories.PlanetsRepository;
import br.com.api.services.IntegrationsService;
import br.com.api.services.PlanetsService;

@Service
public class PlanetsServiceImpl implements PlanetsService {
	
	private static final String OBJECT_NOT_FOUND = "planet not found in database or in swapi.co.";

	@Autowired
	private PlanetsRepository planetsRepository;
	
	@Autowired
	private IntegrationsService integrationsService;

	/**
	 * Salva um planeta no banco de dados local.
	 */
	@Override
	public Planet create(final PlanetDto dto) {
		Planet planet = dto.fromEntity();
		return planetsRepository.save(planet);
	}

	/**
	 * Conta quantas vezes o planeta apareceu em cada filme.
	 */
	@Override
	public Integer countFilms(final String name) {
		List<SwapiPlanetDto> results = integrationsService.findPlanetByName(name)
																		.getResults();
		return results.size() > 0 ?  results.get(0).getFilms().size() : 0;		
	}

	/**
	 * Lista todos os planetas do banco de dados local.
	 */
	@Override
	public List<Planet> getAll() {
		return planetsRepository.findAll();
	}

	/**
	 * Busca por id no banco local e caso nao encontre, busca na API https://swapi.co/.
	 */
	@Override
	public Planet findById(final String id) {
		Optional<Planet> planet = planetsRepository.findById(id);
		
		planet = findByIdIfPlanetIsNull(id, planet);

		return planet
				.orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND));
	}

	/**
	 * Pesquisa planeta por id na API https://swapi.co/, caso o planeta nao exista 
	 * no banco de dados.
	 * 
	 * @param id
	 * @param planet
	 * @return retorna um optional de um planeta.
	 */
	private Optional<Planet> findByIdIfPlanetIsNull(final String id, Optional<Planet> planet) {
		if (!planet.isPresent()) {
			SwapiPlanetDto dto = integrationsService.findById(id);
			
			if (dto != null) {
				planet = Optional.ofNullable(new Planet(
						dto.getName(), 
						dto.getClimate(), 
						dto.getTerrain()));
			}
		}
		return planet;
	}

	/**
	 * Busca por nome no banco local e caso nao encontre, busca na API https://swapi.co/.
	 */
	@Override
	public Planet findByName(final String name) {
		Optional<Planet> planet = planetsRepository.findByName(name);
		planet = findByNameIfPlanetIsNull(name, planet);

		return planet
				.orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND));
	}

	/**
	 * Pesquisa planeta por nome na API https://swapi.co/, caso o planeta nao exista 
	 * no banco de dados.
	 * 
	 * @param name
	 * @param planet
	 * @return retorna um optional de um planeta.
	 */
	private Optional<Planet> findByNameIfPlanetIsNull(final String name, Optional<Planet> planet) {
		if (!planet.isPresent()) {
			List<SwapiPlanetDto> results = integrationsService.findPlanetByName(name)
																			.getResults();
			if (results.size() > 0) {
				planet = Optional.ofNullable(new Planet(
						results.get(0).getName(), 
						results.get(0).getClimate(), 
						results.get(0).getTerrain()));
			}
		}
		return planet;
	}

	
	/**
	 * Deleta planeta do banco de dados local.
	 */
	@Override
	public void delete(String id) {
		planetsRepository.deleteById(id);
	}

}
