package com.genesis.api.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import com.genesis.api.models.Personne;
import com.genesis.api.repositories.PersonneRepository;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

@Service
public class PersonneService {
	
	@Autowired
	private MongoClient mongoClient;
	
	@Autowired
	private MongoConverter mongoConverter; 
	
	
	@Autowired
	private PersonneRepository personneRepository;
	
	
	public List<Personne> searchPersonneByPays(String pays){
		final List<Personne> personnes = new ArrayList<>();
		
		MongoCollection<Document> collection= mongoClient.getDatabase("learnNoSql").getCollection("Personnes");
		
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
			    new Document("index", "default")
			            .append("text", 
			    new Document("query", pays)
			                .append("path", "pays"))), 
			    new Document("$sort", 
			    new Document("prenom", 1L)), 
			    new Document("$limit", 2L)));
		
		result.forEach(doc -> personnes.add(mongoConverter.read(Personne.class, doc)));
		return personnes;
	}
	
	public List<Personne> getAllPersonnes(){
		return personneRepository.findAll();
	}
	
	public Optional<Personne> getPersonneById(String id){
		return personneRepository.findById(id);
	}
	
	public Personne createPersonne(Personne personne) {
		if(personneRepository.existsByNomAndPrenom(personne.getNom(), personne.getPrenom())) {
			throw new DuplicateKeyException("X Cette personne exite déjà");
		}
		return personneRepository.save(personne);
	}
	
	public Personne updatePersonne(String id, Personne newPersonne) {
		return getPersonneById(id)
				.map(personne -> {
					personne.setNom(newPersonne.getNom());
					personne.setPrenom(newPersonne.getPrenom());
					personne.setPays(newPersonne.getPays());
					personne.setTechnos(newPersonne.getTechnos());
					return personneRepository.save(personne);
				}).orElse(null);
	}
	
	public void deletePersonne(String id) {
		personneRepository.deleteById(id);
	}
	
	
}
