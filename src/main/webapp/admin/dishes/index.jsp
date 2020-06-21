<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/admin/head.jsp" %> 
  <link rel="stylesheet" href="/resources/css/admin.css">
  <title>Dishes - TastyTasty</title>
  <script>
    $(window).on('load', () => sessionData.currentPage = "Dishes Menu")
  </script>
</head>
<body>
  <%@ include file="/admin/header.jsp" %>
  <%@ include file="/admin/nav.jsp" %>
  <main class="body">
    <div id="dishes">
      <div class="box">
        <div class="title">Dishes List</div>
      </div>
      <div class="box">
        <div v-if="dishes.length === 0">
          There are no dishes
        </div>
        <list-item v-for="dish in dishes" :item="dish" :key="dish.id" @update="addmodify($event)">
          <b>ID: </b>{{ dish.id }}
          <br>
          <b>Name: </b>{{ dish.name }}
          <br>
          <b>Description: </b>{{ dish.description }}
          <br>
          <b>Category: </b>{{ dish.category.description }}
          <br>
          <b>Price: </b>{{ dish.price }}
        </list-item>
      </div>
      <div class="box">
        <button class="button" @click="addmodify()">Add new dish</button>
      </div>
      <overlay-box v-model="isOverlayShown">
        <template #title>
          <template v-if="mode==='add'">Add a new dish</template>
          <template v-else>Update a dish</template>
        </template>
        <!-- CONTENT !-->

        <div class="input-group">
          <input
            type="text"
            v-model.trim="dish.name"
            placeholder="Name"
            :class="{invalid: !isNameValid}">
          <select v-model="dish.category" :class="{invalid: !isCategoryValid}">
            <option disabled :value=null>Please select one</option>
            <option v-for="category in categories" :value="category">
              {{ category.description }}
            </option>
          </select>
          <input
            type="text"
            v-model.number="dish.price"
            placeholder="Price"
            :class="{invalid: !isPriceValid}">
        </div> 
        
        <div class="input-group">
          <input
            type="text"
            v-model.trim="dish.description"
            placeholder="Description"
            :class="{invalid: !isDescriptionValid}">
        </div>

        <div class="mini" v-if="mode==='modify'">
          <additional-category
            v-for="additionalCategory in additionalsFiltered"
            :key="additionalCategory.category.id"
            v-model="additionalCategory.category"
            @add="addAditional($event)">
            <additional-item
              v-for="(item, index) in additionalCategory.additionals"
              :key="item.id"
              v-model="additionalCategory.additionals[index]">
            </additional-item>
          </additional-category>
          <button class="button" @click="addCategory()">
            Add new additional category
          </button>
        </div>

        <!-- CONTENT ¡-->
        <button class="button" @click="submit" v-bind:disabled="!isValid">
          <template v-if="mode==='add'">Add</template>
          <template v-else>Update</template>
        </button>
      </overlay-box>
    </div>
  </main>
</body>
<script src="${pageContext.request.contextPath}/resources/js/admin/dishes.js"></script>
</html>
