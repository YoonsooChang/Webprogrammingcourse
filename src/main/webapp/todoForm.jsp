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
	<section id="container_main">
		<section id="container_form">
			<h2>할일 등록</h2>
			<form action="/register" method="post"
				id="todo_form">
				<section class="block_input">
					<p class="subject">어떤일인가요?</p>
					<input type="text" id="title_input" name="title"
						placeholder="swift 공부하기(24자까지)" maxlength='24' required>
				</section>
				
				<section class="block_input">
					<p class="subject">누가 할일인가요?</p>
					<input type="text" id="name_input" name="name" placeholder="홍길동"
						maxlength="10" required>
				</section>
				
				<section class="block_input">
					<p class="subject">우선순위를 선택하세요</p>
					<input type="radio" id="pr_1" name="sequence" value="1" required>
					<label for="pr_1" class="input_label">1순위</label>
					<input type="radio" id="pr_2" name="sequence" value="2">
					<label for="pr_2" class="input_label">2순위</label>
					<input type="radio" id="pr_3" name="sequence" value="3">
					<label for="pr_3" class="input_label">3순위</label>
				</section>
				
				<section id="page_man">
					<button id="back_btn" type="button">&lt; 이전</button>
					<section id="form_man">
						<button class="btn_form" id="submit_btn" type="submit"
							form="todo_form">제출</button>
						<button class="btn_form" id="clear_btn" type="button">내용지우기</button>
					</section>
				</section>
			</form>
		</section>
	</section>
</body>
<script src="js/todoform.js">
	
</script>
</html>