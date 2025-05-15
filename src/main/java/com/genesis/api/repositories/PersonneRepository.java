package com.genesis.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.genesis.api.models.Personne;

public interface PersonneRepository extends MongoRepository<Personne, String> {
	boolean existsByNomAndPrenom(String nom, String prenom);
}
