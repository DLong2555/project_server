<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리</title>
<c:import url="/WEB-INF/views/layout/head.jsp"></c:import>
<script src="<c:url value='/js/eventBoardForm.js'/>"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp"></c:import>
	<div class="bodyContents">
		<div id="eventBoardForm">
			<div id="eventBoardFormContainer" class="noto-sans-kr">
				<div id="eventBoardFormTitleBox">
					<div id="eventBoardFormTitle">특수활동</div>
					<div id="eventBoardFormSubTitle">도전과 즐거움이 가득한 특수활동을 경험하세요</div>
				</div>
				
				<div id="eventBoardFormMenuBox">
					<div id="eventBoardFormMenuBox2">
						<div class="eventBoardFormMenuEach"></div>
					</div>

					<form id="eventSearchForm" method="get" action="<c:url value='/event/eventSearch' />">
						<img id="eventSearchImage" src="<c:url value='/image/mapSearch.png' />">
						<input type="text" id="eventSearch" name="keyword">
						<input type="submit" id="eventSearchBtn" value="검색">
					</form>
				</div>
				<div id="eventLink" data-val=""></div>
				<div id="eventBoardFormList">
					<c:forEach var="eventList" items="${eventList }">
						<div class="eventBoardFormListRow">
							<div class="eventBoardFormImageCell" data-val="${eventList.eventNo }">
								<a class="eventLinkA" href="<c:url value='/gallery/eventBoardContentPage?eventNo=${eventList.eventNo }' /> ">
									<img src="<c:url value='/images/${eventList.eventImg }' />" alt="${eventList.eventTitle }" class="eventBoardFormImage">
								</a>
							</div>
							<a class="eventLinkA" href="<c:url value='/gallery/eventBoardContentPage?eventNo=${eventList.eventNo }' /> ">
							<div class="eventBoardFormInfoCell">
								<div class="eventBoardFormDescription" data-value="${eventList.deadLineChk }">${eventList.eventTitle }</div>
								<div class="eventBoardFormDate">마감일 : <span>${eventList.deadLine }</span> 23:59 까지</div>
							</div>
							</a>
						</div>
					</c:forEach>
				</div>
				<c:if test="${not empty gymNo }">
					<div id="eventBoardFormButtonBox">
						<div class="eventBoardFormWriteButton">
							<a href="<c:url value='/gallery/insertEventFrom' />">글쓰기</a>
						</div>
					</div>
				</c:if>
				<div class="eventBoardFormPagination">
					<div class="eventBoardFormPaginationMove">
						<div class="eventBoardFormPaginationDoubleLeft"></div>
						<div class="eventBoardFormPaginationDoubleLeft"></div>
					</div>
					<div class="eventBoardFormPaginationMove">
						<div class="eventBoardFormPaginationDoubleLeft"></div>
					</div>
					<div id="eventBoardFormNumberBox">
						<c:forEach var="i" begin="1" end="${totalPage}">
							<a href="?page=${i}&pageSize=${pageSize}" class="${i == currentPage ? 'active' : ''}">${i}</a>
						</c:forEach>
					</div>
					<div class="eventBoardFormPaginationMove">
						<div class="eventBoardFormPaginationDoubleRight"></div>
					</div>
					<div class="eventBoardFormPaginationMove">
						<div class="eventBoardFormPaginationDoubleRight"></div>
						<div class="eventBoardFormPaginationDoubleRight"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/layout/bottom.jsp"></c:import>
</body>
</html>
