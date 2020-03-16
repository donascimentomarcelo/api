package br.com.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.domain.Planet;
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

	public PlanetsServiceImpl(PlanetsRepository planetRepository) {
		this.planetsRepository = planetRepository;
	}

	/**
	 * Salva um planeta no banco de dados local.
	 */
	@Override
	public Planet create(final Planet planet) {
		return planetsRepository.save(planet);
	}

	/**
	 * Conta quantas vezes o planeta apareceu em cada filme.
	 */
	@Override
	public Integer countFilms(final String name) {
		return integrationsService.countFilms(name);	
	}

	/**
	 * Lista todos os planetas do banco de dados local.
	 */
	@Override
	public List<Planet> getAll() {
		return planetsRepository.findAll();
	}

	/**
	 * Busca por id no banco local.
	 */
	@Override
	public Planet findById(final String id) {
		Optional<Planet> planet = planetsRepository.findById(id);
		
		return planet
				.orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND));
	}

	/**
	 * Busca por nome no banco local.
	 */
	@Override
	public Planet findByName(final String name) {
		Optional<Planet> planet = planetsRepository.findByName(name);

		return planet
				.orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND));
	}
	
	/**
	 * Deleta planeta do banco de dados local.
	 */
	@Override
	public void delete(String id) {
		planetsRepository.deleteById(id);
	}

}
