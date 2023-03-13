package fr.atw.formulaires;

import fr.atw.beans.Equipe;
import jakarta.servlet.http.HttpServletRequest;

public class FormulaireModificationEquipe {
	Equipe equipe;
	
	public Equipe modifierEquipe(Equipe equipe, HttpServletRequest requete) {
		this.equipe = equipe;
		
		String nomEquipe = requete.getParameter("nomEquipe");
		String nouvelEtudiant = requete.getParameter("ajouterEtudiant");
		String supprimerEtudiant = requete.getParameter("supprimerEtudiant");
		
		if(supprimerEtudiant != "") {
			this.equipe.supprimerEtudiant(null);
		} else if (nomEquipe != this.equipe.getNom()) {
			this.equipe.setNom(nomEquipe);
		} else if (nouvelEtudiant != "") {
			this.equipe.ajouterEtudiant(null);
		}
		
		return equipe;
	}
}
