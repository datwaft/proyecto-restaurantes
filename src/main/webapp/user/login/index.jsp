<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/head.jsp" %> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
  <title>Login - TastyTasty</title>
</head>
<body>
  <%@ include file="/header.jsp" %> 
  <main class="body">
      <div class="box">
        <!-- START Vue: vmLogIn -->
        <div id="login">
          <h3 class="title">Log In</h3>
          <div class="input-group">
            <input type="text" v-model="email" placeholder="Email Address" :class="{invalid: !isEmailValid}">
            <div><i class="fas fa-at"></i></div>
          </div>
          <div class="input-group">
            <input type="password" v-model="password" placeholder="Password" :class="{invalid: !isPasswordValid}">
            <div><i class="fas fa-asterisk"></i></div>
          </div>
          <button class="button" @click="submit()" v-bind:disabled="!isValid">Login</button>
          <button class="alt-button" onclick="location.href = '${pageContext.request.contextPath}/user/register';">Register</button>
        </div>
        <!-- END Vue: vmLogIn -->
      </div>
  </main>
  <%@ include file="/footer.jsp" %> 
</body>
<script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
</html>
