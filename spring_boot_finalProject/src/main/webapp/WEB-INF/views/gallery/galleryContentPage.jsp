<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gallery Details</title>
<c:import url="/WEB-INF/views/layout/head.jsp" />
<script src="<c:url value='/js/galleryPage.js'/>"></script>
<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp" />

	<div class="bodyContents">
		<div id="galleryContentPage">
			<div id="galleryContentPageContainer" class="noto-sans-kr">
				<div id="galleryContentPageTitleBox">
					<div id="galleryContentPageTitle">갤러리</div>
					<div id="galleryContentPageSubTitle">추억이 담긴 회원의 사진을 확인하세요</div>
				</div>

				<div id="galleryContentPageContentBox">
					<div id="galleryContentPageContentInnerBox">
						<div id="galleryContentPageContentInfoBox">
							<div id="galleryContentPageContentTitleBox">
								<div id="galleryContentPageContentCtg">전체</div>
								<div id="galleryContentPageContentTitle" data-no="${gall.galleryNo }">${gall.galleryTitle }</div>
							</div>

							<div id="galleryContentPageSubInfoBox">
								<div id="galleryContentPageUploader" class="galleryContentPageSubInfo">
									<img id="galleryManager" class="galleryContentPageImage" src="<c:url value='/image/mapManager.png' />">
									${gall.memName }
								</div>
								<div id="galleryContentPageTime" class="galleryContentPageSubInfo">
									<img id="galleryTime" class="galleryContentPageImage" src="<c:url value='/image/boardTime.png' />">
									${gall.registDate }
								</div>								
								<div id="galleryContentPageView" class="galleryContentPageSubInfo">
									<img id="galleryGym" class="galleryContentPageImage" src="<c:url value='/image/myPageGym.png' />">
									${gall.gymName }
								</div>
							</div>
						</div>

						<div class="textAreaBox">
							<div id="galleryContentPageBoardText" class="textArea">
								${gall.galleryContents }
							</div>
						</div>
						
						<c:if test="${not empty sessionScope.sidGymNo }">
							<div id="galleryContentPageButtonBox">
								<div id="galleryContentPageModifyButton">
									<a href="/gallery/updateGallContentForm?galleryNo=${gall.galleryNo }">수정하기</a> 
								</div>
								<div id="galleryContentPageDeleteButton">
									<a href="#">삭제하기</a> 
								</div>
							</div>
						</c:if>
						
						<div id="galleryContentPageCommentAlarm">
							<img id="commentIcon" class="galleryContentPageImage" src="<c:url value='/image/comment.png' />"> 댓글
						</div>

						<div id="galleryContentPageCommentBox">
							<textarea id="galleryContentPageComment" rows="7" cols="100"></textarea>
							<input type="hidden" id="memId" value="user123"> 
							<input type="hidden" id="memNick" value="UserNick123">
						</div>

						<div id="galleryContentPageCommentButtonBox">
							<input type="file" id="addImgBtn">
							<div class="galleryContentPageCommentBtn" id="galleryContentPageCommentImageButton">이미지 추가</div>
							<input type="button" class="galleryContentPageCommentBtn" id="addCommentBtn" value="작성하기">
						</div>

						<div class="commentResult">							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/views/layout/bottom.jsp" />
</body>
</html>
