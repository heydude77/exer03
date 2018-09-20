<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%
	String user = (String)session.getAttribute("userId");
	
	List<Map> issueList = (List<Map>)request.getAttribute("issueList");
	List<Map> opinion = (List<Map>)request.getAttribute("opinion");
	
	//String title = content.substring(0, content.indexOf("\n"));
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
			
			<div style="background-color: #EAEAEA; padding: 7px; margin-bottom: 5px;"
			onmouseenter="highlight(this, true);" onmouseleave="highlight(this, false)">
				<p style="text-align: right; color: gray; font-size: small;" >
					<%=issueList.get(i).get("CATE")%> / 
					<% 
						Number issueNum = (Number)issueList.get(i).get("NO");
						int totalOpinion =0;
						for (int j=0; j<opinion.size(); j++){
							if(opinion.get(j).get("INO").equals(issueNum)){
									totalOpinion +=1;		
							}
						}					
						
					%>
					<%=totalOpinion%> 의견 /
					<%=issueList.get(i).get("REGDATE")%>
				</p>			
				<p>
					<%
						String content = (String)issueList.get(i).get("CONTENT");
						String title = "";
						if(content.indexOf("\n") ==-1) {
							title = content;
						} else {
							title = content.substring(0, content.indexOf("\n"));	
						}
						 
					%>
					<b>ISSUE.</b>
					 <a href="<%=application.getContextPath()%>/detail.do?no=<%=issueList.get(i).get("NO")%>">
					 <%=title%></a>
				</p>
				
			</div>
			
				
			<%} %>
		</div>
	</div>
	<script>
			var highlight = function(t, e){
				if(e)
					t.style.background ="Silver";
				else
					t.style.background ="Azure";
			}
		
		
	</script>
</body>
</html>