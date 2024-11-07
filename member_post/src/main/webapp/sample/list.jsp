<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <c:forEach begin="2" end="9" var="i" step="2">
		<c:forEach begin="1" end="9" var="j" step="3">
			<p>${i} * ${j} = ${i*j}</p>
		</c:forEach>
	</c:forEach> --%>
	<c:forEach items="${posts}" var="post" varStatus="stat" begin="3" end="10" step="2">
		<h3>${stat.index} // ${stat.count} // ${post.title}</h3>
		<p> ${posts[stat.index] == post} </p>
	</c:forEach>
</body>
</html>