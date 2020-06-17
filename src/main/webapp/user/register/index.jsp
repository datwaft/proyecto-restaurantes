<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/head.jsp" %> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
  <title>Register - TastyTasty</title>
  <script>$(window).on('load', () => $('#a-register').addClass('active'))</script>
</head>
<body>
  <%@ include file="/header.jsp" %> 
  <main class="body">
      <div class="box">
        <!-- START Vue: vmRegister -->
        <div id="register">
          <h3 class="title">Register</h3>
          <div class="input-group">
            <input type="text" v-model.trim="firstname" placeholder="First Name" :class="{invalid: !isFirstNameValid}">
            <input type="text" v-model.trim="lastname" placeholder="Last Name" :class="{invalid: !isLastNameValid}">
          </div>
          <div class="input-group">
            <input type="text" v-model.trim="email" placeholder="Email Address" :class="{invalid: !isEmailValid}">
          </div>
          <div class="input-group">
            <input type="password" v-model="password" placeholder="Password" :class="{invalid: !isPasswordValid}">
            <input type="password" v-model="repassword" placeholder="Password Confirm" :class="{invalid: !isRePasswordValid}">
          </div>
          <div class="input-group">
            <input type="text" v-model.trim="cellphone" placeholder="Cellphone" :class="{invalid: !isCellphoneValid}">
          </div>
          <button class="button" @click="submit()" v-bind:disabled="!isValid">Register</button>
        </div>
        <!-- END Vue: vmRegister -->
      </div>
  </main>
  <%@ include file="/footer.jsp" %> 
</body>
<script src="${pageContext.request.contextPath}/resources/js/register.js"></script>
</html>
