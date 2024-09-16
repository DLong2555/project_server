<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TTS</title>
		<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
		<script src="<c:url value='/js/chatbot.js'/>"></script>
	</head>
	<body>
		<div id="wrap">
		
			<!-- Header -->
			<div id="chatHeader">
				<span>FitChat</span>
			</div>
			
			<!-- 채팅 내용 출력 영역  -->
			<div id="chatBox"></div>
			
			<div>
				<form id="chatForm" name="chatForm">
					<input type="text" id="message" name="message" size="30"  placeholder="질문을 입력하세요"/>
					<input type="submit" value="전송">
				</form>
			</div>
		</div>
	</body>
</html>