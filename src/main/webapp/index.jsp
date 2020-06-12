<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/head.jsp" %> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
  <title>Online Order! - TastyTasty</title>
</head>
<body>
  <%@ include file="/header.jsp" %> 
  <main class="body">
    <img src="${pageContext.request.contextPath}/resources/images/foodbg.jpg">
    <div class="center-w">
      <div class="box">
        <h1 class="main-header">Order delicious food online</h1>
        <button class="main-button button" onclick="window.location.href='${pageContext.request.contextPath}/menu'">Find</button>
      </div>
    </div>
  </main>
  <%@ include file="/footer.jsp" %> 
</body>
</html>

