<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/admin/head.jsp" %> 
  <link rel="stylesheet" href="/resources/css/admin.css">
  <title>Administrators - TastyTasty</title>
  <script>
    $(window).on('load', () => sessionData.currentPage = "Administrators Menu")
  </script>
</head>
<body>
  <%@ include file="/admin/header.jsp" %>
  <%@ include file="/admin/nav.jsp" %>
  <main class="body">
    <div id="admins">
      <div class="box">
        <div class="title">Administrators List</div>
      </div>
      <div class="box">
        <div class="input-group">
          <input
            type="text"
            v-model.trim="filter"
            placeholder="Please enter your filter">
          <select v-model='filterMode'>
            <option disabled value="">Please select one</option>
            <option v-for="filter in filters" :value="filter">
              {{ filter }}
            </option>
          </select>
        </div>
      </div>
      <div class="box">
        <div v-if="admins.length === 0">
          There are no administrators
        </div>
        <div class="item" v-for="admin of filtered" :id="admin.username">
          <b>Username: </b>{{ admin.username }}
          <br>
          <b>Password: </b>{{ admin.password }}
        </div>
      </div>
      <div class="box">
        <button class="button" @click="add()">New Administrator</button>
      </div>
      <overlay-box v-model="isOverlayShown">
        <template #title>
          Add a new administrator
        </template>
        <div class="input-group">
          <input
            type="text"
            v-model.trim="username"
            placeholder="Username"
            :class="{invalid: !isUsernameValid}">
        </div>
        <div class="input-group">
          <input
            type="text"
            v-model.trim="password"
            placeholder="Password"
            :class="{invalid: !isPasswordValid}">
        </div>
        <button class="button" @click="submit" v-bind:disabled="!isValid">Add</button>
      </overlay-box>
    </div>
  </main>
</body>
<script src="${pageContext.request.contextPath}/resources/js/admin/admins.js"></script>
</html>
