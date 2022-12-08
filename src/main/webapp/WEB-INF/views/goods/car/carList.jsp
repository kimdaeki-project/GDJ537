<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- meta tag 추가 -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>
<!-- 공통 css, js, jquery -->
<c:import url="../../temp/layout_header.jsp"></c:import>
</head>

<!-- body ID 작성 -->
<body id="page-top">

	<!-- Page Wrapper 전체 Contents Wrapper -->
	<div id="wrapper">

		<!-- Sidebar import -->
		<c:import url="../../temp/layout_sidebar.jsp"></c:import>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">

				<!-- Topbar import-->
				<c:import url="../../temp/layout_topbar.jsp"></c:import>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<section class="container d-flex flex-wrap justify-content-center">
					
						<!-- Page Heading -->
						<h1 class="h3 mb-4 text-gray-800">차량 정보</h1>
						<table class="table table-hover justify-content-right" style="text-align: center;">
							<tr>
								<th>차량번호</th>
								<th>모델명</th>
								<th>상세정보</th>
								<th>예약</th>
							</tr>
							<c:forEach items="${goods}" var="goods">
								<tr>
									<td>${goods.carNum }</td>
									<td>${goods.name }</td>
									<td>
										<a href="./carDetail?id="${goods.id}>
											<button type="button" class="btn btn-outline-none">정보 보기</button>
										</a>
									</td>
									<td>
										<a href="./carReserve?id=${goods.id}">
											<button type="button" class="btn btn-outline-none">예약하기</button>
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
						
						<div>-------------------------------예약 리스트--------------------------------------</div>

						<table class="table table-hover justify-content-right"
							style="text-align: center;">
							<tr>
								<th>예약 번호</th>
								<th>예약 날짜</th>
								<th>반납 날짜</th>
								<th>예약자</th>
								<th>예약 변경</th>
								<th>예약 취소</th>
							</tr>
							<c:forEach items="${reserves}" var="reserve">


								<tr>
									<td>${reserve.reserveNum}</td>
									<td>${reserve.startTime}</td>
									<td>${reserve.endTime}</td>
									<td>${reserve.memberNum}</td>
									<td><a href="./carReserveChange?reserveNum=${reserve.reserveNum}" class="btn btn-outline-none">변경</a></td>
									<td><a href="#" class="btn btn-outline-none">취소</a></td>
								</tr>

							</c:forEach>
						</table>
					</section>

				</div>
				<!-- End Page Content -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer import -->
			<c:import url="../../temp/layout_footer.jsp"></c:import>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>

	<!-- Scroll Top, Logout Modal import -->
	<c:import url="../../temp/layout_top_logoutModal.jsp"></c:import>
</body>
</html>