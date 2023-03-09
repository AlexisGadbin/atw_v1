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
			<button type="button"> Générer les équipes aléatoires </button>
		</div>
		<hr>
		
		<div class="etudiantSansEquipe">
			<h3> Etudiants sans équipe : </h3>
			<c:if test="${ ! listeEtudiants.isEmpty() }">
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
		
		<c:forEach begin='0' end='1' step='1' var='j'>
			<div clas="equipe_i">
				<h4> Alexis Gadbin</h4>
				<select name="etudiant" id="etudiant">
				  <option value="volvo">Volvo</option>
				  <option value="saab">Saab</option>
				  <option value="opel">Opel</option>
				  <option value="audi">Audi</option>
				</select>
			</div>
		</c:forEach>
		<hr>
		
		<button type="button"> Exporter les équipes </button>
		
		
	</body>
</html>