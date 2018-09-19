<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MVC</title>
<link rel="stylesheet" href="<%=application.getContextPath()%>/css/style.css" />
</head>
<body>
	<%--
		mvc pattern 의 application 에서는 
		action 이나 link , ajax 목적지를 controller 매핑경로로 설정
	 --%>
	<div align="center">
		<h1>〔회원가입〕</h1>
		<div style="margin-left: 10%; margin-right: 10%; text-align: left;">
		
		<h3 style="color: blue;">Step1.당신의 계정을 생성하세요.</h3>
		<h3 style="color: silver;">	Step2.당신의 관심사항을 설정하세요.</h3>
		<form action="<%=application.getContextPath() %>/join.do" method="post" autocomplete="off" >
			<p>
				<b>아이디(*)</b><br/>
				<input type="text" name="id" placeholder="account id" style="width: 99%;" id="id"/><span></span>
			</p>
			<p>
				<b>비밀번호(*)</b><br/>	
				<input type="password" name="pw" placeholder="account pass" style="width: 99%;" id="pw"/>
			</p>
			<p>
				<b>비밀번호 재확인(*)</b><br/>	
				<input type="password" name="pwconfirm" placeholder="confirm pass" style="width: 99%;" id="pwConfirm"/><span></span>
			</p>
			<p>
				<b>이름(*)</b><br/>	
				<input type="text" name="name" placeholder="user name" style="width: 99%;"/>
			</p>
			<p>
				<b>성별(*)</b><br/>	
				<select name="gender" style="width: 100%;">
					<option value="M">남성</option>
					<option value="F">여성</option>
				</select>
			</p>
			<p>
				<button type="submit" style="width: 100%;">가입 신청</button>
			</p>
		</form>
		</div>
	</div>
	<script>		
		document.getElementById("id").onchange = function() {		
			var req = new XMLHttpRequest();		
			req.open("get","signup_check.do?id="+document.getElementById("id").value, true);
			req.onreadystatechange = function () {
				if(this.readyState==4){					
					if(this.responseText.trim()=="true") {
						document.getElementsByTagName("span")[0].innerHTML = "이미사용중인 아이디 입니다."
						document.getElementsByTagName("span")[0].style.color = "red";
						//ar[0]=false;
						//valid();
					}else {
						document.getElementsByTagName("span")[0].innerHTML = "사용가능한 아이디 입니다."
						document.getElementsByTagName("span")[0].style.color = "green";
						//ar[0]=true;
						//valid();
					}
				}
			}
			req.send();
		};
		
		document.getElementById("pwConfirm").onchange = function() {
			var pw = document.getElementById("pw").value;
			var pwConfirm = document.getElementById("pwConfirm").value;
			if(pw != pwConfirm){
				document.getElementsByTagName("span")[1].innerHTML = "비밀번호가 일치하지 않습니다."
				document.getElementsByTagName("span")[1].style.color = "red";
			}
		}; 
	</script>
</body>
</html>