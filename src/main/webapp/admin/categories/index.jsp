<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/admin/head.jsp" %> 
  <link rel="stylesheet" href="/resources/css/admin.css">
  <title>Categories - TastyTasty</title>
  <script>
    $(window).on('load', () => sessionData.currentPage = "Categories Menu")
  </script>
</head>
<body>
  <%@ include file="/admin/header.jsp" %>
  <%@ include file="/admin/nav.jsp" %>
  <main class="body">
    <div id="categories">
      <div class="box">
        <div class="title">Category List</div>
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
        <div v-if="categories.length === 0">
          There are no categories
        </div>
        <list-item v-for="category in filtered" :item="category" :key="category.id" @update="addmodify($event)">
          <b>ID: </b>{{ category.id }}
          <br>
          <b>Description: </b>{{ category.description }}
        </list-item>
      </div>
      <div class="box">
        <button class="button" @click="addmodify()">Add new category</button>
      </div>
      <overlay-box v-model="isOverlayShown">
        <template #title>
          <template v-if="id==null">Add a new category</template>
          <template v-else>Update a category</template>
        </template>
        <div class="input-group">
          <input
            type="text"
            v-model.trim="description"
            placeholder="Description"
            :class="{invalid: !isDescriptionValid}">
        </div>
        <button class="button" @click="submit" v-bind:disabled="!isValid">
          <template v-if="id==null">Add</template>
          <template v-else>Update</template>
        </button>
      </overlay-box>
    </div>
  </main>
</body>
<script src="${pageContext.request.contextPath}/resources/js/admin/categories.js"></script>
</html>
