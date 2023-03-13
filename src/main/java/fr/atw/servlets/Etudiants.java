package fr.atw.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.atw.beans.Equipe;
import fr.atw.beans.Etudiant;
import fr.atw.formulaires.FormulaireInsertionEtudiant;
import fr.atw.formulaires.FormulaireModificationEquipe;
import fr.atw.outils.EnregistreurFichier;
import fr.atw.outils.GenerateurEquipes;
import fr.atw.outils.LecteurCSV;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class Etudiants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Etudiant> listeEtudiants;
	private List<Equipe> listeEquipes;
	
	private int nbEquipes;
	
	public void init() throws ServletException{
		this.listeEtudiants = new ArrayList<Etudiant>();
		this.listeEquipes = new ArrayList<Equipe>();
		this.nbEquipes = 2;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeEtudiants", this.listeEtudiants);
		request.setAttribute("nbEquipes", this.nbEquipes);
		request.setAttribute("listeEquipes", this.listeEquipes);
		
		for(int i=0; i<this.nbEquipes; i++) {
			if(this.listeEquipes.size() < i + 1) {
				this.listeEquipes.add(new Equipe(i+1, "Equipe " + Integer.toString(i+1)));
			}
		}
		
		System.out.println("##############################");
		
		for(int i=0; i<this.listeEquipes.size(); i++) {
			System.out.println(this.listeEquipes.get(i).toString());
		}
		
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
		request.setAttribute("listeEtudiants", this.listeEtudiants);
		request.setAttribute("listeEquipes", this.listeEquipes);
		request.setAttribute("nbEquipes", this.nbEquipes);
		
		if(request.getParameter("ajouterEtudiant") != null) {
			FormulaireInsertionEtudiant formulaireInsertionEtudiant = new FormulaireInsertionEtudiant();
			
			this.listeEtudiants.add(formulaireInsertionEtudiant.verifierEtudiant(request));
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
		} else if(request.getParameter("submitNbEquipes") != null) {
			if(request.getParameter("nbEquipe") != "")
			{
				for(int i = this.nbEquipes; i<Integer.parseInt(request.getParameter("nbEquipe")); i++) {
					this.listeEquipes.add(new Equipe(i+1, "Equipe "+Integer.toString(i+1)));
				}
				
				for(int i = Integer.parseInt(request.getParameter("nbEquipe")); i<this.nbEquipes; i++) {
					this.listeEquipes.get(this.listeEquipes.size()-1).viderEquipe();
					this.listeEquipes.remove(this.listeEquipes.size()-1);
				}
				this.nbEquipes = Integer.parseInt(request.getParameter("nbEquipe"));
		        request.setAttribute("nbEquipes", this.nbEquipes);
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").forward(request, response);
		} else if(request.getParameter("submitEquipesAleatoire") != null) {
			if(this.listeEtudiants.size() >= this.nbEquipes) {
				GenerateurEquipes generateurEquipes = new GenerateurEquipes(this.nbEquipes, this.listeEtudiants, this.listeEquipes);
				generateurEquipes.genererEquipesAleatoire();
			}
			request.setAttribute("listeEquipes", this.listeEquipes);
			this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").forward(request, response);
		} else if (request.getParameter("validerEquipe") != null) { 
			FormulaireModificationEquipe formulaireModificationEquipe = new FormulaireModificationEquipe();
		}else {
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
			this.getServletContext().getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);

		}

		
		
	}  
}
