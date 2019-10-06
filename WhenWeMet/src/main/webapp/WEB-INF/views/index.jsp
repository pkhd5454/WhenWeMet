<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>
</head>
<body class="main-pages">
	<div class="container">
		<%@ include file="/WEB-INF/views/includes/03_header.jsp"%>
		
		<div class="jumbotron">
			<h1>우리 언제 만날까?</h1>
			<p class="lead">일정을 생성하고 팀원들과 맞는 시간을 찾아보세요!</p>
		</div>
		<div class="row marketing">
			<c:catch>
				<c:choose>
					<c:when test="${empty authInfo}">
						<!-- 로그인되지 않았을 때 -->
					</c:when>
					<c:otherwise>
						<!-- 로그인 되었을 때 -->
					</c:otherwise>
				</c:choose>
			</c:catch>
			
		</div>
		<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
	</div>

</body>

</html>