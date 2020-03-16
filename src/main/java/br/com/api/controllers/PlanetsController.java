package br.com.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import br.com.api.domain.Planet;
import br.com.api.dto.PlanetDto;
import br.com.api.dto.QuantityDto;
import br.com.api.services.PlanetsService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/planets")
public class PlanetsController {
	
	@Autowired
	private PlanetsService planetsService;

	@ApiOperation(value = "Cria um planeta e retorna a quantidade de aparições do mesmo em filmes.")
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Planeta criado com sucesso."),
	    @ApiResponse(code = 400, message = "Erro de validação de campos"),
	})
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody PlanetDto dto) {
		Planet object = dto.fromEntity();
		Integer qtd = planetsService.countFilms(object.getName());
		Planet planet = planetsService.create(object);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/api/v1/planets/{id}")
				.buildAndExpand(planet.getId())
				.toUri();
		
		return ResponseEntity
				.created(uri)
				.body(new QuantityDto(qtd));
	}
	
	@ApiOperation(value = "Lista todos os planetas.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Listagem realizada com sucesso."),
	})
	@GetMapping
	public ResponseEntity<List<PlanetDto>> getAll() {
		List<Planet> list = planetsService.getAll();
		List<PlanetDto> dtos = list
				.stream()
				.map(planet -> new PlanetDto(planet))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
	
	@ApiOperation(value = "Busca um planeta por id.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
	    @ApiResponse(code = 404, message = "Planeta não encontrado."),
	    @ApiResponse(code = 503, message = "Erro de integração."),
	})
	@GetMapping("/{id}")
	public ResponseEntity<PlanetDto> findById(@PathVariable String id) {
		Planet planet = planetsService.findById(id);
		return ResponseEntity.ok().body(new PlanetDto(planet));
	}

	@ApiOperation(value = "Busca um planeta por nome.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
	    @ApiResponse(code = 404, message = "Planeta não encontrado."),
	    @ApiResponse(code = 503, message = "Erro de integração."),
	})
	@GetMapping("/findByName")
	public ResponseEntity<PlanetDto> findByName(@RequestParam(value = "name") String name) {
		Planet planet = planetsService.findByName(name);
		return ResponseEntity.ok().body(new PlanetDto(planet));
	}
	
	@ApiOperation(value = "Remove um planeta.")
	@ApiResponses(value = {
	    @ApiResponse(code = 204, message = "Removido com sucesso."),
	    @ApiResponse(code = 404, message = "Planeta não encontrado."),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<PlanetDto> delete(@PathVariable String id) {
		planetsService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
