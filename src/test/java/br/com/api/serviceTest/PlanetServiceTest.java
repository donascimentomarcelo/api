package br.com.api.serviceTest;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.api.domain.Planet;
import br.com.api.repositories.PlanetsRepository;
import br.com.api.services.PlanetsService;
import br.com.api.services.impl.PlanetsServiceImpl;

@SpringBootTest
public class PlanetServiceTest {
	
	private static final String DAGOBAH = "Dagobah";

	private static final String TESTE = "Teste";

	private static final String ID = "1";

	@MockBean
	private PlanetsServiceImpl planetsServiceImpl;
	
	
	private PlanetsService planetsService;

	@MockBean
	private PlanetsRepository planetRepository;
	
	@Mock
	private Planet planet;
	
	@BeforeEach
	public void setUp() {
		planetsService = new PlanetsServiceImpl(planetRepository);
		planet = new Planet();
		planet.setName(DAGOBAH);
		planet.setClimate(TESTE);
		planet.setTerrain(TESTE);		
	}
	
	@Test
	public void itShouldCreatesAPlanet() {
		planetsService.create(planet);
		verify(planetRepository).save(planet);
	}
	

	@Test
	public void itShouldDeleteAPlanet() {
		planetsService.delete(ID);
	}
}
