<%@ tag pageEncoding="ISO-8859-1" %>

<%@ attribute name="titre" %>


<!DOCTYPE html>
<html>
<head>
	<title>${ titre }</title>
	<base href="/" />
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous" />
</head>

<body>
	<div class="container">
		<h1>${ titre }</h1>
		
		<!-- doBody ins�re le contenu d�fini dans l'utilisation du tag -->
		<jsp:doBody />
	</div>
</body>

</html>