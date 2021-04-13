<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TO-DO LIST</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<section id="container_main">
		<header>
			<button class="btn_register">새로운 TODO등록</button>
		</header>
		<article id="title">나의 해야할 일들</article>
		<section id="container_items">
			<section id="todos" class="card_col">
				<span class="card_col_header">TODO</span>
				<c:forEach items="${todos}" var="item">
					<article id="todo_${item.id}" class="card_item">
						<p class="card_title">${item.title}</p>
						<span class="card_detail">등록날짜: ${item.regDate}
							${item.name} 우선순위 ${item.sequence}</span>
						<button class="btn_update">→</button>
					</article>
				</c:forEach>
			</section>

			<section id="doings" class="card_col">
				<span class="card_col_header">DOING</span>
				<c:forEach items="${doings}" var="item">
					<article id="doing_${item.id}" class="card_item">
						<p class="card_title">${item.title}</p>
						<span class="card_detail">등록날짜: ${item.regDate}
							${item.name} 우선순위 ${item.sequence}</span>
						<button class="btn_update">→</button>
					</article>
				</c:forEach>
			</section>

			<section id="dones" class="card_col">
				<span class="card_col_header">DONE</span>
				<c:forEach items="${dones}" var="item">
					<article id="done_${item.id}" class="card_item">
						<p class="card_title">${item.title}</p>
						<span class="card_detail">등록날짜: ${item.regDate}
							${item.name} 우선순위 ${item.sequence}</span>
						<button class="btn_update">→</button>
					</article>
				</c:forEach>
			</section>
		</section>
	</section>
</body>
<script src="js/main.js">
	
</script>
</html>