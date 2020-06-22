<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/head.jsp" %> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/orders.css">
  <title>Orders - TastyTasty</title>
  <script>$(window).on('load', () => $('#a-orders').addClass('active'))</script>
</head>
<body>
  <%@ include file="/header.jsp" %> 
  <main class="body">
    <div class="main">
      <div id="app">
        <div class="box">
          <div class="title">Bills</div>
        </div>
        <div class="box">
          <component-bill
            v-for="(bill, key) in bills"
            :object="bills[key]"
            :key="key">
          </component-bill>
        </div>
      </div>
    </div>
  </main>
  <%@ include file="/footer.jsp" %> 
</body>
<script src="${pageContext.request.contextPath}/resources/js/orders.js" defer></script>
</html>
