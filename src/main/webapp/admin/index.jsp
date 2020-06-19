<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/admin/head.jsp" %> 
  <link rel="stylesheet" href="/resources/css/admin.css">
  <title>Administrator - TastyTasty</title>
  <script>
    $(window).on('load', () => sessionData.currentPage = "Administrator")
  </script>
</head>
<body>
  <%@ include file="/admin/header.jsp" %>
  <%@ include file="/admin/nav.jsp" %>
  <main class="body">
    <div class="only-title">
      <h1>Welcome Administrator</h1>
      Please select one of the options on the left
    </h1>
  </main>
</body>
</html>
