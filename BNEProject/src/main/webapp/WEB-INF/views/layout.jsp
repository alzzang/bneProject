<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<tiles:insertAttribute name="menu" />
	</div>
	
	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>
	
	<footer class="footer">
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>