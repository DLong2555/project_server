<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gallery Details</title>
<c:import url="/WEB-INF/views/layout/head.jsp" />
<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
<script src="<c:url value='/js/eventBoardContentPage.js'/>"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp" />

	<div class="bodyContents">
		<div id="eventBoardContentPage">
			<div id="eventBoardContentPageContainer" class="noto-sans-kr">
				<div id="eventBoardContentPageTitleBox">
					<div id="eventBoardContentPageTitle">특수활동</div>
					<div id="eventBoardContentPageSubTitle">도전과 즐거움이 가득한 특수활동을 경험하세요</div>
				</div>

				<div id="eventBoardContentPageContentBox">
					<div id="eventBoardContentPageContentInnerBox">
						<div id="eventBoardContentPageContentInfoBox">
							<div id="eventBoardContentPageContentTitleBox">
								<div id="eventBoardContentPageContentCtg">공고</div>
								<div id="eventBoardContentPageContentTitle" data-value="${event.eventNo }">${event.eventTitle }</div>
							</div>

							<div id="eventBoardContentPageSubInfoBox">
								<div id="eventBoardContentPageUploader" class="eventBoardContentPageSubInfo">
									<img id="galleryManager" class="eventBoardContentPageImage" src="<c:url value='/image/mapManager.png' />">
									${event.memName }
								</div>
								<div id="eventBoardContentPageTime" class="eventBoardContentPageSubInfo">
									<img id="galleryTime" class="eventBoardContentPageImage" src="<c:url value='/image/boardTime.png' />">
									${event.createdAt }
								</div>
								<div id="eventBoardContentPageView" class="eventBoardContentPageSubInfo">
									<img id="galleryGym" class="eventBoardContentPageImage" src="<c:url value='/image/myPageGym.png' />">
									${event.gymName }
								</div>
							</div>
						</div>

						<div class="textAreaBox">
							<div id="eventBoardContentPageBoardText" class="textArea">
								<c:if test="${not empty event.eventImg }">
									<img src="/images/${event.eventImg }" alt="gallery image" id="galleryMainImage"><br>
								</c:if>
								<div id="eventBoardContentPageDescription">${event.eventContents }</div>
							</div>
						</div>
						
						<c:if test="${not empty sessionScope.sidGymNo }">
							<div id="eventBoardContentPageButtonBox">
								<div id="eventBoardContentPageModifyButton">
									<a href="/gallery/updateEventContentForm?eventNo=${event.eventNo }">수정하기</a> 
								</div>
								<div id="eventBoardContentPageDeleteButton">
									<a href="#">삭제하기</a> 
								</div>
							</div>
						</c:if>
						
						<div id="eventBoardContentPageCommentAlarm">
							<img id="commentIcon" class="eventBoardContentPageImage" src="<c:url value='/image/event.png' />"> 특수활동 정보
						</div>

						<div id="eventBoardContentPageEventInfoBox">
							<div id="eventBoardContentPageEventInfoText" class="textArea">
								<div id="eventBoardContentPageEventInfoTitle"> ${event.eventTitle}</div>
								<div>마감일 : <span>${event.deadLine }</span> 23:59까지</div>
								<div>금액 : <span id="eventPrice">${event.eventPrice }</span>원	</div>
							</div>
						</div>

						<div id="eventBoardContentPageCommentButtonBox">
							<div id="eventBoardContentPagePayButton">
								<c:if test="${empty childNo }">
									<a href="/gym/joinAndPayGym?ctg=특수&eventNo=${event.eventNo }">결제하기</a>
								</c:if>
								<c:if test="${not empty childNo }">
									<a href="/gym/joinAndPayGym?ctg=특수&eventNo=${event.eventNo }&childNo=${childNo}">결제하기</a>									
								</c:if>								
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
