<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
<script src="<c:url value='/js/memberManageForm.js'/>"></script>
<script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script>
<c:import url="/WEB-INF/views/layout/head.jsp" />
</head>
<body>
	<div id="memberManageForm">
		<div id="memberManageContainer" class="noto-sans-kr">
			<div id="memberManageMenuBox">
				<div class="memberManageMenuEach">
					<a href="?ctg=회원정보">회원정보</a>
				</div>
				<div class="memberManageMenuEach">
					<a href="?ctg=수상내역">수상내역</a>
				</div>
			</div>
			
				<div id="memberManageContentsBox">
					<div id="memberManageLeftBox">
						<div id="memberManageNameSearchBox">
							<img id="memberManageNameSearchImage" src="<c:url value='/image/mapSearch.png' />">
							<input type="text" id="memberManageNameSearch" name="memberManageNameSearch" placeholder="이름 검색" value="">
						</div>
						<div id="memberManageNameBox1">
							<c:forEach var="name" items="${nameList }">
								<div class="memberManageNameEach">
									<span data-value="${name.childNo }" data-day="${name.leftChk }">${name.childName }</span>
								</div>
							</c:forEach>
						</div>
						<div id="memberManageNameBox2">
							<div id="memberManageGymSearchBox"></div>
						</div>
					</div>
					<c:if test="${ctg eq '회원정보'}">
					<div id="memberManageRightBox">
						<div id="memberManageMemberInfo">
							<div id="memberManageChooseBox">
								<div id="memberManageChooseTitle">회원 정보</div>
								<img id="memberManageChangeImage" src="<c:url value='/image/memberChange.png'/>">
							</div>
							<div id="memberManageMemberContentsBox1">
								<div class="memberManageMemberContents">
									<img id="memberManageName" src="<c:url value='/image/mapManager.png'/>" class="memberImage">
									<span id="memberManageChildNameText"></span>
								</div>
								<div class="memberManageMemberContents">
									<img id="memberManageAge" src="<c:url value='/image/myPageAge.png'/>" class="memberImage">
									<span id="memberManageAgeText"></span>
								</div>
								<div class="memberManageMemberContents">
									<img id="memberManageGender" src="<c:url value='/image/myPageGender.png'/>" class="memberImage">
									<span id="memberManageGenderText"></span>
								</div>
								<div class="memberManageMemberContents">
									<img id="memberManageNumber" src="<c:url value='/image/mapNumber.png'/>" class="memberImage">
									<span id="memberManageChildNumberText"></span>
								</div>
								<div class="memberManageMemberContents">
									<img id="memberManageBelt" src="<c:url value='/image/myPageBelt.png'/>" class="memberImage">
									<input id="memberManageBeltText" name="memberManageBeltText" type="text" value="흰띠" readonly required> <input type="button" id="memberManageModifyBtn" name="memberManageModifyBtn" value="수정하기" class="modifyBtn"> <input type="button" id="memberManageModifySuccessBtn" name="memberManageModifySuccessBtn" value="수정완료" class="modifyBtn">
								</div>
								<div class="memberManageMemberContents">
									<img id="memberManageDate" src="<c:url value='/image/myPageDate.png'/>" class="memberImage">
									<span id="memberManageDateText"></span>
								</div>
							</div>
							<div id="memberManageMemberContentsBox2">
								<div class="memberManageMemberContents">
									<img id="memberManageName" src="<c:url value='/image/mapManager.png'/>" class="memberImage">
									<span id="memberManageMemNameText"></span>
								</div>
								<div class="memberManageMemberContents">
									<img id="memberManageNumber" src="<c:url value='/image/mapNumber.png'/>" class="memberImage">
									<span id="memberManageNumberText"></span>
								</div>
								<div class="memberManageMemberContents">
									<img id="memberManageAddress" src="<c:url value='/image/mapGymAddress.png'/>" class="memberImage">
									<span id="memberManageAddressText"></span>
								</div>
							</div>
						</div>
					</div>
					</c:if>
					<c:if test="${ctg eq '수상내역' }">
					<div id="memberManageRightBox">
						<div id="memberManageCompetitionInfo">
							<div id="memberManageChooseBox">
								<div id="memberManageChooseTitle">수상 내역</div>
								<div id="memberManageArrowBox">									
									<img id="memberManageLeftArrow" src="<c:url value='/image/leftArrow.png'/>" class="arrowBtn" data-num="0">
									<img id="memberManageRightArrow" src="<c:url value='/image/RightArrow.png'/>" class="arrowBtn" data-num="0">
								</div>
							</div>
							<div id = "memberManageCompetitionNullBox">
								수상 내역을 추가해주세요.
							</div>
							<div id="memberManageCompetitionContentsBox">
								<div class="memberManageMemberContents">
									<img id="memberManageCompetition" src="<c:url value='/image/competition.jfif'/>" class="memberImage">
									<input id="memberManageCompetitionText" name="memberManageCompetitionDateText" type="text" value="" readonly required>
								</div>
								<div class="memberManageMemberContents">
									<img id="memberManageCompetitionDate" src="<c:url value='/image/myPageDate.png'/>" class="memberImage">
									<input id="memberManageCompetitionDateText" name="memberManageCompetitionDateText" type="text" value="" readonly required>
								</div>
								<div class="memberManageMemberContents">
									<img id="memberManageTrophy" src="<c:url value='/image/Trophy.png'/>" class="memberImage">
									<input id="memberManageCompetitionTrophyText" name="memberManageCompetitionTrophyText" type="text" value="" readonly required>
								</div>
							</div>
							<div id="memberManageCompetitionButtonBox">
									<input type="button" id="deleteCompetitionBtn" name="deleteCompetitionBtn" value="삭제하기" class="manageBtn">
									<input type="button" id="addCompetitionCancleBtn" name="addCompetitionCancleBtn" value="취소" class="manageBtn"> 
									<input type="button" id="addCompetitionBtn" name="addCompetitionBtn" value="추가하기" class="manageBtn">
									<input type="button" id="addCompetitionSuccessBtn" name="addCompetitionSuccessBtn" value="추가완료" class="manageBtn">
							</div>
						</div>
					</div>
					</c:if>
				</div>
		</div>
	</div>
</body>
</html>