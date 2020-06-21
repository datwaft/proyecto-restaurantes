<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/admin/head.jsp" %> 
  <link rel="stylesheet" href="/resources/css/admin.css">
  <title>LogIn Admin - TastyTasty</title>
</head>
<body>
  <!-- START Vue: vmNotification -->
  <div id="notification">
    <transition name="fade">
      <div class="banner" v-show="isValid" :class="classObject">
        <div class="title">{{ title }}</div>
        <div class="data">
          {{ description }}
        </div>
      </div>
    </transition>
  </div>
  <!-- END Vue: vmNotification -->
  <main class="full-body">
    <div id="login">
      <div class="box">
        <div class="title">LogIn</div>
      </div>
      <div class="box">
        <div class="input-group">
          <input
            type="text"
            v-model.trim="username"
            placeholder="Username"
            :class="{invalid: !isUsernameValid}">
        </div>
        <div class="input-group">
          <input
            type="password"
            v-model.trim="password"
            placeholder="Password"
            :class="{invalid: !isPasswordValid}">
        </div>
      </div>
      <div class="box">
        <button class="button" @click="submit" v-bind:disabled="!isValid">Log In</button>
      </div>
    </div>
  </main>
</body>
<script src="${pageContext.request.contextPath}/resources/js/admin/login.js" defer></script>
</html>
