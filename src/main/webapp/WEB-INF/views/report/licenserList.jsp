<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- meta tag 추가 -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>
<script defer src="/js/hjs/list.js"></script>
<c:import url="../common/jquery.jsp"></c:import>
<style>
	#listDiv{
		display: none;
	}
</style>
<!-- 공통 css, js, jquery -->
<c:import url="../temp/layout_header.jsp"></c:import>
</head>

<!-- body ID 작성 -->
<body id="page-top">

	<!-- Page Wrapper 전체 Contents Wrapper -->
    <div id="wrapper">

		<!-- Sidebar import -->
		<c:import url="../temp/layout_sidebar.jsp"></c:import>
		<!-- End of Sidebar -->
		
		 <!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
			
				<!-- Topbar import-->
				<c:import url="../temp/layout_topbar.jsp"></c:import>
				<!-- End of Topbar -->
				
				<!-- Begin Page Content -->
	            <div class="container-fluid">
	
	            	<!-- Page Heading -->
	            	<h1 class="h3 mb-4 text-gray-800">모든 승인자 리스트</h1>
	            	
	            	<table border="1" class="table table-striped table-hover col-lg-16">
	            	
	            	<sec:authentication property="Principal" var="user"/>
	            	
						<thead class="table table-dark">
							<tr>
								<th>아이디</th>
								<th>이름</th>
								<th>부서명</th>
								<th>직급</th>
							</tr>
						</thead>
		
							<tbody>
								<c:forEach items="${list}" var="reportVO">
								
								
								<div id="listDiv">
									<input id="roleId" name="roleNum">
										  <ul>
										   		<li class="roleLi" data-roleNum="1">사장</li>
										   		<li class="roleLi" data-roleNum="2">부장</li>
										   		<li class="roleLi" data-roleNum="3">과장</li>
										   		<li class="roleLi" data-roleNum="4">대리</li>
										   		<li class="roleLi" data-roleNum="5">주임</li>
										   		<li class="roleLi" data-roleNum="7">사원</li>
						  				 </ul>
								</div>
								
									
									<tr id="tdiv">
										<td>${reportVO.userVO.id}</td>
										<td>${reportVO.userVO.name}</td>
				 						<td>${reportVO.departmentVO.depName}</td>
										<td id="tdRoleName">${reportVO.roleVO.roleName}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

	
	            </div>
	            <!-- End Page Content -->
					
			</div>
			<!-- End of Main Content -->
			
			<!-- Footer import -->
			<c:import url="../temp/layout_footer.jsp"></c:import>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>

	<!-- Scroll Top, Logout Modal import -->
	<c:import url="../temp/layout_top_logoutModal.jsp"></c:import>
</body>
</html>