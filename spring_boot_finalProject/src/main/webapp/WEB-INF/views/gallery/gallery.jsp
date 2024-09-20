<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리</title>
<c:import url="/WEB-INF/views/layout/head.jsp"></c:import>
<script src="<c:url value='/js/gallery.js'/>"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp"></c:import>
	<div class="bodyContents">
		<div id="galleryView">
			<div id="galleryViewContainer" class="noto-sans-kr">
				<div id="galleryViewTitleBox">
					<div id="galleryViewTitle">갤러리</div>
					<div id="galleryViewSubTitle">추억이 담긴 회원의 사진을 확인하세요</div>
				</div>

				<div id="galleryViewMenuBox">
					<div id="galleryViewMenuBox2">
						<div class="galleryViewMenuEach">
							<a href="/gallery/gallery" style="font-weight: 600; font-size: 21px; text-decoration: underline; text-underline-offset: 15px; text-decoration-thickness: 4px;">전체사진</a>
						</div>
					</div>

					<form id="gallerySearchForm" method="get" action="<c:url value='/gallery/gallerySearch' />">
						<img id="gallerySearchImage" src="<c:url value='/image/mapSearch.png' />">
						<input type="text" id="gallerySearch" name="keyword">
						<input type="submit" id="gallerySearchBtn" value="검색">
					</form>
				</div>
				<div id="galleryViewList">
					<c:forEach var="gall" items="${gallList }">
						<div class="galleryViewListRow" data-no="${gall.galleryNo }">
							<div class="galleryViewImageCell">
								<img src="<c:url value='/images/${gall.galleryImg }' />" alt="${gall.galleryTitle }" class="galleryViewImage">
							</div>
							<div class="galleryViewInfoCell">
								<div class="galleryViewDescription">${gall.galleryTitle }</div>
								<div class="galleryViewDate">${gall.registDate }</div>
							</div>
						</div>
					</c:forEach>
				</div>
				
				<c:if test="${not empty gymNo }">
					<div id="galleryViewButtonBox">
						<div class="galleryViewWriteButton">
							<a href="<c:url value='/gallery/insertGalleryForm' />">갤러리 작성</a>
						</div>
					</div>
				</c:if>
				<div class="galleryViewPagination">
					<div class="galleryViewPaginationMove">
						<div class="galleryViewPaginationDoubleLeft"></div>
						<div class="galleryViewPaginationDoubleLeft"></div>
					</div>
					<div class="galleryViewPaginationMove">
						<div class="galleryViewPaginationDoubleLeft"></div>
					</div>
					<div id="galleryViewNumberBox">
						<c:forEach var="i" begin="1" end="${totalPage}">
							<a href="?page=${i}&pageSize=${pageSize}&gymName=${gymName != null ? gymName : ''}" class="${i == currentPage ? 'active' : ''}">${i}</a>							
						</c:forEach>
					</div>
					<div class="galleryViewPaginationMove">
						<div class="galleryViewPaginationDoubleRight"></div>
					</div>
					<div class="galleryViewPaginationMove">
						<div class="galleryViewPaginationDoubleRight"></div>
						<div class="galleryViewPaginationDoubleRight"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/layout/bottom.jsp"></c:import>
</body>
</html>
