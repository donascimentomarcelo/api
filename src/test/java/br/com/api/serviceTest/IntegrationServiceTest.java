package br.com.api.serviceTest;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.api.domain.Planet;
import br.com.api.dto.SwapiResultDto;
import br.com.api.services.impl.IntegrationsServiceImpl;

@SpringBootTest
public class IntegrationServiceTest {
	
	private static final String DAGOBAH = "Dagobah";

	@MockBean
	private IntegrationsServiceImpl integrationsServiceImpl;
		
	@Mock
	private Planet planet;
	
	@Test
	public void itShouldFindByPlanetName() {
		when(integrationsServiceImpl.findPlanetByName(DAGOBAH))
			.thenReturn(new SwapiResultDto());
	}
	
	@Test
	public void itShouldCountFilms() {
		when(integrationsServiceImpl.countFilms(DAGOBAH))
			.thenReturn(3);
	}
	
}
