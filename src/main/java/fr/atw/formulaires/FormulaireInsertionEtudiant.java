package fr.atw.formulaires;

import fr.atw.beans.Etudiant;
import jakarta.servlet.http.HttpServletRequest;

public class FormulaireInsertionEtudiant {
	private Etudiant etudiant;
	private String message;

	public Etudiant verifierEtudiant(HttpServletRequest request) {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String genre = request.getParameter("genre");
		String sitePrecedent = request.getParameter("sitePrecedent");
		String formationPrecedente = request.getParameter("formationPrecedente");
		
		// TODO GERER EXCEPTION
		
		return etudiant = new Etudiant(nom, prenom, genre, sitePrecedent, formationPrecedente);
	}
	
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	


}
