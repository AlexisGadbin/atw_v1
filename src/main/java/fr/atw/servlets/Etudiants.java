package fr.atw.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.atw.beans.Etudiant;
import fr.atw.formulaires.FormulaireInsertionEtudiant;
import fr.atw.outils.EnregistreurFichier;
import fr.atw.outils.LecteurCSV;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class Etudiants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Etudiant> listeEtudiants;
	
	public void init() throws ServletException{
		this.listeEtudiants = new ArrayList<Etudiant>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeEtudiants", this.listeEtudiants);
		
		String page;
		
        if (request.getParameterMap().containsKey("page")) {
        	page = request.getParameter("page");
        } else {
        	page = "";
        }
		
		switch(page) {
			case "etudiants":
				this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
				break;
			case "equipes":
				this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").include(request, response);
				break;
			default:
				this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
				break;
		}    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("ajouterEtudiant") != null) {
			FormulaireInsertionEtudiant formulaireInsertionEtudiant = new FormulaireInsertionEtudiant();
			
			this.listeEtudiants.add(formulaireInsertionEtudiant.verifierEtudiant(request));
		} else {
			Part part = request.getPart("fichier");
			
			String path = this.getServletContext().getRealPath("/WEB-INF/ressources");
			EnregistreurFichier enregistreur = new EnregistreurFichier(part, path);
			if(!enregistreur.getNomFichier().isEmpty()) {
				enregistreur.ecrireFichier();
				LecteurCSV lecteurCsv = new LecteurCSV(path + "\\" + enregistreur.getNomFichier());
				List<List<String>> output = lecteurCsv.getOutput();
				for(int i=0; i<output.size(); i++) {
					if(output.get(i).size() == 5) {
						this.listeEtudiants.add(new Etudiant(output.get(i).get(0), output.get(i).get(1), output.get(i).get(2), output.get(i).get(3), output.get(i).get(4)));
					}
				}
			}

		}

		request.setAttribute("listeEtudiants", this.listeEtudiants);
		 this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
	}  
}
