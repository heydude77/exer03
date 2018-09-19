<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user = (String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC</title>
<link rel="stylesheet" href="<%=application.getContextPath()%>/css/style.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
	<div align="center">
		<h1># MVC</h1>
		<div align="right" style="margin-right: 10%; margin-left: 10%; font-size: small;">
			<b><%=user%>님</b>,  로그온 |
			<a href="<%=application.getContextPath() %>/logout.do">로그오프</a>
			<hr/>
		</div>
		<div style="margin-right: 10%; margin-left: 10%;">
			<form action="<%=application.getContextPath() %>/search.do">
				<input type="text" style="width:98%;" placeholder="search keyword"/>
			</form>
		</div>
		<div style="text-align: center;">
			<a href="<%=application.getContextPath()%>/trend.do">
			<button class="w2-button w3-round w3-black">토론배틀</button></a>	
		</div>
		<div style="margin-right: 10%; margin-left: 10%;" align="left">
			<h3>최근 등록된 새로운 이슈 !</h3>
			<ul>
				<li>24 시간 이내 등록된 이슈가 없습니다</li>
			</ul>
		</div>
	</div>
	
</body>
</html>