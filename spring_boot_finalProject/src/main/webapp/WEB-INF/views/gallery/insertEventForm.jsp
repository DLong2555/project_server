<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert Gallery</title>
	<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
	<script src="<c:url value='/js/insertGalleryForm.js'/>"></script>
	<c:import url="/WEB-INF/views/layout/head.jsp" />
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp" />
	<div class="bodyContents">
		<div id="insertGalleryPage">
			<div id="insertGalleryPageContainer" class="noto-sans-kr">
				<div id="insertGalleryPageTitleBox">
					<div id="insertGalleryPageTitle">갤러리</div>
					<div id="insertGalleryPageSubTitle">추억이 담긴 회원의 사진을 확인하세요</div>
				</div>

				<div id="insertGalleryPageBox">
					<div class="insertGalleryPageInputBox">
						<label for="galleryCategory">카테고리 선택</label>
						<div class="insertGalleryPageSelect">
							<div class="insertGalleryPageSelectPlaceholder">
								전체사진
								<div class="insertGalleryPageDown"></div>
							</div>
							<div class="insertGalleryPageSubSelect">
								<div class="insertGalleryPageSubSelectEach" data-value="전체사진">전체사진</div>
								<div class="insertGalleryPageSubSelectEach" data-value="박동수">박동수</div>
								<div class="insertGalleryPageSubSelectEach" data-value="김대현">김대현</div>
							</div>
							<input type="text" id="galleryName" name="galleryName" required>
						</div>
					</div>
				</div>

				<!-- 날짜 등록 -->
				<div class="insertGalleryPageInputBox">
					<label for="galleryDate">작성 날짜</label>
					<input type="date" id="galleryDate" name="galleryDate" class="shortInput" required>
				</div>

					<div class="textAreaBox">
						<label for="GalleryMain">메인 사진</label>
						<div id="galleryMain" class="textArea" contenteditable="true"></div>
					</div>
					<div id="galleryMainImageInputBox">
						<div id="galleryMainImageInputButton">이미지 업로드</div>
						<input type="file" id="fileInputMain" name="fileInputMain">
						<div id="galleryMainImageBox"></div>
					</div>
					<div class="textAreaBox">
						<label for="galleryDetail">사진 설명</label>
						<div id="galleryDetail" class="textArea" contenteditable="true"></div>
					</div>
					<div id="galleryDetailImageInputBox">
						<div id="galleryDetailImageInputButton">이미지 업로드</div>
						<input type="file" id="fileInputDetail" name="fileInputDetail">
						<div id="galleryDetailImageBox"></div>
					</div>
				<!-- 등록 버튼 -->
				<div id="insertGalleryPageButtonBox">
					<input type="submit" id="insertGalleryBtn" class="btn" value="등록">
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/layout/bottom.jsp" />
</body>
</html>
