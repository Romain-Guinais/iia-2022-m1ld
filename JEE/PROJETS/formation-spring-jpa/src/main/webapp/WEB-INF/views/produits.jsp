<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:structure titre="Liste des produits">
	
	<table>
		<thead>
			<tr>
				<th>Nom</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="produit" items="${ produits }">
				<tr>
					<td>${ produit.nom }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</t:structure>