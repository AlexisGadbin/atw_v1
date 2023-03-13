<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Equipes</title>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<h1> Page �quipes </h1>
		<br />
		
		<div class="gestionEquipe">
			<form method="post" action="etudiants">
				<label for="nbEquipe"> Nombre d'�quipes :</label>
				<input type="number" id="nbEquipe" name="nbEquipe" min="1" max="12" value="${ nbEquipes }"> 
				
				<input type="submit" name="submitNbEquipes" value="Valider le nombre d'�quipes"/>
			</form>
		</div>
		<br />
		<div>
			<form method="post" action="etudiants">
				<input type="submit" name="submitEquipesAleatoire" value="G�n�rer al�atoirement des �quipes"/>
			</form>			
		</div>
		
		<br />
		<hr>
		<br />
		
		<div class="etudiantSansEquipe">
			<h3> Etudiants sans �quipe : </h3>
			<c:if test="${ !listeEtudiants.isEmpty() }">
				<ul>
					<c:forEach items="${ listeEtudiants }" var="i">
						<c:if test="${ i.getNumeroEquipe() == -1 }">
							<li>
								<p> 
									<c:out value="${ i.getPrenom() }" /> 
									<c:out value="${ i.getNom().toUpperCase() }" /> 
									<c:out value="(${ i.getGenre() })" />
									 - 
									<c:out value="${ i.getFormationPrecedente() }" />
									<c:out value="${ i.getSitePrecedent() }" />
								</p>
							</li>
						</c:if> 
					</c:forEach>
				</ul>
			</c:if>
		</div>
		
		<br />
		<hr>
		<br />
		
		<div class="listeEquipes" style="display:flex; gap:50px"> 
			<c:forEach begin='0' end='${ nbEquipes - 1}' var='i'>
				<div>
					<form method="post" action="etudiants">
						<input name="numeroEquipe" value="${ listeEquipes.get(i).getNumero() }" hidden />
						<input style="font-weight:bold;" type="text" name="nomEquipe" id="nomEquipe" value="<c:out value="${ listeEquipes.get(i).getNom() }" />"> 

						<c:if test="${ listeEquipes.size() > i }">
							<c:forEach items="${ listeEquipes.get(i).getEtudiants() }" var="etudiant">
								<p> 
									<c:out value="${ etudiant.getNom().toUpperCase() }  ${ etudiant.getPrenom() }" /> 
									<button type="submit" name="supprimerEtudiant" value="${etudiant.getId()}">X</button>
								</p>
							</c:forEach>
						</c:if>
						<c:if test="${ listeEquipes.get(i).getEtudiants().size() < 1 }">
							<br>
							<br>
						</c:if>
						<select name="ajouterEtudiantEquipe" id="ajouterEtudiantEquipe">
							<option value="null">--Selectionner un �tudiant--</option>
							<c:forEach items="${ listeEtudiants}" var="etudiantSansEquipe">
								<c:if test="${ etudiantSansEquipe.getNumeroEquipe() == -1 }">
									<option value="${ etudiantSansEquipe.getId() }"> 
										<c:out value="${ etudiantSansEquipe.getPrenom() } ${ etudiantSansEquipe.getNom().toUpperCase() }" /> 
									</option>
								</c:if>
							</c:forEach>
						</select>
						<br>
						<br>
						<input type="submit" name="validerEquipe" value="Valider l'�quipe"/>
					</form>
				</div>
			</c:forEach>
		</div>
		
		<br />
		<hr>
		<br />
		
		<button type="button"> Exporter les �quipes </button>
		
		
	</body>
</html>