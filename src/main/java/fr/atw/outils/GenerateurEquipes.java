package fr.atw.outils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;

public class GenerateurEquipes {
	private int nbEquipes;
	private List<Etudiant> etudiants;
	private List<Equipe> equipes;
	
	public GenerateurEquipes(int nbEquipes, List<Etudiant> etudiants, List<Equipe> equipes) {
		this.nbEquipes = nbEquipes;
		this.etudiants = etudiants;
		this.equipes = equipes;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setNbEquipes(int nbEquipes) {
		this.nbEquipes = nbEquipes;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
	public void genererEquipesAleatoire()
	{
		int nbEtudiantsEquipe = this.etudiants.size() / this.nbEquipes;
		int nbEtudiantsRestant = this.etudiants.size() % this.nbEquipes;
		
		ArrayList<Integer> random = new ArrayList<Integer>();
		
		for(int i=0; i < this.equipes.size(); i++) {
			this.equipes.get(i).viderEquipe();;
		}
		
		for(int i=0; i < this.nbEquipes; i++)
		{
			for(int j=0; j < nbEtudiantsEquipe; j++)
			{
				Random r = new Random();
				int randomInt = r.nextInt(this.etudiants.size());
				while(random.contains(randomInt)) {
					randomInt = r.nextInt(this.etudiants.size());
				}
				
				random.add(randomInt);
				
				Etudiant etudiant = this.etudiants.get(randomInt);
				this.equipes.get(i).ajouterEtudiant(etudiant);
			}
		}
		
		for(int i=0; i < nbEtudiantsRestant; i++) {
			for(int j=0; j < this.etudiants.size(); j++) {
				if(!random.contains(j)) {
					this.equipes.get(i).ajouterEtudiant(this.etudiants.get(j));
					this.etudiants.get(j).setNumeroEquipe(i+1);
					random.add(j);
					break;
				}
			}	
		}
		
	}
	
	
	

}
