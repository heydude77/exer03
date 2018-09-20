<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>

<%
	String user = (String)session.getAttribute("userId");	
	
	Map issue =(Map)request.getAttribute("issue");
	Number no = (Number)issue.get("NO");
	String cate = (String)issue.get("CATE");
	String content = (String)issue.get("CONTENT");
	String title = "";
	if(content.indexOf("\n") ==-1) {
		title = content;
	} else {
		title = content.substring(0, content.indexOf("\n"));	
	}	
	String agree = (String)issue.get("AGREE");
	String disAgree = (String)issue.get("DISAGREE");
	String writer = (String)issue.get("WRITER");
	Date date = (Date)issue.get("REGDATE");
	
	List<Map> opinion =(List<Map>)request.getAttribute("opinion");
	int totalOpinion = opinion.size();
	int agreeCount = 0;
	int disCount = 0;
	if (totalOpinion !=0)	{
		for (int i=0; i<opinion.size(); i++){
			Number choice = (Number)opinion.get(i).get("CHOICE");
			if (choice.intValue()==1){
				agreeCount ++;
			}else {
				disCount ++;
			}
		}	
	}
	/*
		no number(6,0) not null,  -- 의견테이블 기본키
        choice number(1, 0) not null, -- 찬성1 ,반대 0 으로 저장
        ment varchar2(1000) not null, -- 이유
        talker varchar2(100), -- 올린사람
        ino number(6,0) not null,  -- 어떤이슈에 관한 의견인지 부모키
	*/
	

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
			<b><%=user %>님</b>, 로그온 | <a
				href="<%=application.getContextPath()%>/logout.do">로그오프</a>
			<hr />
		</div>	
		<div style="text-align: right; margin-right: 10%; margin-left: 10%;">
			<a href="<%=application.getContextPath()%>/trend.do">
			<button class="w2-button w3-round w3-black">글목록으로</button></a>	
		</div>
		<h2>【토론배틀】</h2>
		<small style="font-style: italic;">찬성이냐, 반대냐 그것이 문제로다!</small>
		<div style="margin-right: 10%; margin-left: 10%; text-align: left;">
			<h3>#. <%=title%></h3><small><%=date %>등록</small>
			<p>
				<%=content %><br/>
				
			</p>
		</div>
		<div style="margin-right: 10%; margin-left: 10%; text-align: left; margin-top: 	55px; font-size: small;">
			<p style="color: blue">
				<b>YES</b> <%=agree %> <span id="agreeCount">(<%=agreeCount %></span> 명) 
			</p>
			<p style="color: red">
				<b>NO</b> <%=disAgree %> <span id="disCount">(<%=disCount %></span> 명)
			</p>
		</div>
		
		<div style="margin-right: 10%; margin-left: 10%; text-align: left; margin-top: 	55px;">
			<p>
			<b>〔의견남기기〕</b><br/>
			</p>
			<p>
			<select id="select">
				<option value="1">YES</option>
				<option value="0">NO</option>
			</select>
			<input type="text" style="width: 80%" id="comment"/>
			</p>
		</div>
		
		<div style="margin-right: 10%; margin-left: 10%; text-align: left; margin-top: 	35px;">
			<p>
				<b>〔전체의견 / <span id="totalOpinion"><%=totalOpinion %></span>〕</b><br/>
				<span id="ajaxresult"></span>
			</p>
			<ul style="list-style: none; font-size: smaller;" id="opinion">
				<% 
					for (int i=0; i<opinion.size(); i++){
						Number oNo = (Number)opinion.get(i).get("NO");
						Number choice = (Number)opinion.get(i).get("CHOICE");
						String ment = (String)opinion.get(i).get("MENT");
						String talker = (String)opinion.get(i).get("TALKER");
						String select = choice.intValue()==1 ? "【YES】" : "【NO】";
				%>
							<li>
							<%if(choice.intValue()==1){ %>
								<b style="color:blue">
							<%}else{  %>
								<b style="color:red">
							<%} %>
							<%=select %></b> <%=talker%> : <%=ment %></li>
				<% } %>
			</ul>
			<script>		
				var latestAjax = function() {
					var xhr = new XMLHttpRequest();
					xhr.open("get","refresh.do?ino="+<%=no%>, true);
					xhr.onreadystatechange =function(){
						if(this.readyState==4) {
							var obj = JSON.parse(this.responseText);
							var html ="";
							var ac=0;
							var dc=1;
							for(var i=0; i<obj.length; i++){
								var choice = obj[i].CHOICE;
								if (choice==1){
									ac++;
								}else{
									dc++;
								}
								var ment = obj[i].MENT;
								var talker = obj[i].TALKER;
								var select = (choice==1) ? "<b style=\"color:blue\">【YES】" : "<b style=\"color:red\">【NO】";
								html+="<li>"+select+"</b>"+talker+" : "+ment+"</li>";										
							}
							document.getElementById("opinion").innerHTML = html;
							document.getElementById("totalOpinion").innerHTML = obj.length;
							document.getElementById("agreeCount").innerHTML = "("+ac;
							document.getElementById("disCount").innerHTML = "("+dc;
						}
					}
					xhr.send();
				};
				
				var time = 10;
				window.setInterval(function(){
					time--;
					if(time==0) {
						latestAjax();
						time=10;
					}
				}, 1000);
			</script>
		</div>
	</div>
	
	<script>
	document.getElementById("comment").onchange = function() {		
		var req = new XMLHttpRequest();	
		var select = document.getElementById("select").value;
		var comment = document.getElementById("comment").value;
		var no = <%=no%>;
		// post 일때
		// req.open("post","comment.do", true);
		req.open("get","comment.do?select="+select+"&comment="+comment+"&ino="+no, true);
		req.onreadystatechange = function () {
			if(this.readyState==4){					
				if(this.responseText.trim()=="true") {
					//location.reload();
					window.alert("댓글이 성공적으로 등록되었습니다.");
					document.getElementById("comment").value= "";
					document.getElementById("select").value= 1;
				}else {				
					window.alert("댓글 등록중 문제가 발생하였습니다.");	
				}
			}
		}
		//var param = {"ino":no, "ment":comment", "choice":select"};
		//var param = JSON.stringify(param);
		//req.send(param);
		req.send();
	};
	</script>
</body>
</html>