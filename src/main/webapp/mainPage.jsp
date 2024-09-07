<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Using include directive -->
<%@ include file="header.jsp" %>

<!-- Page Content -->
<div>
    <h2>Main Content of the Page</h2>
    <p>This is the body of the page.</p>
</div>

<!-- Using jsp:include -->
<jsp:include page="footer.jsp" />
