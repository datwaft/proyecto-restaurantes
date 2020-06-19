<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/admin/head.jsp" %> 
  <link rel="stylesheet" href="/resources/css/admin.css">
  <title>Users - TastyTasty</title>
  <script>
    $(window).on('load', () => sessionData.currentPage = "Users Menu")
  </script>
</head>
<body>
  <%@ include file="/admin/header.jsp" %>
  <%@ include file="/admin/nav.jsp" %>
  <main class="body">
    <div id="users">
      <div class="box">
        <div class="title">Users List</div>
      </div>
      <div class="box">
        <div v-if="users.length === 0">
          There are no users
        </div>
        <div class="item" v-for="user of users" :id="user.email">
          <b>Email: </b>{{ user.email }}
          <br>
          <b>Password: </b>{{ user.password }}
          <br>
          <b>Full Name: </b>{{ user.firstName + ' ' + user.lastName }}
          <br>
          <b>Cellphone: </b>{{ user.cellphone }}
        </div>
      </div>
    </div>
  </main>
</body>
<script src="${pageContext.request.contextPath}/resources/js/admin/users.js"></script>
</html>
