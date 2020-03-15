package br.com.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.domain.Planet;
import br.com.api.dto.PlanetDto;
import br.com.api.dto.QuantityDto;
import br.com.api.services.PlanetsService;

@RestController
@RequestMapping("/api/v1/planets")
public class PlanetsControllers {
	
	@Autowired
	private PlanetsService planetsService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody PlanetDto dto) {
		Integer qtd = planetsService.countFilms(dto.getName());
		Planet planet = planetsService.create(dto);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/api/v1/planets/{id}")
				.buildAndExpand(planet.getId())
				.toUri();
		
		return ResponseEntity
				.created(uri)
				.body(new QuantityDto(qtd));
	}
	
	@GetMapping
	public ResponseEntity<List<PlanetDto>> getAll() {
		List<Planet> list = planetsService.getAll();
		List<PlanetDto> dtos = list
				.stream()
				.map(planet -> new PlanetDto(planet))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlanetDto> findById(@PathVariable String id) {
		Planet planet = planetsService.findById(id);
		return ResponseEntity.ok().body(new PlanetDto(planet));
	}

	@GetMapping("/findByName")
	public ResponseEntity<PlanetDto> findByName(@RequestParam(value = "name") String name) {
		Planet planet = planetsService.findByName(name);
		return ResponseEntity.ok().body(new PlanetDto(planet));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PlanetDto> delete(@PathVariable String id) {
		planetsService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
