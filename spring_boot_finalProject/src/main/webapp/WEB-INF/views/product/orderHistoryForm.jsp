<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제내역</title>
<c:import url="/WEB-INF/views/layout/head.jsp"></c:import>
<script src="<c:url value='/js/orderHistory.js'/>"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/layout/top.jsp" />

	<div class="bodyContents">
		<div id="orderHistoryView">
			<div id="orderHistoryViewContainer" class="noto-sans-kr">
				<div id="orderHistoryTitleBox">
					<div id="orderHistoryTitle">상품 판매</div>
					<div id="orderHistorySubTitle">편리한 쇼핑, 다양한 상품이 한 자리에</div>
				</div>

				<c:set var="menuItems" value="${fn:split('주문,납부', ',')}" />
				<c:set var="selectedCtg" value="${ctg}" />

				<div id="orderHistoryMenuBox">
					<c:forEach items="${menuItems}" var="item" varStatus="status">
						<c:choose>
							<c:when test="${item eq selectedCtg}">
								<div class="orderHistoryMenuEach">
									<a href="/prd/orderHistoryForm?ctg=${item}"style="font-weight: 600; font-size: 21px; text-decoration: underline; text-underline-offset: 15px; text-decoration-thickness: 4px;">${item}내역</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="orderHistoryMenuEach">
									<a href="/prd/orderHistoryForm?ctg=${item}">${item}내역</a>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>

				<c:if test="${ctg eq '주문' }">
					<div id="orderHistoryMain">
						<c:forEach var="ordList" items="${hisList}">
							<div class="orderItem">
								<div class="orderItemHeader">
									<div class="orderDate">${ordList.ordDateFmt }</div>
									<div class="orderNumber">
										주문번호: <span>${ordList.ordNo }</span>
									</div>
								</div>
								<div class="orderItemDetails">
									<c:forEach var="prdList" items="${ordList.ordList}">
										<div class="orderItemDetail">
											<img src="<c:url value='/images/${prdList.prdImg }'/>" alt="상품 A 이미지" class="orderItemImage">
											<div class="orderItemInfo">
												<div class="orderItemName">${prdList.prdName }</div>
												<div class="orderItemSize">
													옵션: <span>${prdList.ordOption }</span>
												</div>
												<div class="orderItemQuantity">
													수량: <span>${prdList.ordQty }</span>
												</div>
												<div class="orderItemPrice">
													개당 가격: <span>${prdList.prdPrice }</span>
												</div>
												<div class="orderItemTotalPrice">
													총 가격: <span>${prdList.prdPrice * prdList.ordQty }</span>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${ctg eq '납부' }">
					<div id="orderHistoryMain">
						<c:forEach var="payList" items="${payHisList}">
							<div class="orderItem">
								<div class="orderItemHeader">
									<div class="orderDate"><span>[${payList.payCtg }] </span>${payList.payDateFmt }</div>
									<div class="orderNumber">
										납부번호: <span>${payList.payNo }</span>
									</div>
								</div>
								<div class="orderItemDetails">
									<c:forEach var="pay" items="${payList.payList}">
										<c:if test="${payList.payCtg eq '회비' }">
											<div class="orderItemDetail">
												<%-- <img src="<c:url value='/images/${prdList.prdImg }'/>" alt="상품 A 이미지" class="orderItemImage"> --%>
												<div class="orderItemInfo">
													<div class="orderItemName">${pay.childName }</div>
													<div class="orderItemSize">
														도장: <span>${pay.gymName }</span>
													</div>
													<div class="orderItemQuantity">
														개월: <span>${pay.payMonth }</span>개월
													</div>
													<div class="orderItemPrice">
														가격: <span>${pay.gymPrice }</span>
													</div>
													<div class="orderItemTotalPrice">
														총 가격: <span>${pay.gymPrice * pay.payMonth }</span>
													</div>
												</div>
											</div>
										</c:if>
										<c:if test="${payList.payCtg eq '특수' }">
											<div class="orderItemDetail">
												<%-- <img src="<c:url value='/images/${prdList.prdImg }'/>" alt="상품 A 이미지" class="orderItemImage"> --%>
												<div class="orderItemInfo">
													<div class="orderItemName">${pay.childName }</div>
													<div class="orderItemSize">
														도장: <span>${pay.gymName }</span>
													</div>
													<div class="orderItemSize">
														활동: <span>${pay.eventTitle }</span>
													</div>
													<div class="orderItemPrice">
														가격: <span>${pay.eventPrice }</span>
													</div>
													<div class="orderItemTotalPrice">
														총 가격: <span>${pay.eventPrice}</span>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/views/layout/bottom.jsp" />
</body>
</html>
