<%@ tag pageEncoding="ISO-8859-1" %>

<%@ attribute name="titre" %>


<!DOCTYPE html>
<html>
<head>
	<title>${ titre }</title>
</head>

<body>
	<h1>${ titre }</h1>
	
	<!-- doBody insère le contenu défini dans l'utilisation du tag -->
	<jsp:doBody />
</body>

</html>