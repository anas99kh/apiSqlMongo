package com.genesis.api.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Personnes")
public class Personne {
	private String id;
	private String nom;
	private String prenom;
	private String pays;
	private List<String> technos;
	
	public Personne() {}
	
	public Personne(String id, String nom, String prenom, String pays, List<String> technos) {
		this.nom = nom;
		this.prenom = prenom;
		this.pays = pays;
		this.technos = technos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public List<String> getTechnos() {
		return technos;
	}

	public void setTechnos(List<String> technos) {
		this.technos = technos;
	}
	
	
	
}
