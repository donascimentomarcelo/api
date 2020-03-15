package br.com.api.services.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.dto.SwapiPlanetDto;
import br.com.api.dto.SwapiResultDto;
import br.com.api.exceptions.IntegrationException;
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
	 * Faz integração com a API https://swapi.co/ para buscar um planeta por id.
	 */
	@Override
	public SwapiPlanetDto findById(final String id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<SwapiPlanetDto> result = null;
		try {
			URI url = new URI(String.format("%s%s", swapiOiPlanet, id));
			RequestEntity<?> request = setHeaderConfiguration(url);

			result = restTemplate.exchange(
					url, 
					HttpMethod.GET, 
					request,
					SwapiPlanetDto.class);

		} catch (HttpClientErrorException e) {
			return null;
			
		} catch (RestClientException | URISyntaxException e ) {

			throw new IntegrationException(e.getMessage());
		}

		return result.getBody();
	}

	private RequestEntity<Void> setHeaderConfiguration(final URI url) {
		return RequestEntity
				.get(url)
				.header(headerName, headerValue)
				.accept(MediaType.APPLICATION_JSON)
				.build();
	}

	private URI getUrl(final String key, final String value) {
		return UriComponentsBuilder
				.fromHttpUrl(swapiOiPlanet)
				.queryParam(key, value)
				.build()
				.toUri();
	}

}
