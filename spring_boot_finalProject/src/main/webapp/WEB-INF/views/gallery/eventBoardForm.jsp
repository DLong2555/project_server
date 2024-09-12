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
					<div id="galleryViewTitle">특수활동</div>
					<div id="galleryViewSubTitle">추억이 담긴 회원의 사진을 확인하세요</div>
				</div>

				<div id="galleryViewMenuBox">
					<div id="galleryViewMenuBox2">
						<div class="galleryViewMenuEach">
							<a href="/gallery/gallery"  style="font-weight: 600; font-size: 21px; text-decoration: underline; text-underline-offset: 15px; text-decoration-thickness: 4px;">특수활동</a>
						</div>
					</div>

					<form id="gallerySearchForm" method="get" action="<c:url value='/gallery/gallerySearch' />">
						<img id="gallerySearchImage" src="<c:url value='/image/mapSearch.png' />">
						<input type="text" id="gallerySearch" name="keyword"> <input type="submit" id="gallerySearchBtn" value="검색">
					</form>
				</div>
				<div id="galleryViewList">
					<div class="galleryViewListRow">
						<div class="galleryViewImageCell">
							<img src="<c:url value='/image/mainPageGallery1.jpg' />" alt="겨울 놀이" class="galleryViewImage">
						</div>
						<div class="galleryViewInfoCell">
							<div class="galleryViewDescription">겨울 놀이</div>
							<div class="galleryViewDate">2024-12-18</div>
						</div>
					</div>

					<div class="galleryViewListRow">
						<div class="galleryViewImageCell">
							<img src="<c:url value='/image/mainPageGallery1.jpg' />" alt="학교 행사" class="galleryViewImage">
						</div>
						<div class="galleryViewInfoCell">
							<div class="galleryViewDescription">학교 행사</div>
							<div class="galleryViewDate">2024-11-05</div>
						</div>
					</div>

					<div class="galleryViewListRow">
						<div class="galleryViewImageCell">
							<img src="<c:url value='/image/mainPageGallery1.jpg' />" alt="가을 산책" class="galleryViewImage">
						</div>
						<div class="galleryViewInfoCell">
							<div class="galleryViewDescription">가을 산책</div>
							<div class="galleryViewDate">2024-10-12</div>
						</div>
					</div>

					<div class="galleryViewListRow">
						<div class="galleryViewImageCell">
							<img src="<c:url value='/image/mainPageGallery1.jpg' />" alt="친구들과의 추억" class="galleryViewImage">
						</div>
						<div class="galleryViewInfoCell">
							<div class="galleryViewDescription">친구들과의 추억</div>
							<div class="galleryViewDate">2024-09-28</div>
						</div>
					</div>

					<div class="galleryViewListRow">
						<div class="galleryViewImageCell">
							<img src="<c:url value='/image/mainPageGallery1.jpg' />" alt="여름 캠프" class="galleryViewImage">
						</div>
						<div class="galleryViewInfoCell">
							<div class="galleryViewDescription">여름 캠프</div>
							<div class="galleryViewDate">2024-07-20</div>
						</div>
					</div>

					<div class="galleryViewListRow">
						<div class="galleryViewImageCell">
							<img src="<c:url value='/image/mainPageGallery1.jpg' />" alt="수영장 방문" class="galleryViewImage">
						</div>
						<div class="galleryViewInfoCell">
							<div class="galleryViewDescription">수영장 방문</div>
							<div class="galleryViewDate">2024-06-05</div>
						</div>
					</div>

					<div class="galleryViewListRow">
						<div class="galleryViewImageCell">
							<img src="<c:url value='/image/mainPageGallery1.jpg' />" alt="운동회 단체 사진" class="galleryViewImage">
						</div>
						<div class="galleryViewInfoCell">
							<div class="galleryViewDescription">운동회 단체 사진</div>
							<div class="galleryViewDate">2024-05-15</div>
						</div>
					</div>

					<div class="galleryViewListRow">
						<div class="galleryViewImageCell">
							<img src="<c:url value='/image/mainPageGallery1.jpg' />" alt="봄 소풍" class="galleryViewImage">
						</div>
						<div class="galleryViewInfoCell">
							<div class="galleryViewDescription">봄 소풍</div>
							<div class="galleryViewDate">2024-04-10</div>
						</div>
					</div>
				</div>
					<div id="galleryViewButtonBox">
	                    <div class="galleryViewWriteButton">
	                        <a href="<c:url value='/gallery/insertEventFrom' />">글쓰기</a>
	                    </div>
	                </div>

				<div class="galleryViewPagination">
					<div class="galleryViewPaginationMove">
						<div class="galleryViewPaginationDoubleLeft"></div>
						<div class="galleryViewPaginationDoubleLeft"></div>
					</div>
					<div class="galleryViewPaginationMove">
						<div class="galleryViewPaginationDoubleLeft"></div>
					</div>
					<div id="galleryViewNumberBox">
						<c:forEach var="i" begin="1" end="${totalPages}">
							<a href="?ctg=${ctg}&page=${i}&pageSize=${pageSize}" class="${i == currentPage ? 'active' : ''}">${i}</a>
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
