<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>result</title>
	<c:import url="/WEB-INF/template/link.jsp" />
	<style type="text/css" media="screen">
     		.result_txt{ color : #EF5350;}
      </style>
</head>
<body>
<div class="container">
<<<<<<< HEAD
	<h1> 당신은 구두쇠 성향이 <span class="result_txt"> ${type.detail} </span> </h1>

	<div class="box_detail">
	<span> 검사한 사람들 중 ${type.name} 안에 있습니다. </span>
	</div><!--//.box_detail -->

	<div class="box_btn">
	<button class="btn btn-outline-dark" data-clipboard-text="http://localhost/scrooge/result/${type.id}">링크 복사하기</button>
=======
	<h1> 당신은 구두쇠 성향이 <span class="result_txt"> 거의 없어요 </span> </h1>

	<div class="box_detail">
	<span> 돈을 쓰고 돌아다는 건 정말 신나고 재밌는 일이죠! </span>
	</div><!--//.box_detail -->

	<div class="box_btn">
	<button class="btn btn-outline-dark" data-clipboard-text="카피하고 싶은 텍스트">링크 복사하기</button>
>>>>>>> cbdaeb7f97b44d65fc101ce6b27681c8fa40e782
	<a class="btn btn-outline-dark" href="/scrooge"> 다시하기 </a>
	</div><!--//.box_btn -->
</div>

<script type="text/javascript">
	let clipboard = new ClipboardJS('.btn');

	clipboard.on('success',function(e){
		alert('복사완료!');
	});
	clipboard.on('error', function(e) {
    	alert("복사실패!")
	});
</script>
</body>
</html>