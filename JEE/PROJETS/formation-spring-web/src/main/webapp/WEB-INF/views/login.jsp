<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Se connecter</title>
</head>
<body>
	<c:if test="${ error == true }">
		<div class="error">
			Le nom d'utilisateur est obligatoire !
		</div>
	</c:if>
	
	
	<form method="POST">
		<div>
			<label>Nom d'utilisateur</label>
			<input type="text" name="username" placeholder="Pourquoi pas" />
		</div>
		
		<div>
			<input type="submit" value="Se connecter" />
		</div>
	</form>

</body>
</html>