<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:structure titre="Bonjour ${ utilisateurSession } !">
	<p>TADAM</p>
	
	<c:forEach var="i" begin="0" end="7" step="1">
		<p>${ i }</p>
	</c:forEach>
</t:structure>