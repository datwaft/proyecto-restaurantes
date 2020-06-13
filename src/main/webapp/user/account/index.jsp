<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/head.jsp" %> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user.css">
  <title>LogIn - TastyTasty</title>
  <script>$(window).on('load', () => $('#a-account').addClass('active'))</script>
</head>
<body>
  <%@ include file="/header.jsp" %> 
  <main class="body">
    <div class="main" id="app">
      <div class="box">
        
      </div>
    </div>
  </main>
  <%@ include file="/footer.jsp" %> 
</body>
<script src="${pageContext.request.contextPath}/resources/js/account.js" defer></script>
</html>
