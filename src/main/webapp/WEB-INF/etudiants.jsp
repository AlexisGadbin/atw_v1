<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Etudiants</title>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<h1> Page Etudiants </h1>
		
		<form method="POST" action="etudiants">
			<input type="text" id="nom" name="nom" placeholder="Nom"/>
			<input type="text" id="prenom" name="prenom" placeholder="Prenom" />
			<select id="genre" name="genre">
				<option value="Homme"> Homme </option>
				<option value="Femme"> Femme </option>
				<option value="Autre"> Autre </option>
			</select>
			<br />
			
			<select id="sitePrecedent" name="sitePrecedent">
				<option value="Angers"> Angers </option>
				<option value="Paris"> Paris </option>
				<option value="Dijon"> Dijon </option>
			</select>
			<br />
			
			<select id="formationPrecedente" name="formationPrecedente">
				<option value="Ingénieur"> Ingénieur </option>
				<option value="Bachelor"> Bachelor </option>
			</select>
			<br />
			
			<input type="submit" name="ajouterEtudiant" value="Ajouter etudiant"/>
		</form>
		
		<hr />
		
		<form method="POST" action="etudiants" enctype="multipart/form-data">
	        <input type="file" name="fichier" id="fichier" />
			<input type="submit" name="ajouterCsv" value="Ajouter liste etudiant">
		</form>
		
		<hr /> 
		
		<c:if test="${ listeEtudiants.size() > 0 }">
			<c:forEach items="${ listeEtudiants }" var="i">
				<p><c:out value="${i.getNom()}" /> </p>
				<p><c:out value="${i.getPrenom()}" /> </p>
				<p><c:out value="${i.getGenre()}" /> </p>
				<p><c:out value="${i.getSitePrecedent()}" /> </p>
				<p><c:out value="${i.getFormationPrecedente()}" /> </p>
				<hr />
			</c:forEach>
		</c:if>
	</body>
</html>