<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTER</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/todoform.css">
</head>
<body>
	<section id="container-main">
		<section id="container-form">
			<h2>할일 등록</h2>
			<form action="http://localhost:8080/todoapp/register" method="post"
				id="todo-form">
				<section class="block-input">
					<p class="subject">어떤일인가요?</p>
					<input type="text" id="title-input" name="title"
						placeholder="swift 공부하기(24자까지)" maxlength='24' required>
				</section>
				<section class="block-input">
					<p class="subject">누가 할일인가요?</p>
					<input type="text" id="name-input" name="name" placeholder="홍길동"
						maxlength="10" required>
				</section>
				<section class="block-input">
					<p class="subject">우선순위를 선택하세요</p>
					<input type="radio" id="pr-1" name="sequence" value="1" required>
					<label for="pr-1" class="input-label">1순위</label>
					<input type="radio" id="pr-2" name="sequence" value="2">
					<label for="pr-2" class="input-label">2순위</label>
					<input type="radio" id="pr-3" name="sequence" value="3">
					<label for="pr-3" class="input-label">3순위</label>
				</section>
				<section id="page-man">
					<button id="back-btn" type="button">&lt; 이전</button>
					<section id="form-man">
						<button class="btn-form" id="submit-btn" type="submit"
							form="todo-form">제출</button>
						<button class="btn-form" id="clear-btn" type="button">내용지우기</button>
					</section>
				</section>
			</form>
		</section>
	</section>
</body>
<script src="js/todoform.js">
	
</script>
</html>