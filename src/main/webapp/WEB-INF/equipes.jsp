<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Equipes</title>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<h1> Page équipes </h1>
		<hr>
		
		<div class="gestionEquipe">
			<form method="post" action="etudiants">
				<label for="nbEquipe"> Nombre d'équipes :</label>
				<input type="number" id="nbEquipe" name="nbEquipe" min="1" max="12" value="${ nbEquipes }"> 
				
				<input type="submit" name="submitNbEquipes" value="submitNbEquipes"/>
			</form>
		</div>
		
		<p><c:out value="Nombre d'équipes : ${ nbEquipes }" /></p>
		
		<div>
			<form method="post" action="etudiants">
				<input type="submit" name="submitEquipesAleatoire" value="Générer aléatoirement des équipes"/>
			</form>			
		</div>
		<hr>
		
		<div class="etudiantSansEquipe">
			<h3> Etudiants sans équipe : </h3>
			<c:if test="${ !listeEtudiants.isEmpty() }">
				<c:forEach items="${ listeEtudiants }" var="i">
					<c:if test="${ i.getNumeroEquipe() == -1 }">
						<p> <c:out value="${ i.getPrenom() }" /> </p>
						<p> <c:out value="${ i.getNom() }" /> </p>
						<p> <c:out value="${ i.getGenre() }" /> </p>
						<p> <c:out value="${ i.getSitePrecedent() }" /> </p>
						<p> <c:out value="${ i.getFormationPrecedente() }" /> </p>
					</c:if> 
				</c:forEach>
			</c:if>
		</div>
		<hr>
		
		<div class="listeEquipes" style="display:flex; gap:50px"> 
			<c:forEach begin='0' end='${ nbEquipes - 1}' var='i'>
				<div>
					<h2> <c:out value="Equipe ${ i + 1}" /> </h2>
					<c:if test="${ listeEquipes.size() > i }">
						<c:forEach items="${ listeEquipes.get(i).getEtudiants() }" var="etudiant">
							<p> <c:out value="${ etudiant.getNom() }  ${ etudiant.getNumeroEquipe() }" /> </p>
						</c:forEach>
					</c:if>
				</div>
			</c:forEach>
		</div>
		<hr>
		
		<button type="button"> Exporter les équipes </button>
		
		
	</body>
</html>