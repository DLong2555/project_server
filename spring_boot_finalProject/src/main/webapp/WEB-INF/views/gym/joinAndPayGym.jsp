<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/layout/head.jsp" />
<script src="<c:url value='/js/joinAndPayGym.js'/>"></script>
<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
<script src="<c:url value='/js/mapRegistGym.js'/>"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp" />
	<div class="bodyContents">
		<div id="joinAndPayGym">
			<div id="payGymContainer" class="noto-sans-kr">
				<div id="payGymTitleBox">
					<div id="payGymTitle">회비 납부</div>
				</div>
				<div id="payGymContentsBox">
					<div id="payGymLeftBox">
						<div id="payGymLeftTitleBox">
							<div id="payGymLeftTitle" data-ctg="${ctg }">회원 선택</div>
						</div>
						<c:forEach var="child" items="${chvoList }">
							<div id="payGymUserBox">
								<div class="payGymUserEach">
									<c:if test="${ctg eq '회비' }">						
									<div class="payGymUserEachInfoBox">
										<input type="hidden" value="${child.childNo}" class="payChildNo">
											<div class="payGymUserEachTitle">
												<input type="checkbox" id="payGymCheckBox" class="payGymCheckBox" name="payGymCheckBox" value="payGymCheckBox" checked>${child.childName}
											</div>
										<div class="payGymUserEachData">
											<img src="/image/myPageGym.png" class="payGymImage" onclick="editField('memHp')">
											<div class="payGymUserEachInfo">
												<span class="payGymName">${child.gymName}</span>
											</div>
											<input type="button" id="payGymSearch" value="체육관 검색" class="payGymBtn" data-value="${child.childNo}">
										</div>
										<div class="payGymUserEachData">
											<img src="/image/payGymRegister.png" class="payGymImage" onclick="editField('memHp')">
											<div class="payGymUserEachInfo">
												<div class="payGymSelect">
													<div class="payGymSelectPlaceholder" data-value="1">
														1개월
														<div class="payGymDown"></div>
													</div>
													<div class="payGymSubSelect">
														<div class="payGymSubSelectEach">1개월</div>
														<div class="payGymSubSelectEach">2개월</div>
														<div class="payGymSubSelectEach">3개월</div>
														<div class="payGymSubSelectEach">4개월</div>
														<div class="payGymSubSelectEach">5개월</div>
														<div class="payGymSubSelectEach">6개월</div>
														<div class="payGymSubSelectEach">7개월</div>
														<div class="payGymSubSelectEach">8개월</div>
														<div class="payGymSubSelectEach">9개월</div>
														<div class="payGymSubSelectEach">10개월</div>
														<div class="payGymSubSelectEach">11개월</div>
														<div class="payGymSubSelectEach">12개월</div>
													</div>
												</div>
											</div>
										</div>
										<div class="payGymUserEachData">
											<img src="/image/payGymMoney.png" class="payGymImage" onclick="editField('memHp')">
											<div class="payGymUserEachInfo">
												<c:if test="${child.gymName != '미등록'}">
													<input type="hidden" class="costPayGymPrice" value="<c:out value="${map[child.gymName]}" />">
													<span class="payGymPrice"> <c:out value="${map[child.gymName]}" />원
													</span>
												</c:if>
												<c:if test="${child.gymName eq '미등록'}">
													<input type="hidden" class="costPayGymPrice" value="">
													<span class="payGymPrice">미등록</span>
												</c:if>
											</div>
										</div>
										<div class="payGymUserEachData">
											<img src="/image/myPageDate.png" class="payGymImage" onclick="editField('memHp')">
											<div class="payGymUserEachInfo">
												<input type="date" class="registDate" value="2024-06-15">
											</div>
										</div>
									</div>
									</c:if>
									
									
									<c:if test="${ctg eq '특수' }">
									<div class="payGymUserEachInfoBox">
										<input type="hidden" value="${child.childNo}" class="payChildNo">
										<div class="payGymUserEachTitle">
											<input type="checkbox" id="payGymCheckBox" class="payGymCheckBox" name="payGymCheckBox" value="payGymCheckBox" checked>${child.childName}
										</div>
										<div class="payGymUserEachData">
											<img src="/image/event.png" class="payGymImage" onclick="editField('memHp')">
											<div class="payGymUserEachInfo">
												<span class="payEventName" data-no="${event.eventNo }">${event.eventTitle}</span>
											</div>
										</div>
										<div class="payGymUserEachData">
											<img src="/image/myPageGym.png" class="payGymImage" onclick="editField('memHp')">
											<div class="payGymUserEachInfo">
												<span class="payGymName">${child.gymName}</span>
											</div>
										</div>
										<div class="payGymUserEachData">
											<img src="/image/payGymMoney.png" class="payGymImage" onclick="editField('memHp')">
											<div class="payGymUserEachInfo">
												<span class="payGymPrice">${event.eventPrice}</span>																								
											</div>
										</div>
									</div>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>

					<div id="payGymRightBox">
						<div id="payGymRightContents">
							<div id="payGymRightTitleBox">
								<div id="payGymRightTitle">결제 상세</div>
								<div id = "payGymEventSelect" class="payGymEventSelect">
									<div id = "payGymEventPlaceholder" class="payGymEventSelectPlaceholder" data-value="신용카드">
										신용카드
										<div class="payGymEventDown"></div>
									</div>
									<div class="payGymEventSubSelect">
										<div class="payGymEventSubSelectEach" data-value="신용카드">신용카드</div>
										<div class="payGymEventSubSelectEach" data-value="계좌이체">계좌이체</div>
										<div class="payGymEventSubSelectEach" data-value="카카오페이">카카오페이</div>
										<div class="payGymEventSubSelectEach" data-value="네이버페이">네이버페이</div>
									</div>
								</div>
							</div>
							<div id="payGymPayBox">
								<div id="payGymAddBox"></div>
								<div id="payGymPlusBox">+</div>
								<div id="payGymAddResult">총 가격 : 원</div>
							</div>
							<div id="payGymPayButton">
								<a id="payGymPayA" href="<c:url value='#' />">0원 결제하기</a>
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