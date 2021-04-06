<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TO-DO LIST</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<header>
		<button>새로운 TODO등록</button>
	</header>
	
	<section>
		<section id="todos" class="card-col">
			<article class="card-col-header">TODO</article>
			<c:forEach items="${todos}" var="item">
				<article class="card-item">
					${item.title}<br>
					등록날짜 : ${item.regDate} ${item.name} 우선순위 ${item.sequence}
				</article>
			</c:forEach>
		</section>

		<section id="doings" class="card-col">
			<article class="card-col-header">DOING</article>
			<c:forEach items="${doings}" var="item">
				<article class="card-item">
					${item.title}<br>
					등록날짜 : ${item.regDate} ${item.name} 우선순위 ${item.sequence}
				</article>
			</c:forEach>
		</section>

		<section id="dones" class="card-col">
			<article class="card-col-header">DONE</article>
			<c:forEach items="${dones}" var="item">
				<article class="card-item">
					${item.title}<br>
					등록날짜 : ${item.regDate} ${item.name} 우선순위 ${item.sequence}
				</article>
			</c:forEach>
		</section>
	</section>
</body>
<script src="js/main.js">
</script>
</html>