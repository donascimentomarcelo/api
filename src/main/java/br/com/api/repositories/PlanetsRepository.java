package br.com.api.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.api.domain.Planet;

@Repository
public interface PlanetsRepository extends MongoRepository<Planet, String>{

	Optional<Planet> findByName(String name);

}
