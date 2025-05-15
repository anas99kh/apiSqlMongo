package com.genesis.api.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.api.models.Personne;
import com.genesis.api.services.PersonneService;

@RestController
@RequestMapping("/v1/api/personnes")
public class PersonneController {
	
	@Autowired
	private PersonneService personneService;
	
	
	@GetMapping
	public List<Personne> getAllPersonne(){
		return personneService.getAllPersonnes();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Personne> getPersonneById(@PathVariable String id){
		Optional<Personne> personne = personneService.getPersonneById(id);
		return personne.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	
	@PostMapping
	public Personne createPersonne(@RequestBody Personne personne){
		return personneService.createPersonne(personne);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Personne> udpatePerseonne(@PathVariable String id, @RequestBody Personne personne){
		Personne updatePersonne = personneService.updatePersonne(id, personne);
		return updatePersonne != null ? ResponseEntity.ok(updatePersonne) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePersonne(@PathVariable String id){
		personneService.deletePersonne(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/search")
	public List<Personne> searchPersonnes(@RequestParam String pays){
		return personneService.searchPersonneByPays(pays);
	}
	
}
