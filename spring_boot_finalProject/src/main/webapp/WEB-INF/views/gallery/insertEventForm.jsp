<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Event</title>
<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
<script src="<c:url value='/js/insertEventForm.js'/>"></script>
<c:import url="/WEB-INF/views/layout/head.jsp" />
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp" />
	<div class="bodyContents">
		<div id="insertEventForm">
			<div id="insertEventFormContainer" class="noto-sans-kr">
				<div id="insertEventFormTitleBox">
					<div id="insertEventFormTitle">특수활동</div>
					<div id="insertEventFormSubTitle">도전과 즐거움이 가득한 특수활동을 경험하세요</div>
				</div>

				<div id="insertEventFormBox">
					<div class="insertEventFormInputBox">
						<label for="EventDate">공고 제목</label> <input type="text" id="EventName" name="EventName" placeholder="제목을 입력하세요" required>
					</div>
				</div>

				<!-- 날짜 등록 -->
				<div id = "insertEventFormEventBox">
					<div class="insertEventFormInputBox">
						<label for="EventMoney">등록 금액</label> <input type="text" id="EventMoney" name="EventMoney" class="shortInput" placeholder="금액을 입력하세요 (숫자만 입력)" required>
					</div>
					<div class="insertEventFormInputBox">
						<label for="EventDate">등록 마감일</label> <input type="date" id="EventDate" name="EventDate" class="shortInput" required>
					</div>
				</div>
				<div class="textAreaBox">
					<label for="EventMain">메인 사진</label>
					<div id="EventMain" class="textArea" contenteditable="true"></div>
				</div>
				<div id="EventMainImageInputBox">
					<div id="EventDetailWarning">
						<div>* 이미지를 업로드 하신 후 클릭을 하셔야 본문에 첨부됩니다.</div>
						<div>* 하단 썸네일을 클릭하시면 본문에 추가됩니다.</div>
					</div>
					<div id="EventMainImageInputButton">이미지 업로드</div>
					<input type="file" id="fileInputMain" name="fileInputMain">
					<div id="EventMainImageBox"></div>
				</div>
				<div class="textAreaBox">
					<label for="EventDetail">사진 설명</label>
					<div id="EventDetail" class="textArea" contenteditable="true"></div>
				</div>
				<div id="EventDetailImageInputBox">
					<div id="EventDetailWarning">
						<div>* 이미지를 업로드 하신 후 클릭을 하셔야 본문에 첨부됩니다.</div>
						<div>* 하단 썸네일을 클릭하시면 본문에 추가됩니다.</div>
					</div>
					<div id="EventDetailImageInputButton">이미지 업로드</div>
					<input type="file" id="fileInputDetail" name="fileInputDetail">
					<div id="EventDetailImageBox"></div>
				</div>
				<!-- 등록 버튼 -->
				<div id="insertEventFormButtonBox">
					<input type="submit" id="insertEventBtn" class="btn" value="등록">
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/layout/bottom.jsp" />
</body>
</html>
