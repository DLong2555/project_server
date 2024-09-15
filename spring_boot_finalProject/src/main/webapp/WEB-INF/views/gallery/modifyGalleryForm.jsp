<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Gallery</title>
<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
<script src="<c:url value='/js/modifyGalleryForm.js'/>"></script>
<c:import url="/WEB-INF/views/layout/head.jsp" />
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp" />
	<div class="bodyContents">
		<div id="modifyGalleryPage">
			<div id="modifyGalleryPageContainer" class="noto-sans-kr">
				<div id="modifyGalleryPageTitleBox">
					<div id="modifyGalleryPageTitle">갤러리</div>
					<div id="modifyGalleryPageSubTitle">추억이 담긴 회원의 사진을 확인하세요</div>
				</div>

				<div id="modifyGalleryPageBox">
					<div class="modifyGalleryPageInputBox" data-no="${gall.galleryNo }">
						<label for="galleryCategory">사진 제목</label>
						<input type="text" id="galleryName" name="galleryName" placeholder="제목을 입력하세요" value="${gall.galleryTitle }" required>
					</div>
				</div>

				<!-- 날짜 수정 -->
				<div class="modifyGalleryPageInputBox">
					<label for="galleryDate">사진 날짜</label>
					<input type="date" id="galleryDate" name="galleryDate" class="shortInput" value="${gall.registDate }" required>
				</div>

				<div class="textAreaBox">
					<label for="GalleryMain">갤러리 작성</label>
					<div id="galleryMain" class="textArea" contenteditable="true" data-img="${gall.galleryImg }">${gall.galleryContents }</div>
				</div>
				

				<!-- 수정 버튼 -->
				<div id="modifyGalleryPageButtonBox">
					<input type="file" id="fileInputMain" name="fileInputMain" multiple>
					<div id="galleryMainImageInputButton">이미지 업로드</div>
					<input type="button" id="modifyGalleryBtn" class="btn" value="수정완료">
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/layout/bottom.jsp" />
</body>
</html>
