<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>main</title>
	<c:import url="/WEB-INF/template/link.jsp" />
</head>
<body>
<div class="container">
	<div class="row justify-content-center">
		<figure class="text-center">
			<h1>나는 구두쇠?</h1>
  			<blockquote class="blockquote">
    			<p>돈을 소비하는 것에 있어서 두려워하고 소비하지 않는 것에 대해 집착하는 사람</p>
  			</blockquote>
		</figure>
	</div>
	 <div class="row justify-content-center">
	 	<!-- <button type="button" class="btn btn-primary btn-lg">확인하기</button> -->
			<div class="col-2"><a href="/scrooge/survey" class="btn btn-primary btn-lg">확인하기</a></div>
	</div>

</div>



<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>