<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:structure titre="${ fournisseur == null ? 'Ajouter' : 'Editer' }">
	<form method="POST">
		<div class="row mb-3">
			<div class="col">
				<input class="form-control" type="text" name="nom" value="${ fournisseur.nom }" placeholder="Nom" />
			</div>
		</div>
		
		<div class="row">
			<div class="col">
				<input class="btn btn-success" type="submit" value="OK" />
			</div>		
		</div>
	</form>
</t:structure>