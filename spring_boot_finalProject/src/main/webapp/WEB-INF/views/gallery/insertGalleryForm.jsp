<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<label for="galleryCategory">사진 제목</label>
						<input type="text" id="galleryName" name="galleryName" placeholder="제목을 입력하세요" required>
					</div>
				</div>


				<!-- 날짜 등록 -->
				<div class="insertGalleryPageInputBox">
					<label for="galleryDate">사진 날짜</label>
					<input type="date" id="galleryDate" name="galleryDate" class="shortInput" required>
				</div>

				<div class="textAreaBox">
					<label for="GalleryMain">갤러리 작성</label>
					<div id="galleryMain" class="textArea" contenteditable="true"></div>
				</div>

				<!-- 등록 버튼 -->
				<div id="insertGalleryPageButtonBox">
					<input type="file" id="fileInputMain" class="_fileInput" multiple title="추가" accept="image/png,image/jpg,image/jpeg,image/gif,image/*,video/avi,video/wmv,video/mpg,video/mpeg,video/mov,video/asf,video/skm,video/k3g,video/flv,video/mp4,video/3gp,video/m4v,video/mts,video/ts,video/*">
					<div id="galleryMainImageInputButton">이미지 업로드</div>
					<input type="submit" id="insertGalleryBtn" class="btn" value="등록">
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/layout/bottom.jsp" />
</body>
</html>
