<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/head.jsp" %> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user.css">
  <title>Account - TastyTasty</title>
  <script>$(window).on('load', () => $('#a-account').addClass('active'))</script>
</head>
<body>
  <%@ include file="/header.jsp" %> 
  <main class="body">
    <div class="main">
      <div id="app">
        <div class="box">
          <h3>Welcome {{ fullName }}</h3>
        </div>
        <div class="box">
          <div class="title">Edit Details</div>
          <div class="input-group">
            <input
              type="text"
              v-model.trim="data.firstname"
              placeholder="First Name"
              :class="{invalid: !isFirstNameValid}">
            <input
              type="text"
              v-model.trim="data.lastname"
              placeholder="Last Name"
              :class="{invalid: !isLastNameValid}">
          </div>
          <div class="input-group">
            <input
              type="text"
              v-model.trim="data.cellphone"
              placeholder="Cellphone" 
              :class="{invalid: !isCellphoneValid}">
            <input
              type="text"
              v-model.trim="data.email"
              disabled>
          </div>
          <div class="title">Change Password</div>
          <div class="input-group">
            <input
              type="password"
              v-model.trim="data.oldpassword"
              placeholder="Old Password" 
              :class="{invalid: !isOldPasswordValid}">
          </div>
          <div class="input-group">
            <input
              type="password"
              v-model.trim="data.password"
              placeholder="New Password" 
              :class="{invalid: !isNewPasswordValid}">
            <input
              type="password"
              v-model.trim="data.repassword"
              placeholder="New Password Confirmation" 
              :class="{invalid: !isRePasswordValid}">
          </div>
          <button class="button" @click="submit()" v-bind:disabled="!isValid">Change</button>
        </div>
      </div>
    </div>
  </main>
  <%@ include file="/footer.jsp" %> 
</body>
<script src="${pageContext.request.contextPath}/resources/js/account.js" defer></script>
</html>
