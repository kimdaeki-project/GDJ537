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
						<h1 class="h3 mb-4 text-gray-800">회의실 정보</h1>
						<table class="table table-hover justify-content-right" style="text-align: center;">
							<tr>
								<th>이름</th>
								<th>위치</th>
								<th>상세정보</th>
								<th>예약하기</th>
								<th>예약된 정보 보기</th>
							</tr>
							<c:forEach items="${goodVO }" var="good">
								<input type="hidden" value="${good.id}" class="ROID">
								<tr>
									<td>${good.name }</td>
									<td>${good.location }</td>
									<td>
										<a href="./roomDetail?id=${good.id }">
											<button type="button" class="btn btn-outline-none">보기</button>
										</a>
									</td>
									<td>
										<a href="./roomReserve?id=${good.id }">
											<button type="button" class="btn btn-outline-none">예약</button>
										</a>
									</td>
									<td>
										<a href="./roomResInfo?id=${good.id }">
											<button type="button" class="btn btn-outline-none">보기</button>
										</a>
									</td>
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