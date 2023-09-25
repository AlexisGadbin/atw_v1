package fr.atw.formulaires;

import java.util.List;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;
import jakarta.servlet.http.HttpServletRequest;

public class FormulaireModificationEquipe {
	Equipe equipe;
	List<Etudiant> listeEtudiants;
	
	public void modifierEquipe(List<Equipe> listeEquipes, List<Etudiant> listeEtudiants, HttpServletRequest requete) {
		this.listeEtudiants = listeEtudiants;
		

		
		int numeroEquipe = Integer.parseInt(requete.getParameter("numeroEquipe"));
		String nomEquipe = requete.getParameter("nomEquipe");
		String nouvelEtudiant = requete.getParameter("ajouterEtudiantEquipe");
		String idEtudiantASupprimer = requete.getParameter("supprimerEtudiant");
		
		for(Equipe e : listeEquipes) {
			if(numeroEquipe == e.getNumero()) {
				this.equipe = e;
			}
		}
		
		System.out.println(nouvelEtudiant);
		
		if(idEtudiantASupprimer != null) {
			Etudiant etudiant = this.equipe.getEtudiant(Integer.parseInt(idEtudiantASupprimer));
			if(etudiant != null) {
				this.equipe.supprimerEtudiant(etudiant);
			}
		} else if (!nouvelEtudiant.equals("null")) {
			System.out.println("ca rentre");
			for(Etudiant e : this.listeEtudiants) {
				if(e.getId() == Integer.parseInt(nouvelEtudiant)) {
					this.equipe.ajouterEtudiant(e);
				}
			}
		} else if (!nomEquipe.equals(this.equipe.getNom())) {
			this.equipe.setNom(nomEquipe);
		} 
	}
}
