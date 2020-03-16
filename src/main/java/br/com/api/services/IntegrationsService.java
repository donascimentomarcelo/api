package br.com.api.services;

import br.com.api.dto.SwapiResultDto;

public interface IntegrationsService {

	SwapiResultDto findPlanetByName(String name);

	Integer countFilms(String name);

}
