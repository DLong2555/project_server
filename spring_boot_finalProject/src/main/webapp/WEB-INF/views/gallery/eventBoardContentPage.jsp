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
								<div id="galleryContentPageContentCtg">단체</div>
								<div id="galleryContentPageContentTitle">여름 휴가 사진</div>
							</div>

							<div id="galleryContentPageSubInfoBox">
								<div id="galleryContentPageUploader" class="galleryContentPageSubInfo">
									<img id="galleryManager" class="galleryContentPageImage" src="<c:url value='/image/mapManager.png' />">
									관리자
								</div>
								<div id="galleryContentPageTime" class="galleryContentPageSubInfo">
									<img id="galleryTime" class="galleryContentPageImage" src="<c:url value='/image/boardTime.png' />">
									2024년 9월 9일(월) 17시 16분 15초
								</div>
								<div id="galleryContentPageView" class="galleryContentPageSubInfo">
									<img id="galleryView" class="galleryContentPageImage" src="<c:url value='/image/boardView.png' />">
									1234
								</div>
								<div id="galleryContentPageView" class="galleryContentPageSubInfo">
									<img id="galleryGym" class="galleryContentPageImage" src="<c:url value='/image/myPageGym.png' />">
									참 좋은 인성 합기도
								</div>
							</div>
						</div>

						<div class="textAreaBox">
							<div id="galleryContentPageBoardText" class="textArea">
								<img src="/image/mainPageGallery1.jpg" alt="gallery image" id="galleryMainImage">
							</div>
						</div>

						<div class="textAreaBox">
							<div id="galleryContentPageBoardText2" class="textArea">
								<div id="galleryContentPageDescription">이것은 2024년 체육관에서 찍은 사진입니다.</div>
							</div>
						</div>
						
						<div id="galleryContentPageButtonBox">
							<div id="galleryContentPageModifyButton">
								<a href="#">수정하기</a> 
							</div>
							<div id="galleryContentPageDeleteButton">
								<a href="#">삭제하기</a> 
							</div>
						</div>

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
							<ul>
								<li>
									<div class="commentNick">User1</div> 
									<div class="commentText">정말 멋진 사진이에요!</div> 
									<div class="commentDate">2024-09-09</div> 
								</li>
								<li>
									<div class="commentNick">User2</div> 
									<div class="commentText">다음에 저도 가보고 싶네요.</div>
									<div class="commentDate">2024-09-08</div> 
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/views/layout/bottom.jsp" />
</body>
</html>
