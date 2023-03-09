package fr.atw.beans;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
	private String nom;
	private int numero;
	private List<Etudiant> etudiants;
	
	public Equipe(int numero, String nom) {
		this.nom = nom;
		this.numero = numero;
		this.etudiants = new ArrayList<Etudiant>();
	}

	public String getNom() {
		return nom;
	}

	public int getNumero() {
		return numero;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
	public void ajouterEtudiant(Etudiant etudiant) {
		if(!etudiants.contains(etudiant) && etudiant.getNumeroEquipe() != -1) {
			etudiants.add(etudiant);
		}
	}
	
	public void supprimerEtudiant(Etudiant etudiant) {
		if(etudiants.contains(etudiant) && etudiant.getNumeroEquipe() == this.numero) {
			etudiants.remove(etudiant);
		}
	}
}
