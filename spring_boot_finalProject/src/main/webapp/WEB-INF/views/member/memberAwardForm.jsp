<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수상내역 관리</title>
<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
<script src="<c:url value='/js/memberAwardForm.js'/>"></script>
<c:import url="/WEB-INF/views/layout/head.jsp" />
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp" />
	<div class="bodyContents">
		<div id="memberAwardForm">
			<div id="memberAwardContainer" class="noto-sans-kr">
				<div id="memberAwardTitleBox">
					<div id="memberAwardTitle">마이페이지</div>
				</div>
				<div id="memberAwardContentsBox">
					<div id="memberAwardLeftBox">
						<div id="memberAwardInfoBox">
							<input type="file" id="awardFileInput" name="awardImage">
							<div id="memberAwardImageBox">
								<img id="awardImg" src="<c:url value='/images/${vo.memImgNo}' />">
								<img id="awardImgModify" src="<c:url value='/image/myPageImageModify.png' />">
							</div>
							<div id="memberAwardInfoNick">${vo.memNick}</div>
							<div id="memberAwardInfoEmail">${vo.memEmail}</div>
						</div>
						<div id="memberAwardMenuBox">
							<div class="memberAwardMenuEach" id="awardProfile">내 프로필</div>
							<div class="memberAwardMenuEach" id="awardManage">
								<c:if test="${vo.gymNo == null }">
                            		등록정보
                            	</c:if>
								<c:if test="${vo.gymNo != null }">
									회원관리
								</c:if>
							</div>
							<c:if test="${vo.gymNo == null }">
								<div class="memberAwardMenuEach" id="awardAwards" style="font-weight: 600; font-size: 21px; text-decoration: underline; text-underline-offset: 15px; text-decoration-thickness: 2px;">수상내역</div>							
							</c:if>
							<div class="memberAwardMenuEach" id="awardDeleteAccount">회원탈퇴</div>
						</div>
					</div>
					<div id="memberAwardRightBox">
						<div id="memberAwardRegisterInfo">
							<div id="memberAwardRegisterContainer">
								<div id="memberAwardRegisterUser">
									<div id="memberAwardRegisterUserData"></div>
									<div id="memberAwardRegisterUserPlus">
										<img id="awardPlus" src="<c:url value='/image/myPagePlusButton.jpg' />">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/layout/bottom.jsp" />
</body>
</html>
