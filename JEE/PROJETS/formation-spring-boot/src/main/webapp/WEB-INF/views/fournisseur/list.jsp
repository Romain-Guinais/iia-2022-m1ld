<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:structure titre="Liste des fournisseurs">
	<a class="btn btn-primary" href="fournisseur/ajouter">Ajouter</a>
	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="fournisseur" items="${ fournisseurs }">
				<tr>
					<td>${ fournisseur.id }</td>
					<td>${ fournisseur.nom }</td>
					<td>
						<a class="btn btn-warning" href="fournisseur/modifier?id=${ fournisseur.id }">Modifier</a>
						<a class="btn btn-danger" href="fournisseur/supprimer?id=${ fournisseur.id }">Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</t:structure>