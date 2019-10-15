<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();

	String name = "";
	if (principal != null) {
		name = auth.getName();
	}
%>
<div class="header">
	<nav>
		<sec:authorize access="isAnonymous()">
			<ul class="nav nav-pills pull-right">
				<li role="presentation"><a href="/user/login/loginPage">로그인</a></li>
				<li role="presentation"><a href="/user/signup/step1">회원가입</a></li>
			</ul>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<ul class="nav nav-pills pull-right">
				<li role="presentation"><a href="#">${authInfo.name}님,
						반갑습니다.</a></li>
				<li role="presentation"><a href="/user/logout">로그아웃</a></li>
			</ul>
		</sec:authorize>
	</nav>

	<h3 class="text-muted">
		<a href="/"><b>우리</b>언제<b>만날까?</b></a>
	</h3>

	<div>
		<ul class="nav nav-pills">
			<%-- 탭:모임 만들기 --%>
			<li role="presentation"><a href="/meeting/make">새로운 모임 만들기</a></li>
			<%-- 탭:모임관리 --%>
			<li role="presentation"><a href="#" id=meetingList>모임 관리</a></li>
			<%-- 탭:받은초대 --%>
			<li role="presentation"><a href="/invitation/index">받은 초대</a></li>
		</ul>
	</div>

	<form method="post" action="/meeting/list" id="postMeetingList">
		<input type="hidden" name="userId" value="<%=name%>"> <input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</div>
<script>
	$(document).ready(function() {
		$("#meetingList").on("click", function(e) {
			e.preventDefault();
			$("#postMeetingList").submit();
		});
	});
</script>