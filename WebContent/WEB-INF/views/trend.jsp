<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%
	String user = (String)session.getAttribute("userId");
	
	List<Map> issueList = (List<Map>)request.getAttribute("issueList");
		
	//Map issueTotal = (Map)request.getAttribute("opinionTotalByIssueNo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC</title>
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/css/style.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
	<div align="center">
		<h1># MVC</h1>
		<div align="right"
			style="margin-right: 10%; margin-left: 10%; font-size: small;">
			<b><%=user%>님</b>, 로그온 | <a
				href="<%=application.getContextPath()%>/logout.do">로그오프</a>
			<hr />
		</div>
		<%if(request.getAttribute("success") !=null) {%>
				<script>
					window.alert("글이 성공적으로 등록되었습니다.");
				</script>
		<%} %>
		<h2>【토론배틀】</h2>
		
		<div style="margin-right: 10%; margin-left: 10%; text-align: right;">
			<a href="<%=application.getContextPath()%>/new.do">
			<button class="w2-button w3-round w3-black">글쓰기</button></a>	
		</div>
		
		<div style="margin-right: 10%; margin-left: 10%; text-align: left">
			<%for (int i=0; i<issueList.size(); i++) {%>
			<div style="background-color: #EAEAEA; padding: 7px; margin-bottom: 5px;">
				<p style="text-align: right; color: gray; font-size: small;" >
					<%=issueList.get(i).get("CATE")%> / 
					000 의견 /
					<%=issueList.get(i).get("REGDATE")%>
				</p>			
				<p>
					<a href=""><b>ISSUE.</b> <%=issueList.get(i).get("CONTENT")%> </a>
				</p>
				
			</div>
				
			<%} %>
		</div>
	</div>
</body>
</html>