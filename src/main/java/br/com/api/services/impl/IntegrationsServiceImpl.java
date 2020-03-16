package br.com.api.services.impl;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.dto.SwapiPlanetDto;
import br.com.api.dto.SwapiResultDto;
import br.com.api.services.IntegrationsService;

@Service
public class IntegrationsServiceImpl implements IntegrationsService {

	@Value("${integration.swapi.planets}")
	private String swapiOiPlanet;

	@Value("${integration.headers.name}")
	private String headerName;

	@Value("${integration.headers.value}")
	private String headerValue;

	/**
	 * Faz integração com a API https://swapi.co/ para buscar um planeta por nome.
	 */
	@Override
	public SwapiResultDto findPlanetByName(final String name) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<SwapiResultDto> result = null;
		try {
			final String key = "search";
			URI url = getUrl(key, name);
			RequestEntity<?> request = setHeaderConfiguration(url);

			result = restTemplate.exchange(
					url, 
					HttpMethod.GET, 
					request,
					SwapiResultDto.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		return result.getBody();
	}
	
	/**
	 * Configura headers
	 * @param url
	 * @return
	 */
	private RequestEntity<Void> setHeaderConfiguration(final URI url) {
		return RequestEntity
				.get(url)
				.header(headerName, headerValue)
				.accept(MediaType.APPLICATION_JSON)
				.build();
	}

	/**
	 * Configura url
	 * @param key
	 * @param value
	 * @return
	 */
	private URI getUrl(final String key, final String value) {
		return UriComponentsBuilder
				.fromHttpUrl(swapiOiPlanet)
				.queryParam(key, value)
				.build()
				.toUri();
	}

	
	/**
	 * Acessa a API https://swapi.co/ e conta quantas vezes o planeta apareceu em cada filme.
	 */
	@Override
	public Integer countFilms(String name) {
		List<SwapiPlanetDto> results = findPlanetByName(name)
													.getResults();
		return results.size() > 0 ?  results.get(0).getFilms().size() : 0;	
	}

}
